//Author: Lucas Brunner
//Date 2021-10-7
//Manages threading outside of the Fibonacci implementations to speed up the program and times each running of the implementations. 
//Class name: Brunner_ChartCalculator

package main_package;

import javafx.scene.chart.XYChart;
import math.Brunner_FibonacchiBase;
import math.Brunner_MathMethods;

public class Brunner_ChartCalculator implements Runnable
{
  private Brunner_FibonacchiBase fib;
  private int increment;
  private int depth;
  private int samples;
  
  private XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();

  private Thread t;
  
  public Brunner_ChartCalculator(Brunner_FibonacchiBase _fib, int _increment, int _depth, int _samples)
  {
    fib = _fib;
    increment = Math.max(_increment, 1);
    depth = Math.min(Math.max(_depth, 1), 10000); // Artificial depth cap of 10 thousand to prevent stack overflows
    samples = Math.max(_samples, 1);
    
    t = new Thread(this);
  }
  
  public XYChart.Series<Number, Number> GetSeries()
  {
    return series;
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

  public void run()
  {
    // Calculate how many values need to be generated
    int loops = depth / increment; 
    loops = Math.max(1, loops);
    
    // Calculate values
    for (int i = 0; i < loops; i++)
    {
      CalculateSeries(depth - (i * increment));
    }
  }
  
  private void CalculateSeries(int _depth)
  {
    long startTime = 0;
    long endTime = 0;
    long[] timeValues = new long[samples];
    
    // Measures runtime for multiple samples
    for (int j = 0; j < samples; j++)
    {
      startTime = System.nanoTime();
      fib.GetFib(1, 1, _depth + 1);
      endTime = System.nanoTime();
      timeValues[j] = endTime - startTime; // Subtract start from end time and add to list
    }
    
    // Add average sample (mode) to the dataset
    series.getData().add(new XYChart.Data<Number, Number>(_depth + 1, Brunner_MathMethods.FindLongMode(timeValues)));    
  }
}












