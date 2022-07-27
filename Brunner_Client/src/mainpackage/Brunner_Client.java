//Author: Lucas Brunner
//Date 2021-12-04
//The client program which sends numbers to the server and receives results.
//Class name: Brunner_Client

package mainpackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Brunner_Client extends Application
{
  private DataOutputStream toServer = null;
  private DataInputStream fromServer = null;
  
  public static void main(String[] args)
  {
    launch(args);
  }

  @Override
  public void start(Stage _primaryStage) throws Exception
  {
    BorderPane textFieldContainer = new BorderPane();
    textFieldContainer.setPadding(new Insets(5));
    textFieldContainer.setStyle("-fx-border-color: green");
    textFieldContainer.setLeft(new Label("Enter a number: "));
    
    TextField tf = new TextField();
    tf.setAlignment(Pos.BOTTOM_RIGHT);
    textFieldContainer.setCenter(tf);
    
    BorderPane mainPane = new BorderPane();
    TextArea ta = new TextArea();
    mainPane.setCenter(new ScrollPane(ta));
    mainPane.setTop(textFieldContainer);
    Scene scene = new Scene(mainPane, 450, 200);
    
    _primaryStage.setTitle("Client");
    _primaryStage.setScene(scene); 
    _primaryStage.show();
    
    tf.setOnAction(e -> {
      try
      {
        int num = Integer.parseInt(tf.getText().trim());
        
        toServer.writeInt(num);
        toServer.flush();
        
        String message = fromServer.readUTF();
        ta.appendText("Input = " + num + ". Output = " + message + "\n");
        
      } catch (Exception ex) { ex.printStackTrace(); }
    });
    
    try
    {
      Socket socket = new Socket("localhost", 8000);
      _primaryStage.setOnCloseRequest(event -> {
        try
        {
          socket.close();
        } catch (IOException e1) {}
      });
      
      fromServer = new DataInputStream(socket.getInputStream());
      
      toServer = new DataOutputStream(socket.getOutputStream());
    } catch (Exception e) { e.printStackTrace(); }
    
  }
}









