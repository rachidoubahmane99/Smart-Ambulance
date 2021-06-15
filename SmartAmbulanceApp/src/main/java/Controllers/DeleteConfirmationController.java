/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Include.Notification;
import MainConrollers.AmbulanceMainController;
import MainConrollers.AmbulanceTravelMainController;
import MainConrollers.PatientMainController;
import Models.Ambulance;
import Models.AmbulanceTravel;
import Models.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class DeleteConfirmationController implements Initializable {

    @FXML
    private JFXButton yesDeleteBtn;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXTextField matriculetxt;
    @FXML
    private JFXTextField idPatienttxt;
     @FXML
    private JFXTextField ambulanceTravelTxt;
    
    
    AmbulanceMainController amc;
    PatientMainController pa;
    AmbulanceTravelMainController amcT;
    Notification nt = new Notification();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
         yesDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent Action) {
                 if (matriculetxt.getText()!="") {
                     try {
                         deleteAmbulance(matriculetxt.getText());
                     } catch (SQLException ex) {
                         System.out.println("Error de suppression");
                     }
                 }
                 
                 if (idPatienttxt.getText()!="") {
                     try {
                         int idP = Integer.parseInt(idPatienttxt.getText());
                         deletePatient(idP);
                     } catch (SQLException ex) {
                         Logger.getLogger(DeleteConfirmationController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
                 if (ambulanceTravelTxt.getText()!="") {
                     try {
                         int idT = Integer.parseInt(ambulanceTravelTxt.getText());
                         deleteAmbulanceTravel(idT);
                     } catch (SQLException ex) {
                         Logger.getLogger(DeleteConfirmationController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 CloseView();
             }
         });
        
        //System.out.println("heyyy");
    }    

    @FXML
    private void leaveView(ActionEvent event) {
        
     Stage stage = (Stage) cancelBtn.getScene().getWindow();
    stage.close();
    }
    
    private void CloseView() {
        
     Stage stage = (Stage) cancelBtn.getScene().getWindow();
    stage.close();
    }
    
    public void deleteAmbulance(String matricule) throws SQLException{
      
      AmbulanceMainController amc = new AmbulanceMainController();
        boolean result = amc.DeleteAmbulance(matricule);
        if (result) {
            nt.SuccessNotification("ambulance est supprimé avec succes", "ambulance deleted successfully");
        }else{
            nt.Failednotification("ambulance Non supprimé ", "ambulance NOT deleted");
        }
        
    }
    
    public void deletePatient(int id) throws SQLException{
        boolean etat ;
        PatientMainController pa = new PatientMainController();
        etat = pa.DeletePatient(id);
        if (etat) {
            nt.SuccessNotification("Patient est supprimé avec succes", "patient deleted successfully");
        }else{
            nt.Failednotification("Patient Non supprimé ", "patient NOT deleted");
        }
        
    }
    
    public void deleteAmbulanceTravel(int id) throws SQLException{
        boolean etat = false;
        AmbulanceTravelMainController amcT = new AmbulanceTravelMainController();
        etat = amcT.deleteAmbulanceTravel(id);
        if (etat) {
            nt.SuccessNotification("ambulance Voyage est supprimé avec succes", "ambulance Travel deleted successfully");
        }else{
            nt.Failednotification("ambulance Voyage Non supprimé ", "ambulance Travel NOT deleted");
        }
        
    }
    
    
    public void SetMatricule(Ambulance am){
        matriculetxt.setText(am.getMatricule());
        
    }
    public void SetIdPatient(Patient p){
        idPatienttxt.setText(Integer.toString(p.getIdPatient()));
        
    }
    public void SetIdTravel(AmbulanceTravel am){
        ambulanceTravelTxt.setText(Integer.toString(am.getIdTravel()));
         
        
    }
    
}
