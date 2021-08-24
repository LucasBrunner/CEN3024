import java.util.HashMap;
import java.util.Map;

// Author: Lucas Brunner
// Date 23-8-2021
// Class name: Brunner_Directions
// Class which holds an enum which represents the cardinal directions.

// Int to Enum code from: https://codingexplained.com/coding/java/enum-to-integer-and-integer-to-enum

public class Brunner_Directions
{
  public enum Direction
  {
      North   (0)
    , East    (1)
    , South   (2)
    , West    (3)
    , Default (-1);
    
    private int value;
    private static Map<Integer, Direction> map = new HashMap<>();
    
    static 
    {
      for (Direction d : Direction.values())
      {
        map.put(d.value, d);
      }
    }
    
    public static Direction ValueOf(int _value)
    {
      return map.get(_value);
    }
    
    public static Direction ClockwiseValue(Direction _d)
    {
      int v = _d.value;
      ++v;
      if (v > 3)
      {
        v = 0;
      }
      return ValueOf(v);
    }
    
    public static Direction CounterclockwiseValue(Direction _d)
    {
      int v = _d.value;
      --v;
      if (v < 0)
      {
        v = 3;
      }
      return ValueOf(v);
    }
    
    private Direction(int _value)
    {
      value = _value;
    }
    
    public int GetValue()
    {
      return value;
    }
  }
}
