//Author: Lucas Brunner
//Date 2021-11-4
//Contains a thread for adding numbers together.
//Class name: Brunner_AdditionThread

package main_package;

public class Brunner_AdditionThread implements Runnable
{
  private int[] numbers;
  private int returnValue;
  
  private Thread t;
  
  public Brunner_AdditionThread() 
  {
    t = new Thread(this);
  }
  
  public void SetNumbers(int[] _numbers)
  {
    numbers = _numbers;
  }
  
  public int GetResult()
  {
    return returnValue;
  }
  
  public void start()
  {
    t.start();
  }
  
  public void join()
  {
    try
    {
      t.join();
    } catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void run()
  {
    for (int i = 0; i < numbers.length; i++)
    {
      returnValue += numbers[i];
    }
  }
}









