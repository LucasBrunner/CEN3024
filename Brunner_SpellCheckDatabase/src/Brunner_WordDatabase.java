//Author: Lucas Brunner
//Date 2021-11-21
//Puts every word in a text file into a MySQL database and allows the program to query the database.
//Class name: Brunner_WordDatabase

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Brunner_WordDatabase
{
  private Connection conn;
  
  /**
   * Connects to the database.
   * @return Returns true if connection was successful, otherwise false with exception print.
   * @throws Exception
   */
  public boolean getConnection()
  {
    try
    {
      String driver = "com.mysql.cj.jdbc.Driver";
      String url = "jdbc:mysql://localhost:3306/word occurrences";
      String username = "admin";
      String password = "Brunner2001";
      Class.forName(driver);
      
      Connection conn = DriverManager.getConnection(url, username, password);
      this.conn = conn;
      System.out.println("connected");
      return true;
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
  /**
   * Deletes every row from the database.
   * @throws SQLException
   */
  public void clearDict() throws SQLException
  {
    PreparedStatement statement = conn.prepareStatement("DELETE FROM word");
    statement.executeUpdate();
  }
  
  /**
   * Adds all the words in a file to the database.
   * @param _file
   * @throws SQLException
   */
  public void addFileContents(File _file) throws SQLException
  {
    Brunner_TextFileContents contents = new Brunner_TextFileContents();
    contents.LoadFile(_file);
    
    for (int i = 0; i < contents.GetSize(); i++)
    {
      if (isStringInDict(contents.GetWord(i)) == false)
      {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO word (word) VALUES ('"+contents.GetWord(i)+"')");
        statement.executeUpdate();
      }
    }    
  }
  
  /**
   * Queries the database to check if a word is in it.
   * @param _s
   * @return
   */
  public boolean isStringInDict(String _s)
  {
    PreparedStatement statement;
    try
    {
      statement = conn.prepareStatement("SELECT id FROM word WHERE word LIKE '%"+_s+"%'");
      ResultSet result = statement.executeQuery();
      
      return result.next();
    } catch (SQLException e)
    {
      return true;
    }
  }
  
  /**
   * Prints every every word in the database.
   * @throws SQLException
   */
  public void printDict() throws SQLException
  {
    PreparedStatement statement = conn.prepareStatement("SELECT word FROM word");
    ResultSet result = statement.executeQuery();

    System.out.println("\nDatabase contents:");
    while (result.next())
    {
      System.out.println(result.getString("word"));
    }
    
  }
}









