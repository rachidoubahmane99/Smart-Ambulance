/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Include.Notification;
import MainConrollers.AdminMainController;
import MainConrollers.PatientMainController;
import Models.Admin;
import Models.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author rachid dev
 */
public class AddPatientController implements Initializable {
    
    @FXML
    private JFXTextField nom;
    @FXML
    private Label fullnameStatus;
    @FXML
    private JFXTextField phone;
    @FXML
    private Label phoneStatus;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField maladie;
    @FXML
    private JFXTextArea medicalNotes;
    @FXML
    private JFXTextField adress;
    @FXML
    private JFXDatePicker dateJoin;
    @FXML
    private JFXButton save;

        Patient p;
    PatientMainController pa;
    Notification nt = new Notification();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       save.setOnAction(Action -> {
            
        try {
            AddPatient();
        }  catch (SQLException ex) {
               Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
           }
         });
    }
    public boolean checkInputs(){
             if(nom.getText().equals(""))
                 return false;
             if(prenom.getText().equals(""))
                 return false;
             if(maladie.getText().equals(""))
                 return false;
             if(phone.getText().equals(""))
                 return false;
              if(medicalNotes.getText().equals(""))
                 return false;
               if(adress.getText().equals(""))
                 return false;
             if(dateJoin.getValue().equals(""))
                 return false;
        return true;
                         
         }
    private void AddPatient() throws SQLException {
          if (checkInputs()) {
             java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dateJoin.getValue());
           p = new Patient(nom.getText(), prenom.getText(), maladie.getText(), medicalNotes.getText(), adress.getText(), phone.getText(),gettedDatePickerDate);
       pa= new PatientMainController();
           boolean result = pa.addPatient(p);
              if (result) {
                  nt.SuccessNotification("Patient  est ajouté avec succes", "Patient Added succesfully");
                  System.out.println("Patient added succesfully");
              }else{
                  nt.SuccessNotification("Erreur le patient n'est pas ajouté", "Merci de remplir tous les champs");
               
              }
        
              }
    }
    
}
