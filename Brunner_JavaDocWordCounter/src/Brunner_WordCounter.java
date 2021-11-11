//Author: Lucas Brunner
//Date 2021-11-11
//Creates a hashtable of words and how often they occur.
//Class name: Brunner_WordCounter

import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;

public class Brunner_WordCounter
{
  /**
   * Returns a hashtable of the words in the provided file and a count of their occurrences. Returns null if an error occurs.
   * 
   * @param _filePath
   * @return
   * 
   * @author Lucas Brunner
   */
  public static Hashtable<String, Integer> ReadFile(String _filePath)
  {
    Scanner scanner;
    Hashtable<String, Integer> dictionary = new Hashtable<String, Integer>();
    try
    {
      scanner = new Scanner(new File(_filePath));
      
      // Loop through lines in file 
      while(scanner.hasNext())
      {
        String s = scanner.nextLine();
        
        s = formatForComparison(s);
        
        // Divide into words
        String[] sa = s.split(" ");
        
        // Loop through words and count their occurrences
        Integer tempInt = 0;
        for (int i = 0; i < sa.length; i++)
        {
          // Filter out "empty" words
          if (!sa[i].isEmpty())
          {
            // If word does not exists, add it. Otherwise add one to it
            tempInt = dictionary.get(sa[i]);
            if (tempInt == null)
            {
              dictionary.put(sa[i], 1);
            } else {
              dictionary.put(sa[i], tempInt + 1);
            }
          }
        }
      }
    } catch (Exception e)
    {
      return null;
    }
    
    return dictionary;
  }
  
  /**
   * Accepts a string, which can contains multiple words, and converts it to lowercase, removes non alpha-numeric numbers, and removes all unnessicary whitespace.
   * 
   * @param _string
   * @return
   * 
   * @author Lucas Brunner
   */
  private static String formatForComparison(String _string)
  {
    _string = _string.toLowerCase();
    _string = _string.replaceAll("[^a-z0-9' ]", "");
    _string = _string.replaceAll("\\s+", " ");
    _string.strip();
    
    return _string;
  }
}









