//Author: Lucas Brunner
//Date 2021-11-4
//The main window for the application.
//Class name: Brunner_MainWindow

package main_package;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Brunner_MainWindow extends Application
{
  public static Brunner_TestResultViewer resultViewer = new Brunner_TestResultViewer();
  public static Button goButton = new Button();
  
  public static TextField numberField = new TextField();
  public static TextField threadField = new TextField();
 
  public static void main(String[] args)
  {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    VBox stack = new VBox(10);
    stack.setPadding(new Insets(10));
    
    // Setup TableView container
    stack.getChildren().add(resultViewer.GetTable());
    
    // Input box label and field container
    GridPane inputbar = new GridPane();
    inputbar.setHgap(10);
    inputbar.setVgap(10);
    stack.getChildren().add(inputbar);
    
    // Labels
    Text numberLabel = new Text("Amount of numbers");
    Text threadLabel = new Text("Amount of threads");
    
    inputbar.add(numberLabel, 0, 0);
    inputbar.add(threadLabel, 1, 0);
    
    // Fields    
    numberField.setText("200000000");
    threadField.setText("5");
    
    inputbar.add(numberField, 0, 1);
    inputbar.add(threadField, 1, 1);
    
    // Run button
    goButton.setText("Calculate Time");
    goButton.setOnMouseClicked((event) ->
    {
      OnStartButtonPress();
    });
    stack.getChildren().add(goButton);
    
    // Warning message
    Text threadWarning = new Text("Note: There is a bug which causes thread amounts higher than 1000 to result in a recorded completion time which is much higher than it should be. I was not able to track the source of the problem down but I also did not include a hard limit on the program so that higher thread counts could still be timed manually. \nBe warned, \"Time taken\" may not be accurate at all thread count values.");
    threadWarning.wrappingWidthProperty().bind(stack.widthProperty());
    stack.getChildren().add(threadWarning);
    
    // Initialize window
    Scene mainScene = new Scene(stack, 600, 500);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }
  
  private void OnStartButtonPress()
  {
    // Disable the run button
    goButton.setDisable(true);
    
    // create a multithreaded Javafx Task
    Task<Void> task = new Task<Void>()
    {
      protected Void call()
      {
        try
        {
          // Get data
          Brunner_TestData results = Brunner_ThreadRunner.RunThreads(Integer.parseInt(threadField.getText()), Integer.parseInt(numberField.getText()));
          
          // Create a runnable to update the data. The runnable will run on the next possible ui update.
          javafx.application.Platform.runLater(new Runnable()
          {
            @Override
            public void run()
            {
              // Add data
              resultViewer.AddDataItem(results);
            }
          });
        } catch (Exception e)
        {
          e.printStackTrace();
        }
        
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









