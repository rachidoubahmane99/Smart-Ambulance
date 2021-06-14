/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class HomePageController implements Initializable {

    @FXML
    private JFXButton profileBtn;
    @FXML
    private JFXButton adminsBtn;
    @FXML
    private JFXButton patientBtn;
    @FXML
    private JFXButton ambulanceBtn;
    @FXML
    private JFXButton StatisticsBtn;
    @FXML
    private JFXButton TrackBtn;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToProfileView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
stage = (Stage) profileBtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/AdminProfile.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
      @FXML
    private void goToAdminsView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
stage = (Stage) adminsBtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/Admins.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void goToPatientView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
stage = (Stage) patientBtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/Patients.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void goToAmbulancesView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
stage = (Stage) ambulanceBtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/Ambulances.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void goToStatsView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
stage = (Stage) StatisticsBtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/Statistics.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void goToTrackView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
stage = (Stage) TrackBtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/AmbulancesTravel.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
