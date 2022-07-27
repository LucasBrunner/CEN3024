//Author: Lucas Brunner
//Date 2021-12-04
//The server program which receives inputs from the client and returns whether the number is prime.
//Class name: Brunner_Client

package mainpackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Brunner_Server extends Application
{
  private TextArea ta;
  
  public static void main(String[] args) 
  { 
    launch(args); 
  }
  
  @Override
  public void start(Stage _primaryStage) throws Exception
  {
    ta = new TextArea();
    
    Scene scene = new Scene(new ScrollPane(ta), 450, 200);
    _primaryStage.setTitle("Server");
    _primaryStage.setScene(scene);
    _primaryStage.show();
    
    StartServer();
  }
  
  private void StartServer()
  {
    new Thread( () -> {
      ServerSocket serverSocket = null;
      try
      {
        serverSocket = new ServerSocket(8000);
        Platform.runLater(() -> ta.appendText("Server started at " + new Date() + "\n"));
        
        Socket socket = serverSocket.accept();
        
        DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
        
        while (true)
        {
          int num = inputFromClient.readInt();
          primeCheckResults results = checkPrime(num);
          Platform.runLater(() -> ta.appendText("Input = " + num + ". Output = " + results.toString() + "\n"));
          switch (results)
          {
            case prime:
              outputToClient.writeUTF("The number is prime.");
              break;
            case notPrime:
              outputToClient.writeUTF("The number is not prime.");
              break;
            case invalid:
              outputToClient.writeUTF("The number cannot be prime.");
              break;
            
            default:
              outputToClient.writeUTF("Error!");
              break;
          }
        }
        
      } catch (Exception e) { e.printStackTrace(); }
      
      if (serverSocket != null)
      {
        try
        {
          serverSocket.close();
        } catch (IOException e) { e.printStackTrace(); }
      }
    }).start();
  }
  
  private primeCheckResults checkPrime(int num)
  {
    if (num <= 0)
    {
      return primeCheckResults.invalid;
    }
    if (num == 1)
    {
      return primeCheckResults.prime;
    }
    
    for (int i = 2; i <= num / 2; i++)
    {
      if (num % i == 0)
      {
        return primeCheckResults.notPrime;
      }
    }
    
    return primeCheckResults.prime;
  }
  
  private enum primeCheckResults
  {
      prime
    , notPrime
    , invalid;
  }
}









