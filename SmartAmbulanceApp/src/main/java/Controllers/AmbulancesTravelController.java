/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Include.FilterTable;
import MainConrollers.AmbulanceTravelMainController;
import Models.Ambulance;
import Models.AmbulanceTravel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.smartambulanceapp.App;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author rachid dev
 */
public class AmbulancesTravelController implements Initializable{
    
    
    App app = new App();
    AmbulanceTravel amt;
    AmbulanceTravelMainController amtc;
    //Notification nt = new Notification();
 
    
    
    
    @FXML
    private JFXTextField searchTextField;
    @FXML
    private TableView<AmbulanceTravel> tableView;
    @FXML
    private TableColumn<AmbulanceTravel,Integer> IdColumn;
    @FXML
    private TableColumn<AmbulanceTravel,String> matriculeColumn;
    @FXML
    private TableColumn<AmbulanceTravel,String> NomColumn;
    @FXML
    private TableColumn<AmbulanceTravel,String> AdressColumn;
    @FXML
    private TableColumn<AmbulanceTravel,Date> DateColumn;
    @FXML
    private TableColumn<AmbulanceTravel,Boolean> etatColumn;
    
   
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXButton suivreBtn;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton homebtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        amtc = new AmbulanceTravelMainController();   
        tableView.setItems(amtc.getAllAmbulancesTravel());
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("idTravel"));
        matriculeColumn.setCellValueFactory(new PropertyValueFactory<>("AmbulanceMatricule"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("FullNamePatient"));
        AdressColumn.setCellValueFactory(new PropertyValueFactory<>("AdressPatient"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("TravelDate"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("IsDone"));
         FilterTable filter = new FilterTable();
        filter.addTextFilter(amtc.getAllAmbulancesTravel(), searchTextField, tableView);
    }

    @FXML
    private void goToAddView(ActionEvent event) throws IOException {
       
         Stage stage;
        Parent root;
stage = (Stage) addbtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/NewAmbulanceTravel.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

}
     @FXML
    private void goToTrackView(ActionEvent event) throws IOException {
       AmbulanceTravel am;
         Stage stage;
        Parent root;
    stage = (Stage) suivreBtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Track.fxml"));
      root = loader.load();
      TrackController controller = loader.getController();
      am = tableView.getSelectionModel().getSelectedItem();
      controller.FillPatientData(am);
    
    Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        

}
    
    @FXML
    private void GoToDeleteView(ActionEvent event) throws IOException{
      
           AmbulanceTravel ambT ;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeleteConfirmation.fxml"));
     Parent root1;
        root1 = (Parent)loader.load();
      DeleteConfirmationController controller = loader.getController();
      ambT = tableView.getSelectionModel().getSelectedItem();
      controller.SetIdTravel(ambT);
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
