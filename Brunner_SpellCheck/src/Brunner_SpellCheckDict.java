import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Author: Lucas Brunner
//Date 2021-9-8
//Reads lines from a text file, separates the words, removes duplicates, and allows for another program to check if a string matches one of the contained words.
//Class name: Brunner_SpellCheckDict

public class Brunner_SpellCheckDict
{
  private List<String> dict = new ArrayList<>();

  /**
   * Takes in a file and loads each word into an list.
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
        String[] sArray = s.split(" ");
        for (int i = 0; i < sArray.length; i++)
        {
          dict.add(sArray[i]);
        }
      }
      in.close();
    } catch (Exception e)
    {
      System.out.println("An error occured while reading the dictionary file.");
      return -1;
    }
    return 0;
  }
  
  /**
   * Returns true if the string parameter is identical to a word in the dictionary.
   * @param _word
   * @return
   */
  public boolean IsWordInDict(String _word)
  {
    for (int i = 0; i < dict.size(); i++)
    {
      if (dict.get(i).compareTo(_word) == 0)
      {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Removes duplicate words from the dictionary.
   */
  public void RemoveDuplicates()
  {
    boolean exists = false;
    for (int i = 0; i < dict.size(); i++)
    {
      exists = false;
      for (int j = 0; j < dict.size(); j++)
      {
        if (dict.get(i).compareTo(dict.get(j)) == 0)
        {
          if (exists == true)
          {
            dict.remove(j);
            --j;
          } else 
          {
            exists = true;
          }
        }
      }
    }
  }
}













