//Author: Lucas Brunner
//Date 2021-11-5
//Creates, loads, starts, and ends threads for adding numbers together.
//Class name: Brunner_ThreadRunner

package main_package;

public class Brunner_ThreadRunner
{
  public static Brunner_TestData RunThreads(int _threadCount, int _numAmount)
  {
    Brunner_TestData output = new Brunner_TestData();
    output.SetStartTime(java.time.LocalDateTime.now());
    output.SetNumAmount(_numAmount);
    output.SetThreadAmount(_threadCount);
    
    // Create and load AdditionThreads
    Brunner_AdditionThread[] threads = new Brunner_AdditionThread[_threadCount];
    for (int i = 0; i < threads.length; i++)
    {
      threads[i] = new Brunner_AdditionThread();
    }
    Brunner_NumberDistributor.DistributeToThreads(threads, _numAmount);
    
    // Start Timer
    long startTime = 0;
    long endTime = 0;
    startTime = System.nanoTime();
    
    // Start AdditionThreads
    for (int i = 0; i < threads.length; i++)
    {
      threads[i].start();
    }

    // Wait for them to complete
    for (int i = 0; i < threads.length; i++)
    {
      threads[i].join();
    }
    
    // Add results together
    int total = 0;
    for (int i = 0; i < threads.length; i++)
    {
      total += threads[i].GetResult();
    }
    
    // End timer
    endTime = System.nanoTime();
    long totalTime = endTime - startTime;
    
    output.SetCountResult(total);
    output.SetTimeTaken(totalTime);
    
    return output;
  }
}
