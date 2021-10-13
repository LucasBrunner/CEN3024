import java.io.File;
import java.util.Scanner;

//Author: Lucas Brunner
//Date 2021-9-8
//A menu for interfacing with the Brunner_SpellCheckDict and Brunner_TextFileContents classes. Allows the user to input "dictionary" and "content" text files and informs the user of any word in the content file which is not in the dictionary file.
//Class name: Brunner_SpellCheckMenu

public class Brunner_SpellCheckMenu
{
  private static final int loadDictFileValue = 1;
  private static final int checkFileValue = 2;
  private static final int exitProgramValue = 3;
  
  public static boolean runMainLoop = true;
  
  private static Scanner scanner = new Scanner(System.in);
  private static Brunner_SpellCheckDict dict;
  
  /**
   * Main program loop. Contains the menu.
   * @param args
   */
  public static void main(String[] args)
  {
    int input = -1;
    
    while (runMainLoop)
    {
      PrintMenu();
      input = GetIntFromUser();
      
      switch (input)
      {
        case loadDictFileValue:
          LoadDictFile();
          break;
          
        case checkFileValue:
          CheckFile();
          break;
          
        case exitProgramValue:
          runMainLoop = false;
          break;
          
        default:
          System.out.println("Invalid selection.");
          break;
      }
    }
    
    System.out.println();
    System.out.println("Thanks for using the program, goodbye!");
  }

  /**
   * Loads the file path given and compares it to the currently loaded Brunner_SpellCheckDict.
   */
  private static void CheckFile()
  {
    if (dict != null)
    {
      String fileName;
      Brunner_TextFileContents contents = null;
      int result = 0;
      
      System.out.print("Enter the path of the file to load and spell check: ");
      fileName = GetStringFromUser();
      try
      {
        File f = new File(fileName);
        contents = new Brunner_TextFileContents();
        result = contents.LoadFile(f);
      } catch (Exception e)
      {
        System.out.println("Error: Something went wrong while loading the file.");
      }
      if (result != -1 && contents != null)
      {
        int size = contents.GetSize();
        for (int i = 0; i < size; i++)
        {
          String s = contents.GetWord(i);
          if (!dict.IsWordInDict(s))
          {
            System.out.println("An spelling error exists at " + contents.GetPosition(i) + ". " + s + ".");
          }
        }
      }
    } else 
    {
      System.out.println("Error: No dictionary loaded.");
    }
  }
  
  /**
   * Loads the file path given into a Brunner_SpellCheckDict.
   */
  private static void LoadDictFile()
  {
    String fileName;
    int result = 0;
    
    System.out.print("Enter the path of the file to load as the dictionary: ");
    fileName = GetStringFromUser();
    try
    {
      File f = new File(fileName);
      dict = new Brunner_SpellCheckDict();
      result = dict.LoadFile(f);
      dict.RemoveDuplicates();
    } catch (Exception e)
    {
      System.out.println("Error: Something went wrong while loading the file.");
    }
    if (result == -1)
    {
      dict = null;
    }
  }
  
  /**
   * Prints the menu options out.
   */
  private static void PrintMenu()
  {
    System.out.println("");
    System.out.println("Which command would you like to invoke?");
    System.out.println(loadDictFileValue + ": Load file as dictionary.");
    System.out.println(checkFileValue + ": Load file and check its spelling.");
    System.out.println(exitProgramValue + ": Exit program.");
    System.out.print("Enter the corresponding integer: ");
  }
  
  /**
   * Gets an int from the user, using recursion if the user gives a non-int input.
   * @return
   */
  private static int GetIntFromUser()
  {
    String in = scanner.next();
    int output = 0;
    
    try
    {
      output = Integer.parseInt(in);
    } catch (Exception e)
    {
      System.out.print("Error: User input is not an integer, please enter the input again: ");
      output = GetIntFromUser();
    }
    
    return output;
  }
  
  /**
   * Gets a string from the user.
   * @return
   */
  private static String GetStringFromUser()
  {
    return scanner.next();
  }
}
