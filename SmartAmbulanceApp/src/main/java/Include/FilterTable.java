/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Include;

import com.jfoenix.controls.JFXTextField;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author rachid dev
 */
public class FilterTable {
    
    public FilterTable(){
        
    }
    
    
    
     public static <T> void addTextFilter(ObservableList<T> allData,
      JFXTextField filterField, TableView<T> table) {

    final List<TableColumn<T, ?>> columns = table.getColumns();

    FilteredList<T> filteredData = new FilteredList<>(allData);
    filteredData.predicateProperty().bind(Bindings.createObjectBinding(() -> {
        String text = filterField.getText();

        if (text == null || text.isEmpty()) {
            return null;
        }
        final String filterText = text.toLowerCase();

        return o -> {
            for (TableColumn<T, ?> col : columns) {
                ObservableValue<?> observable = col.getCellObservableValue(o);
                if (observable != null) {
                    Object value = observable.getValue();
                    if (value != null && value.toString().toLowerCase().equals(filterText)) {
                        return true;
                    }
                }
            }
            return false;
        };
    }, filterField.textProperty()));

    SortedList<T> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(table.comparatorProperty());
    table.setItems(sortedData);
}
}
