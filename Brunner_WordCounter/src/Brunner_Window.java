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
import javafx.stage.Window;

public class Brunner_Window extends Application
{
  private TextField filePathField;
  private Button goButton;
  private HBox goButtonBox;
  private Text genericErrorMessage;
  public static ListView<String> wordCountList;
  
  /**
   * Launches the {@link Window}.
   * 
   * @author Lucas Brunner
   */
  public static void main(String[] args)
  {
    // Create a window and start it
    launch("");
  }

  /**
   * {@link Scene} generation code.
   * 
   * @Override
   * 
   * @author Lucas Brunner
   */
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
    
    genericErrorMessage = new Text("Error: Something went wrong!");
    genericErrorMessage.setFill(Color.RED);
    
    // Listview for file scan results
    wordCountList = new ListView<String>();
    wordCountList.setPrefHeight(500);
    stack.getChildren().add(wordCountList);
    
    // Instantiate window
    Scene mainScene = new Scene(stack, 500, 600);
    _primaryStage.setScene(mainScene);
    _primaryStage.setTitle("Word Counter");
    _primaryStage.show();
  }
  
  /**
   * Scans the file in the path specified in {@link filePathField}, counts how many times each word occurs, and records that amount in {@link #wordCountList}.
   * 
   * @author Lucas Brunner
   */
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
          addErrorMessage();
        } else {
          // Otherwise, first remove error message if exists  
          removeErrorMessage();

          // Clear listview
          wordCountList.getItems().removeAll(wordCountList.getItems());

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
            wordCountList.getItems().add(entries.get(i).string + " - " + entries.get(i).integer);
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
  
  /**
   * Adds {@link #genericErrorMessage} to the bottom of the screen.
   * 
   * @author Lucas Brunner
   */
  private void addErrorMessage()
  {
    javafx.application.Platform.runLater(new Runnable()
    {
      @Override
      public void run()
      {
        if (!goButtonBox.getChildren().contains(genericErrorMessage))
        {
          goButtonBox.getChildren().add(genericErrorMessage);
        }
      }
    });
  }
  
  /**
   * Removes {@link #genericErrorMessage} from the screen.
   * 
   * @author Lucas Brunner
   */
  private void removeErrorMessage()
  {
    javafx.application.Platform.runLater(new Runnable()
    {
      @Override
      public void run()
      {
        goButtonBox.getChildren().remove(genericErrorMessage);
      }
    }); 
  }
}

/**
 * A simple object for storing a word and its amount of occurrences.
 * 
 * @author Lucas Brunner
 */
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










