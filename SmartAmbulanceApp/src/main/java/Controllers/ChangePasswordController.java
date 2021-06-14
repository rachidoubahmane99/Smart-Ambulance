/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Include.Notification;
import MainConrollers.AdminMainController;
import Models.Admin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    private JFXPasswordField current;
    private JFXPasswordField newPass;
    private JFXPasswordField repeat;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton homebtn;
      @FXML
    private JFXButton backBtn;

    
    Admin d;
    AdminMainController adm;
    Notification nt = new Notification();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       save.setOnAction(Action -> {
            
        try {
            ChangePass();
        }  catch (SQLException ex) {
               Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
           }
         });
    }    
    
    
       private void ChangePass() throws SQLException, IOException{
          
           d = new Admin();
       adm= new AdminMainController();
           if (newPass.getText().equals(repeat.getText())) {
                 boolean result = adm.ChangePass(current.getText(), newPass.getText());
              if (result) {
                  nt.SuccessNotification("Mot de pass changé avec succes", "Mercii");
                  System.out.println("Password Changed");
                  BackToProfile();
                  
              }else{
                  nt.Failednotification("Erreur Mot de pass n'est pas changé", "Merci de ressayer  a nouveau");
               
              }
           }else{
                nt.Failednotification("Les deux Mot de pass sont different ", "Merci de ressayer  a nouveau");
           }
       
         
         
    }
       private void BackToProfile() throws IOException{
             Stage stage;
        Parent root;
        stage = (Stage) save.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/AdminProfile.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
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
    
    @FXML
    private void GoToBackView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
    stage = (Stage) backBtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminProfile.fxml"));
      root = loader.load();
    Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        
}
}
