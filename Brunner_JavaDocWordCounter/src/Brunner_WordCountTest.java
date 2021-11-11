//Author: Lucas Brunner
//Date 2021-10-30
//Performs a junit test on the Brunner_WordCounter class.
//Class name: Brunner_WordCounterTest

import static org.junit.jupiter.api.Assertions.*;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

class Brunner_WordCountTest
{
  
  /**
   * Test for the {@link Brunner_WordCounter#ReadFile(String)} method.
   * 
   * @author Lucas Brunner
   */
  @Test
  void WordCount1()
  {
    Hashtable<String, Integer> output = Brunner_WordCounter.ReadFile(System.getProperty("user.dir") + "\\TestFile.txt");
    assertEquals(output.get("zero"), null);
    assertEquals(output.get("one"), 1);
    assertEquals(output.get("two"), 2);
    assertEquals(output.get("three"), 3);
    assertEquals(output.get("four"), 4);
  }
  
}
