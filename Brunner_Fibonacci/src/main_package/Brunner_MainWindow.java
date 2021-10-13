//Author: Lucas Brunner
//Date 2021-10-7
//Opens a Javafx window which allows the user to input the parameters of a comparison of recursive and iterative implementations of a Fibonacci series calculator.
//Class name: Brunner_MainWindow

package main_package;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import math.Brunner_IterativeFib;
import math.Brunner_RecursiveFib;

public class Brunner_MainWindow extends Application
{
  public static List<XYChart.Series<Number, Number>> sl = Collections.synchronizedList(new ArrayList<XYChart.Series<Number, Number>>()); // Thread safe list
  
  public static LineChart<Number, Number> lineChart;
  public static Button goButton = new Button();

  public static TextField incrementField = new TextField();
  public static TextField depthField = new TextField();
  public static TextField samplesField = new TextField();
  
  public static void main(String[] args)
  {
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception
  {    
    VBox stack = new VBox(10);
    stack.setPadding(new Insets(10));
    
    // Setup linechart
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Sequnce Depth");
    yAxis.setLabel("Time in Nanoseconds");
    
    lineChart = new LineChart<>(xAxis, yAxis);
    lineChart.setTitle("Fibonacci Method Runtime Efficiency");
    stack.getChildren().add(lineChart);
    
    // Input box label and field container
    GridPane inputbar = new GridPane();
    inputbar.setHgap(10);
    inputbar.setVgap(10);
    stack.getChildren().add(inputbar);
    
    // Labels
    Text incrementLabel = new Text("Increment value");
    Text depthLabel = new Text("Fibonacci depth");
    Text samplesLabel = new Text("Sample count");
    
    inputbar.add(incrementLabel, 0, 0);
    inputbar.add(depthLabel, 1, 0);
    inputbar.add(samplesLabel, 2, 0);
    
    // Fields
    incrementField.setText("50");
    depthField.setText("2500");
    samplesField.setText("10");
    
    inputbar.add(incrementField, 0, 1);
    inputbar.add(depthField, 1, 1);
    inputbar.add(samplesField, 2, 1);
    
    // Run button
    goButton.setText("Calculate fibonacci chart");
    goButton.setOnMouseClicked((event) ->
    {
      RecalculateTable();
    });
    stack.getChildren().add(goButton);
    
    // Initialize window
    Scene mainScene = new Scene(stack, 600, 500);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * Recalculates the values in the linechart. Uses multithreading to prevent the window from locking.
   */
  public void RecalculateTable()
  {
    // clear linechart data
    if (lineChart.getData() != null)
    {
      lineChart.getData().removeAll(lineChart.getData());
    }
    
    // create a multithreaded Javafx Task
    Task<Void> task = new Task<Void>()
    {
      protected Void call()
      {
        // Try-catch block to prevent integer parsing errors
        try
        {
          // Disable the run button
          goButton.setDisable(true);
          
          // Create and start multithreaded processing methods
          Brunner_ChartCalculator iterative = new Brunner_ChartCalculator(new Brunner_IterativeFib(), Integer.parseInt(incrementField.getText()), Integer.parseInt(depthField.getText()), Integer.parseInt(samplesField.getText()));
          Brunner_ChartCalculator recursive = new Brunner_ChartCalculator(new Brunner_RecursiveFib(), Integer.parseInt(incrementField.getText()), Integer.parseInt(depthField.getText()), Integer.parseInt(samplesField.getText()));
          iterative.start();
          recursive.start();
          
          // Wait for them to complete
          iterative.join();
          recursive.join();
          
          // Add results to a thread safe list
          sl.add(iterative.GetSeries());
          sl.get(0).setName("Iterative Time");
          sl.add(recursive.GetSeries());
          sl.get(1).setName("Recursive Time");

          // Create a runnable to update the data. The runnable will run on the next possible ui update.
          javafx.application.Platform.runLater(new Runnable()
          {
            @Override
            public void run()
            {
              lineChart.getData().add(sl.get(0));
              lineChart.getData().add(sl.get(1));
              sl.clear();
            }
          });
          
        } catch (Exception e) {}
        
        // Re-enable the run button
        goButton.setDisable(false);
        return null;
      }
    };
    
    // Run the Task
    Thread th = new Thread(task);
    th.start();
  }
}























