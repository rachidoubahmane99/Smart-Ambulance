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
import MainConrollers.PatientMainController;
import Models.Ambulance;
import Models.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author rachid dev
 */
public class AddAmbulanceController implements Initializable {
     @FXML
    private JFXTextField matricule;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXComboBox gps;
    @FXML
    private JFXComboBox temperateur;
    @FXML
    private JFXComboBox hearbeat;
    @FXML
    private JFXButton save;

        Ambulance am;
    AmbulanceMainController amc;
    GpsSensor gpsSensor = new GpsSensor();
     HearBeatSensor heartSensor = new HearBeatSensor();
      TemperateurSensor tempSensor = new TemperateurSensor();
    Notification nt = new Notification();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         try {
              FillHeartBeatSensor(heartSensor.getSensors());
             FillGpsSensor(gpsSensor.getSensors());
             FillTemperateurSensor(tempSensor.getSensors());
         } catch (SQLException ex) {
             Logger.getLogger(AddAmbulanceController.class.getName()).log(Level.SEVERE, null, ex);
         }
       save.setOnAction(Action -> {
            
        try {
            AddAmbulance();
        }  catch (SQLException ex) {
               Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
           }
         });
    }
    public boolean checkInputs(){
             if(matricule.getText().equals(""))
                 return false;
             if(nom.getText().equals(""))
                 return false;
             if(gps.getValue().toString().equals(""))
                 return false;
             if(temperateur.getValue().toString().equals(""))
                 return false;
              if(hearbeat.getValue().toString().equals(""))
                 return false;
               
        return true;
                         
         }
    private void AddAmbulance() throws SQLException {
          if (checkInputs()) {
              Boolean etat = true;
          am = new Ambulance(matricule.getText(), nom.getText(), gps.getValue().toString(), temperateur.getValue().toString(), hearbeat.getValue().toString(),etat);
       amc= new AmbulanceMainController();
           boolean result = amc.addAmbulance(am);
              if (result) {
                  nt.SuccessNotification("Ambulance : "+matricule.getText()+"  est ajouté avec succes", "Patient Added succesfully");
                  System.out.println("Ambulance added succesfully");
              }else{
                  nt.SuccessNotification("Erreur Ambulance n'est pas ajouté", "Merci de remplir tous les champs");
               
              }
        
              }
    }
    
    
    
    public void FillGpsSensor(List sensors){
                gps.setItems(FXCollections.observableArrayList(sensors));
    }
    public void FillHeartBeatSensor(List sensors){
                hearbeat.setItems(FXCollections.observableArrayList(sensors));
    }
    public void FillTemperateurSensor(List sensors){
                temperateur.setItems(FXCollections.observableArrayList(sensors));
    }
}
