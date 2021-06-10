/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BdConnect.DbConnection;
import MainConrollers.AdminMainController;
import Models.Admin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.management.Query.value;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
       Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
      Admin d;
    AdminMainController adm;
public static int IdAdmin;
    
    @FXML
    private JFXTextField EmailTxt;

    @FXML
    private JFXPasswordField Passwordtxt;

    @FXML
    private JFXButton loginBtn;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
   @FXML
    public void login(MouseEvent event) throws IOException{
           
               if (validateCredentiel(EmailTxt.getText(),Passwordtxt.getText())) {
               SuccessNotification();
              // adm = new AdminMainController();
               //IdAdmin = adm.showadminId(EmailTxt.getText(), Passwordtxt.getText());
               Preferences userPreferences = Preferences.userRoot();
               userPreferences.put("adminEmail",EmailTxt.getText());
               
                 Stage stage;
                Parent root;
                stage = (Stage) loginBtn.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/fxml/HomePage.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/Styles/Styles.css");
                stage.setScene(scene);
                stage.show();
               
               
               /*
               Stage stage = new Stage();
               Parent root =FXMLLoader.load(getClass().getResource("/fxml/HomePage.fxml"));
               Scene scene = new Scene(root);
               scene.getStylesheets().add("/Styles/Styles.css");
               System.out.println(scene+"this is the Home  scene");
               stage.setScene(scene);
               stage.setResizable(true);
               stage.show();
               */
               }else{
               Failednotification();
               }
              
         
    
    }
    
    
    public boolean validateCredentiel(String email ,String mdp){
            boolean state = false;
            int active =1;
            
         String sql = "SELECT * FROM Admin Where email = ? and mdp = ? and active = ?";
         try {
             con = DbConnection.getConnection();
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, mdp);
                 preparedStatement.setInt(3, active);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                   state= false;
                } else {
                 state= true;
                }
                } catch (SQLException ex) {
             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
         }
   return state;
    }
    
    public void SuccessNotification(){
         String title = "Connected Successfully";
            String message = "Welcome To E-Smart Ambulance App";
            NotificationType notifyType = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notifyType);
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(new Duration(3));
        
        
    }
           
    public void Failednotification(){
       String title = "Failed";
            String message = "Email Ou Mot de passe inccorect";
            NotificationType notifyType = NotificationType.WARNING;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notifyType);
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(new Duration(3));
        
    }
   
}
