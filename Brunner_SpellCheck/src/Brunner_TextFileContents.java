import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Author: Lucas Brunner
//Date 2021-9-8
//Reads lines from a text file, separates the words into individual strings and lines, and allows for another program to iterate through them using a for loop.
//Class name: Brunner_TextFileContents

public class Brunner_TextFileContents
{
  private List<String[]> Contents = new ArrayList<String[]>();
  
  /**
   * Takes in a file and loads each word into a list, keeping track of the row and column.
   * @param _file
   * @return
   */
  public int LoadFile(File _file)
  {
    try
    {
      Scanner in = new Scanner(_file);
      while (in.hasNextLine())
      {
        String s = in.nextLine();
        s.replaceAll("[^abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNIOPQRSTUVWXYZ]", "");
        String[] sArray = s.split(" ");
        List<String> sList = new ArrayList<String>();
        for (int i = 0; i < sArray.length; i++)
        {
          if (sArray[i] != "")
          {
            sList.add(sArray[i]);
          }
        }
        sArray = new String[sList.size()];
        for (int i = 0; i < sArray.length; i++)
        {
          sArray[i] = sList.get(i);
        }
        Contents.add(sArray);
      }
      in.close();
    } catch (Exception e)
    {
      System.out.println(e.getMessage());
      return -1;
    }
    return 0;
  }
  
  /**
   * Returns the total count of words in the file.
   * @return
   */
  public int GetSize()
  {
    int size = 0;
    for (int i = 0; i < Contents.size(); i++)
    {
      size += Contents.get(i).length;
    }
    return size;
  }
  
  /**
   * Returns the word at the specified index.
   * @param _index
   * @return
   */
  public String GetWord(int _index)
  {
    int line = -1;
    int word = -1;
    while (_index >= 0)
    {
      ++line;
      _index -= Contents.get(line).length;
    }
    word = -_index - 1;
    
    return Contents.get(line)[word];
  }
  
  /**
   * Returns a string with the original line and word position of the word at the specified index.
   * @param _index
   * @return
   */
  public String GetPosition(int _index)
  {
    int line = -1;
    int word = -1;
    while (_index >= 0)
    {
      ++line;
      _index -= Contents.get(line).length;
    }
    word = -_index;
    ++line;
    
    return "line " + line + ", word " + word;
  }
}















