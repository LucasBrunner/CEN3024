
// Author: Lucas Brunner
// Date 23-8-2021
// Class name: Brunner_Vector3
// Vector3 class. Because I'm used to gamedev and don't like change.

public class Brunner_Vector3
{
  // I would use getters/setters here but Java doesn't have C#-style properties.
  public int X = 0;
  public int Y = 0;
  public int Z = 0;
  
  public Brunner_Vector3() {}
  
  public Brunner_Vector3(int _value)
  {
    X = _value;
    Y = _value;
    Z = _value;
  }
  
  public Brunner_Vector3(int _X, int _Y, int _Z)
  {
    X = _X;
    Y = _Y;
    Z = _Z;
  }
  
  public String toString()
  {
    return "X: " + X + ", Y: " + Y + ", Z: " + Z;
  }
}
