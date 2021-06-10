/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BdConnect.DbConnection;
import Include.Notification;
import MainConrollers.PatientMainController;
import Models.Admin;
import Models.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.smartambulanceapp.App;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class PatientsController implements Initializable {

    
     App app = new App();
     Patient p;
    PatientMainController pa;
    //Notification nt = new Notification();
   
    
    ResultSet resultSet = null;
    
    
    
    @FXML
    private JFXTextField searchTextField;
    @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient,Integer> idColumn;
    @FXML
    private TableColumn<Patient,String> NomColumn;
    @FXML
    private TableColumn<Patient,String> prenomColumn;
    @FXML
    private TableColumn<Patient,String> maladieColumn;
    @FXML
    private TableColumn<Patient,String> medicalNotesColumn;
    @FXML
    private TableColumn<Patient,String> adressColumn;
    @FXML
    private TableColumn<Patient,String> phoneColumn;
    @FXML
    private TableColumn<Patient,Date> joinDateColumn;
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXButton updatebtn;
    @FXML
    private JFXButton delete;

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pa = new PatientMainController();
         tableView.setItems(pa.getAllPatients());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        maladieColumn.setCellValueFactory(new PropertyValueFactory<>("maladie"));
        medicalNotesColumn.setCellValueFactory(new PropertyValueFactory<>("medicalNotes"));
        adressColumn.setCellValueFactory(new PropertyValueFactory<>("Adress"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));

       
    }    
    
    
    

    @FXML
    private void goToAddView(ActionEvent event) throws IOException {
       
         Stage stage;
        Parent root;
stage = (Stage) addbtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/AddPatient.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

}
     @FXML
    private void GoToUpdateView(ActionEvent event) throws IOException {
       Patient p;
         Stage stage;
        Parent root;
    stage = (Stage) updatebtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditPatient.fxml"));
      root = loader.load();
      EditPatientController controller = loader.getController();
      p = tableView.getSelectionModel().getSelectedItem();
      
      controller.FillInputes(p);
    // root = FXMLLoader.load(getClass().getResource("/fxml/EditPatient.fxml"));
    Scene scene = new Scene(root);
        stage.setScene(scene);
      // EditPatientController controller = root.getController();
        stage.show();
        

}
    
    
    
    
}
