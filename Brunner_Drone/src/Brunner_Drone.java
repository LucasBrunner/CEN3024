
// Author: Lucas Brunner
// Date 23-8-2021
// Class name: Brunner_Drone
// Class for storing drone information.

public class Brunner_Drone
{
  public Brunner_Directions.Direction direction = Brunner_Directions.Direction.North;
  public Brunner_Vector3 pos = new Brunner_Vector3();
  
  public void MoveX(int _moveAmount)
  {
    pos.X += _moveAmount;
  }
  
  public void MoveY(int _moveAmount)
  {
    pos.Y += _moveAmount;
  }
  
  public void MoveZ(int _moveAmount)
  {
    pos.Z += _moveAmount;
  }
  
  public void Move(Brunner_Vector3 _moveAmount)
  {
    pos.X += _moveAmount.X;
    pos.Y += _moveAmount.Y;
    pos.Z += _moveAmount.Z;
  }
  
  public String toString()
  {
    return "Position: " + pos.toString() + ", Direction: " + direction.name();
  }
}
