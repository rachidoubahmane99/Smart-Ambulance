/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Include.FilterTable;
import MainConrollers.AdminMainController;
import Models.Admin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.smartambulanceapp.App;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class AdminsController implements Initializable {
    
    App app = new App();
    
    @FXML
    private Button AddNewAdminBtn;
    
    @FXML
    private JFXTextField searchTextField;
    @FXML
    private TableView<Admin> tableView;
    @FXML
    private TableColumn<Admin,Integer> idColumn;
    @FXML
    private TableColumn<Admin,String> lastNameColumn;
    @FXML
    private TableColumn<Admin,String> firstNameColumn;
    @FXML
    private TableColumn<Admin,String> locationColumn;
    @FXML
    private TableColumn<Admin,String> roleColumn;
    @FXML
    private TableColumn<Admin,Integer> statusColumn;
     @FXML
    private JFXButton homebtn;
     
     AdminMainController adm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         adm = new AdminMainController();
         tableView.setItems(adm.getAllAdmins());
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("active"));

        FilterTable filter = new FilterTable();
        filter.addTextFilter(adm.getAllAdmins(), searchTextField, tableView);

     
        
    }    

    @FXML
    private void goToAddView(ActionEvent event) throws IOException {

         Stage stage;
        Parent root;
stage = (Stage) AddNewAdminBtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/NewUser.fxml"));
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
 
    

    
    
}
