//Author: Lucas Brunner
//Date 2021-10-7
//Calculates Fibonacci numbers recursively.
//Class name: Brunner_RecursiveFib

package math;

public class Brunner_RecursiveFib implements Brunner_FibonacchiBase
{

  @Override
  public int GetFib(int _startVal1, int _startVal2, int _finalDepth)
  {
    if (_finalDepth > 1)
    {
      return this.GetFib(_startVal2, _startVal1 + _startVal2, --_finalDepth);
    } else if (_finalDepth == 1) {
      return _startVal1 + _startVal2;
    } else {
      return -1;
    }
  }  
}
