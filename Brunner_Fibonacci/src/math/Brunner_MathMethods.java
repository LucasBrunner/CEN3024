//Author: Lucas Brunner
//Date 2021-10-7
//Contains math methods which can be used elsewhere in the program.
//Class name: Brunner_MathMethods

package math;

import java.util.Dictionary;
import java.util.Hashtable;

public class Brunner_MathMethods
{  
  /**
   * Accepts an array of longs and returns the number which appeared the most times.
   * @param _longs
   * @return
   */
  public static long FindLongMode(long[] _longs)
  {
    Dictionary<Long, Integer> d = new Hashtable<Long, Integer>();
    
    // Put all numbers in a dictionary. Duplicates will overwrite each other which is fine
    for (int i = 0; i < _longs.length; i++)
    {
      d.put(_longs[i], 0);
    }
    
    // For each occurrence of a number increment its instance in the dictionary
    Integer tempInt = 0;
    for (int i = 0; i < _longs.length; i++)
    {
      tempInt = d.get(_longs[i]);
      d.put(_longs[i], tempInt += 1);
    }
    
    // Iterate through the dictionary's values and pick the key with the highest value
    long max = 0;
    int maxCount = 0;
    for (int i = 0; i < _longs.length; i++)
    {
      if (maxCount < d.get(_longs[i]))
      {
        maxCount = d.get(_longs[i]);
        max = _longs[i];
      }
    }
    
    return max;
  }
}















