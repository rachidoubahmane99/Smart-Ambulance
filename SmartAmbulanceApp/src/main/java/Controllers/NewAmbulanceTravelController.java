/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import IOTsensors.GpsSensor;
import IOTsensors.HearBeatSensor;
import IOTsensors.TemperateurSensor;
import Include.Notification;
import MainConrollers.AmbulanceMainController;
import MainConrollers.AmbulanceTravelMainController;
import MainConrollers.PatientMainController;
import Models.Ambulance;
import Models.AmbulanceTravel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rachid dev
 */
public class NewAmbulanceTravelController implements Initializable  {
     @FXML
    private JFXTextField adress;
    @FXML
    private JFXComboBox ambulance;
    @FXML
    private JFXComboBox patient;
    @FXML
    private JFXDatePicker datetravel;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton backBtn;

        AmbulanceTravel am;
    AmbulanceTravelMainController amc;
    AmbulanceMainController amb   = new AmbulanceMainController();
    PatientMainController patients = new PatientMainController();
     
    Notification nt = new Notification();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         try {
             FillAmbulances(amb.getAmbulancesList());
             FillPatients(patients.getPatientsList());
         } catch (SQLException ex) {
             System.out.println("Error cannot Fill comboboxes");
         }
         TodayDate();
         save.setOnAction(Action -> {
            
        try {
            NewTravel();
        }  catch (SQLException ex) {
               System.out.println("Controllers.NewAmbulanceTravelController.initialize()");
           }
         });
                              
    }
    
    
    public boolean checkInputs(){
             if(adress.getText().equals(""))
                 return false;
             
             if(ambulance.getValue().toString().equals(""))
                 return false;
             if(patient.getValue().toString().equals(""))
                 return false;
              if(datetravel.getValue().toString().equals(""))
                 return false;
               
        return true;
                         
         }
    
    public void FillAmbulances(List ambulances){
                ambulance.setItems(FXCollections.observableArrayList(ambulances));
    }
    public void FillPatients(List patients){
                patient.setItems(FXCollections.observableArrayList(patients));
    }
    
    public void TodayDate(){
        datetravel.setValue(LocalDate.now());
    }
    
    private void NewTravel() throws SQLException {
          if (checkInputs()) {
              String FullName =patient.getValue().toString();
              String[] parts = FullName.split(" ");
              String Nom = parts[0]; 
              String Prenom = parts[1];
              java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(datetravel.getValue());
              boolean etat = false;
              am = new AmbulanceTravel(ambulance.getValue().toString(), patients.getPatientByName(Nom), adress.getText(),gettedDatePickerDate, etat); 
              amc= new AmbulanceTravelMainController();
           boolean result = amc.addAmbulanceTravel(am);
           
              System.out.println("here");
              if (result) {
                  nt.SuccessNotification("Ambulance Voyage :   est Créer avec succes", "Ambulave Travel Added succesfully");
                  System.out.println("Ambulance Travel added succesfully");
              }else{
                  nt.SuccessNotification("Erreur Le voyage n'est pas Créer", "Merci de remplir tous les champs");
               
              }
        
              }
    
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
    
}
