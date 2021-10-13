//Author: Lucas Brunner
//Date 2021-10-7
//Calculates Fibonacci numbers iteratively.
//Class name: Brunner_IterativeFib

package math;

public class Brunner_IterativeFib implements Brunner_FibonacchiBase
{

  @Override
  public int GetFib(int _startVal1, int _startVal2, int _finalDepth)
  {
    int val1 = _startVal1;
    int val2 = _startVal2;
    int val3 = 0;
    
    if (_finalDepth < 1)
    {
      return -1;
    }
    
    while (_finalDepth > 0)
    {
      --_finalDepth;
      
      val3 = val1 + val2;
      val1 = val2;
      val2 = val3;
    }
    
    return val3;
  }
  
}
