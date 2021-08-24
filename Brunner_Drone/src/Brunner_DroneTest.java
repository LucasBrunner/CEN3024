import java.util.Scanner;

// This is all in my personal GIT, which keeps track of revisions for me.
// Author: Lucas Brunner
// Date 23-8-2021
// "Simulates" drone movement in x, y, z locations + z rotation using cli as input.
   
// In Java the file name is the same as the class name, so this is doubly redundant
// Also this naming scheme is hell since I have to type in my last name any time I want to reference any class. If I want my last name to be part of the class name it should come after the unique class name so autocomplete still works.
// Class name: Brunner_DroneTest

public class Brunner_DroneTest
{
  private static Scanner scanner = new Scanner(System.in);
  
  private static final int xMoveValue = 1;
  private static final int yMoveValue = 2;
  private static final int zMoveValue = 3;
  private static final int rotateValue = 4;
  private static final int displayPosValue = 5;
  private static final int exitValue = 6;
  
  public static void main(String[] args)
  {
    int input = -1;
    boolean doLoop = true;
    Brunner_Drone drone = new Brunner_Drone();
    
    System.out.println("Welcome to the drone controller!");
    
    while (doLoop)
    {
      PrintMenu();
      input = GetIntFromUser();
      
      switch (input)
      {
        case xMoveValue:
          MoveDrone(drone, 'X');
          break;
        case yMoveValue:
          MoveDrone(drone, 'Y');
          break;
        case zMoveValue:
          MoveDrone(drone, 'Z');
          break;
        case rotateValue:
          RotateDrone(drone);
          break;
        case displayPosValue:
          System.out.println("Drone position: " + drone.pos.toString() + ", rotation: " + drone.direction.name() + ".");
          break;
        case exitValue:
          doLoop = false;
          break;
        default:
          System.out.println("Invalid selection.");
          break;
      }
    }
    
    System.out.println();
    System.out.println("Thanks for using the program, goodbye!");
  }
  
  private static void MoveDrone(Brunner_Drone _drone, char _direction)
  {
    System.out.print("Enter the distance you would like the drone to move (Enter a negative integer to move backwards): ");
    int moveDis = GetIntFromUser();
    switch (_direction)
    {
      case 'X':
        _drone.pos.X += moveDis;
        break;
      case 'Y':
        _drone.pos.Y += moveDis;
        break;
      case 'Z':
        _drone.pos.Z += moveDis;
        break;      
      default:
        System.out.println("Error: Oops, something went super wrong with the code!");
        break;
    }
    System.out.println("Drone move along the " + _direction + " axis " + moveDis + " units.");
  }
  
  private static void RotateDrone(Brunner_Drone _drone)
  {
    System.out.print("Input 'R' to rotate the drone clockwise, input 'L' to rotate it counterclockwise: ");
    char direction = GetStringFromUser().toLowerCase().charAt(0);
    
    switch (direction)
    {
      case 'r':
        _drone.direction = Brunner_Directions.Direction.ClockwiseValue(_drone.direction);
        System.out.println("Rotated the drone clockwise.");
        break;
      case 'l':
        _drone.direction = Brunner_Directions.Direction.CounterclockwiseValue(_drone.direction);
        System.out.println("Rotated the drone counterclockwise.");
        break;
      default:
        System.out.println("Invalid selection.");
        break;
    }
  }
  
  private static void PrintMenu()
  {
    System.out.println();
    System.out.println("What command would you like to issue to the drone?");
    System.out.println(xMoveValue + ": Move along X axis.");
    System.out.println(yMoveValue + ": Move along Y axis.");
    System.out.println(zMoveValue + ": Move along Z axis.");
    System.out.println(rotateValue + ": Rotate along Z axis.");
    System.out.println(displayPosValue + ": Display position.");
    System.out.println(exitValue + ": Exit program.");
    System.out.print("Enter the corresponding integer: ");
  }
  
  private static int GetIntFromUser()
  {
    String in = scanner.next();
    int output = 0;
    
    try
    {
      output = Integer.parseInt(in);
    } catch (Exception e)
    {
      System.out.print("Error: User input is not an integer, please enter the input again: ");
      output = GetIntFromUser();
    }
    
    return output;
  }
  
  private static String GetStringFromUser()
  {
    return scanner.next();
  }
}











