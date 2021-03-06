/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Include.FilterTable;
import MainConrollers.AmbulanceMainController;
import MainConrollers.PatientMainController;
import Models.Ambulance;
import Models.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.smartambulanceapp.App;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 *
 * @author rachid dev
 */
public class AmbulancesController implements Initializable{
    
    
    App app = new App();
    Ambulance am;
    AmbulanceMainController amc;
    //Notification nt = new Notification();
   
    
    ResultSet resultSet = null;
    
    
    
    @FXML
    private JFXTextField searchTextField;
    @FXML
    private TableView<Ambulance> tableView;
    @FXML
    private TableColumn<Ambulance,String> matriculeColumn;
    @FXML
    private TableColumn<Ambulance,String> NomColumn;
    @FXML
    private TableColumn<Ambulance,String> GpsSensorColumn;
    @FXML
    private TableColumn<Ambulance,String> TemperateurColumn;
    @FXML
    private TableColumn<Ambulance,String> HearBeatColumn;
    @FXML
    private TableColumn<Ambulance,Boolean> etatColumn;
    
   
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXButton updatebtn;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton homebtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        amc = new AmbulanceMainController();
         tableView.setItems(amc.getAllAmbulances());
        matriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("NomChafeur"));
        GpsSensorColumn.setCellValueFactory(new PropertyValueFactory<>("GpsSensor"));
        TemperateurColumn.setCellValueFactory(new PropertyValueFactory<>("Temperateur"));
        HearBeatColumn.setCellValueFactory(new PropertyValueFactory<>("HearBeat"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
         FilterTable filter = new FilterTable();
        filter.addTextFilter(amc.getAllAmbulances(), searchTextField, tableView);
        
        
       
    }
    
     @FXML
    private void goToAddView(ActionEvent event) throws IOException {
       
         Stage stage;
        Parent root;
stage = (Stage) addbtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/AddAmbulance.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

}
     @FXML
    private void GoToUpdateView(ActionEvent event) throws IOException {
       Ambulance am;
         Stage stage;
        Parent root;
    stage = (Stage) updatebtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditAmbulance.fxml"));
      root = loader.load();
      EditAmbulanceController controller = loader.getController();
      am = tableView.getSelectionModel().getSelectedItem();
      controller.FillInputes(am);
    
    Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        

}
    
    @FXML
    private void GoToDeleteView(ActionEvent event) throws IOException{
      
        Parent root ;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeleteConfirmation.fxml"));
     Parent root1;
        root1 = (Parent)loader.load();
      DeleteConfirmationController controller = loader.getController();
      am = tableView.getSelectionModel().getSelectedItem();
      controller.SetMatricule(am);
            Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
       
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

    
}
