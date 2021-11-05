//Author: Lucas Brunner
//Date 2021-11-4
//Creates and distributes arrays of random numbers to threads.
//Class name: Brunner_NumberDistributor

package main_package;

import java.util.Random;

public class Brunner_NumberDistributor
{
  private static Random rand = new Random();
  
  /**
   * Generates an array of numbers from 1-10.
   * @param _arrayLength
   * @return
   */
  private static int[] GenerateRandomInts(int _arrayLength)
  {
    int[] output = new int[_arrayLength];
    
    for (int i = 0; i < output.length; i++)
    {
      output[i] = rand.nextInt(10) + 1; // nextInt generates a random int from 0 (inclusive) to the specified number (exclusive) so 1 must be added to the number to get a value 1-10.
    }
    
    return output;
  }
  
  public static void DistributeToThreads(Brunner_AdditionThread[] _threads, int _totalNumCount)
  {
    if (_threads.length > 0)
    {
      int numsPerThread = _totalNumCount / _threads.length;
      
      _threads[0].SetNumbers(GenerateRandomInts(numsPerThread + _totalNumCount % _threads.length)); // Add the remainder to the first value to ensure that there are the correct amount of total numbers.
      
      for (int i = 1; i < _threads.length; i++)
      {
        _threads[i].SetNumbers(GenerateRandomInts(numsPerThread));
      }
    }
  }
}
