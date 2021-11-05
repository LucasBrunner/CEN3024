//Author: Lucas Brunner
//Date 2021-11-5
//A control object for the TableView.
//Class name: Brunner_TestResulViewer

package main_package;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Brunner_TestResultViewer
{
  private TableView<Brunner_TestData> table = new TableView<Brunner_TestData>();
  
  private ObservableList<Brunner_TestData> data = FXCollections.observableArrayList();
  
  public Brunner_TestResultViewer()
  {
    TableColumn<Brunner_TestData, String> timeCollumn = new TableColumn<Brunner_TestData, String>("Start Time");
    TableColumn<Brunner_TestData, String> numAmountCollumn = new TableColumn<Brunner_TestData, String>("Amount of numbers");
    TableColumn<Brunner_TestData, String> threadAmountCollumn = new TableColumn<Brunner_TestData, String>("Amount of threads");
    TableColumn<Brunner_TestData, String> timeTakenCollumn = new TableColumn<Brunner_TestData, String>("Time taken (seconds)");
    TableColumn<Brunner_TestData, String> countResultCollumn = new TableColumn<Brunner_TestData, String>("Count result");
    
    timeCollumn.setCellValueFactory(new PropertyValueFactory<Brunner_TestData, String>("startTime"));
    numAmountCollumn.setCellValueFactory(new PropertyValueFactory<Brunner_TestData, String>("numAmount"));
    threadAmountCollumn.setCellValueFactory(new PropertyValueFactory<Brunner_TestData, String>("threadAmount"));
    timeTakenCollumn.setCellValueFactory(new PropertyValueFactory<Brunner_TestData, String>("timeTaken"));
    countResultCollumn.setCellValueFactory(new PropertyValueFactory<Brunner_TestData, String>("countResult"));
    
    numAmountCollumn.setMinWidth(130);
    threadAmountCollumn.setMinWidth(130);
    timeTakenCollumn.setMinWidth(140);
    
    table.setItems(data);
    
    table.getColumns().add(timeCollumn);
    table.getColumns().add(numAmountCollumn);
    table.getColumns().add(threadAmountCollumn);
    table.getColumns().add(timeTakenCollumn);
    table.getColumns().add(countResultCollumn);
  }
  
  public void AddDataItem(Brunner_TestData _data)
  {
    data.add(_data);
  }
  
  public TableView<Brunner_TestData> GetTable()
  {
    return table;
  }
}
