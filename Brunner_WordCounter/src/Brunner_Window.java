//Author: Lucas Brunner
//Date 2021-10-12
//Generates the window for the program.
//Class name: Brunner_Window

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Brunner_Window extends Application
{
  private TextField filePathField;
  private Button goButton;
  private HBox goButtonBox;
  private Text filepathErrorMessage;
  private ListView<String> list;
  
  /**
   * Opens the window
   */
  public void LaunchWindow()
  {
    launch("");
  }

  @Override
  public void start(Stage _primaryStage) throws Exception
  {
    // Outermost pane
    VBox stack = new VBox();
    stack.setPadding(new Insets(10));
    stack.setSpacing(10);
    
    // Horizontal pane for user file input
    HBox filePathInputBox = new HBox();
    filePathInputBox.setSpacing(10);
    stack.getChildren().add(filePathInputBox);
    
    filePathInputBox.getChildren().add(new Text("Enter filepath here:")); 
    
    filePathField = new TextField();
    filePathInputBox.getChildren().add(filePathField);

    // Horizontal pane for start button
    goButtonBox = new HBox();
    goButtonBox.setSpacing(10);
    stack.getChildren().add(goButtonBox);
    
    goButton = new Button("Scan file");
    goButtonBox.getChildren().add(goButton);
    goButton.setOnMouseClicked((event) ->
    {
      ScanFile();
    });
    
    filepathErrorMessage = new Text("Error: Something went wrong!");
    filepathErrorMessage.setFill(Color.RED);
    
    // Listview for file scan results
    list = new ListView<String>();
    list.setPrefHeight(500);
    stack.getChildren().add(list);
    
    // Instantiate window
    Scene mainScene = new Scene(stack, 500, 600);
    _primaryStage.setScene(mainScene);
    _primaryStage.setTitle("Word Counter");
    _primaryStage.show();
  }
  
  private void ScanFile()
  {
    // Multithreaded task to prevent window from freezing
    Task<Void> task = new Task<Void>()
    {
      @Override
      protected Void call() throws Exception
      {
        // Disable button to prevent multiple threads from running at once
        goButton.setDisable(true);
      
        // Get hashtable from file
        Hashtable<String, Integer> dictionary = Brunner_WordCounter.ReadFile(filePathField.getText());
        if (dictionary == null)
        {
          // If there was an error, add the error message
          javafx.application.Platform.runLater(new Runnable()
          {
            @Override
            public void run()
            {
              if (!goButtonBox.getChildren().contains(filepathErrorMessage))
              {
                goButtonBox.getChildren().add(filepathErrorMessage);
              }
            }
          });
        } else {
          // Otherwise, first remove error message if exists
          javafx.application.Platform.runLater(new Runnable()
          {
            @Override
            public void run()
            {
              goButtonBox.getChildren().remove(filepathErrorMessage);
            }
          });        

          // Clear listview
          list.getItems().removeAll(list.getItems());

          // Move hashtable entries to a sortable list
          List<SIPair> entries = new ArrayList<SIPair>();
          for (Entry<String, Integer> entry : dictionary.entrySet())
          {
            entries.add(new SIPair(entry.getValue(), entry.getKey()));
          }
          
          // Sort list
          Collections.sort(entries, Collections.reverseOrder());
          
          // Add sorted entries to the listview
          for (int i = 0; i < entries.size(); i++)
          {
            list.getItems().add(entries.get(i).string + " - " + entries.get(i).integer);
          }
        }
      
        // re-enable button
        goButton.setDisable(false);
        return null;
      }
    };
    
    // Run the Task
    Thread th = new Thread(task);
    th.start();
  }
}

// Class for sorting word/count pairs
class SIPair implements Comparable<SIPair>
{
  public int integer;
  public String string;
  
  public SIPair(int _integer, String _string)
  {
    string = _string;
    integer = _integer;
  }

  @Override
  public int compareTo(SIPair _o)
  {
    return ((Integer)integer).compareTo(_o.integer);
  }
}










