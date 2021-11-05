//Author: Lucas Brunner
//Date 2021-11-5
//An object for the results of a test which a TableView can read.
//Class name: Brunner_TestData

package main_package;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javafx.beans.property.SimpleStringProperty;

public class Brunner_TestData
{
  private final SimpleStringProperty startTime = new SimpleStringProperty();
  private final SimpleStringProperty numAmount = new SimpleStringProperty();
  private final SimpleStringProperty threadAmount = new SimpleStringProperty();
  private final SimpleStringProperty timeTaken = new SimpleStringProperty();
  private final SimpleStringProperty countResult = new SimpleStringProperty();
  
  public Brunner_TestData() {}
  
  public Brunner_TestData(LocalDateTime _time, int _numAmount, int _threadAmount, long _timeTaken, int _countResult)
  {
    SetValues(_time, _numAmount, _threadAmount, _timeTaken, _countResult);
  }
  
  public void SetStartTime(LocalDateTime _startTime)
  {
    startTime.set(_startTime.toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString());
  }
  
  public void SetNumAmount(int _numAmount)
  {
    numAmount.set(Integer.toString(_numAmount));
  }
  
  public void SetThreadAmount(int _threadAmount)
  {
    threadAmount.set(Integer.toString(_threadAmount));
  }
  
  public void SetTimeTaken(long _timeTaken)
  {
    double time = (double)_timeTaken / 100000000;
    timeTaken.set(Double.toString(time));
  }
  
  public void SetCountResult(int _countResult)
  {
    countResult.set(Integer.toString(_countResult));
  }
  
  public void SetValues(LocalDateTime _time, int _numAmount, int _threadAmount, long _timeTaken, int _countResult)
  {
    SetStartTime(_time);
    SetNumAmount(_numAmount);
    SetThreadAmount(_threadAmount);
    SetTimeTaken(_timeTaken);
    SetCountResult(_countResult);
  }
  
  public String getStartTime()
  {
    return startTime.get();
  }
  
  public String getNumAmount()
  {
    return numAmount.get();
  }
  
  public String getThreadAmount()
  {
    return threadAmount.get();
  }
  
  public String getTimeTaken()
  {
    return timeTaken.get();
  }
  
  public String getCountResult()
  {
    return countResult.get();
  }
}









