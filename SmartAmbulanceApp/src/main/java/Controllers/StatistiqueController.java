/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import MainConrollers.AmbulanceTravelMainController;
import MainConrollers.StatisticsMainController;
import Models.Statistique;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author rachid dev
 */
public class StatistiqueController implements Initializable  {
 
    
    @FXML
    private Label ambtlbl;
    @FXML
    private Label patlbl;
    @FXML
    private Label admlbl;
    @FXML
    private Label amblbl;
    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton backBtn;
    @FXML 
    private BarChart bCh;
    @FXML
    NumberAxis xAxis;
    @FXML
    CategoryAxis yAxis;
    StatisticsMainController st;
    AmbulanceTravelMainController ambt;
    
    Statistique s ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        st = new StatisticsMainController();
        ambt= new AmbulanceTravelMainController();
        BarchartInit(ambt.getAllTravelDates());
        try {
            
            
            FillStats(st.getTablesCount());
           
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void FillStats(Statistique st){
        admlbl.setText(" "+Integer.toString(st.getAdmCount()));
        amblbl.setText("  "+Integer.toString(st.getAmbCount()));
        patlbl.setText("  "+Integer.toString(st.getPatientCount()));
        ambtlbl.setText("  "+Integer.toString(st.getAmbTCount()));
   
    }
    
    // back and home Method
        @FXML
    private void GoToHomeView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
    stage = (Stage) homebtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
      root = loader.load();
    Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        
}
    @FXML
    private void GoToBackView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
    stage = (Stage) backBtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AmbulancesTravel.fxml"));
      root = loader.load();
    Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        
}
    
    
    public void BarchartInit(ArrayList<Date> arrayDates){
        
        ambt= new AmbulanceTravelMainController();
        
        //bCh.setTitle("Ambulance Voyage par Jour");
        xAxis.setLabel("Nombre");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Jour");
         XYChart.Series<String, Number> series1 = new XYChart.Series();
        
        for (int i=0; i<arrayDates.size(); i++){
        series1.setName("Ambulance Voyage Par Dates");       
        series1.getData().add(new XYChart.Data(arrayDates.get(i).toString(),ambt.getTravelCountByDate(arrayDates.get(i))));
        }

       
        bCh.getData().add(series1);
          
    }
    
    
    
      
}
