/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Include.Notification;
import MainConrollers.PatientMainController;
import Models.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author rachid dev
 */
public class EditPatientController implements Initializable {
    
    @FXML
    private JFXTextField nom;
    @FXML
    private Label fullnameStatus;
    @FXML
    private Label idPatient;
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
     @FXML
    private JFXButton homebtn;
      @FXML
    private JFXButton backBtn;

        Patient p;
    PatientMainController pa;
    Notification nt = new Notification();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       save.setOnAction(Action -> {
            
        try {
            EditPatient();
        }  catch (SQLException ex) {
               Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
           }
         });
    }
    
    public void FillInputes(Patient p){
      
      
        idPatient.setText(String.valueOf(p.getIdPatient()));
       nom.setText(p.getNom());
       prenom.setText(p.getPrenom());
       maladie.setText(p.getMaladie());
       phone.setText(p.getPhone());
       medicalNotes.setText(p.getMedicalNotes());
       adress.setText(p.getAdress());
    Date date = p.getJoinDate();
    Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
//Loc/*ofInstant*/alDate ld = p.getJoinDate().toLocalDate();
//LocalDate date   = LocalDate.ofInstant(input.toInstant(), ZoneId.systemDefault());
       dateJoin.setValue(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
 
             
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
    private void EditPatient() throws SQLException {
          if (checkInputs()) {
             java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dateJoin.getValue());
           p = new Patient(Integer.parseInt((idPatient.getText())),nom.getText(), prenom.getText(), maladie.getText(), medicalNotes.getText(), adress.getText(), phone.getText(),gettedDatePickerDate);
       pa= new PatientMainController();
           boolean result = pa.EditPatient(p);
              if (result) {
                  nt.SuccessNotification("Patient  est Modifié avec succes", "Patient Edited succesfully");
                  System.out.println("Patient Edited succesfully");
              }else{
                  nt.SuccessNotification("Erreur le patient n'est pas Modifié", "Merci de remplir tous les champs");
               
              }
        
              }
    }
    
    
        // back and home Method
        @FXML
    private void GoToHomeView(ActionEvent event) throws IOException {
       Patient p;
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
       Patient p;
         Stage stage;
        Parent root;
    stage = (Stage) backBtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Patients.fxml"));
      root = loader.load();
    Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        
}
    
}
