/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BdConnect.DbConnection;
import Models.Admin;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class AdminsController implements Initializable {

    
    Connection con = null;
    public Statement stmt;
    
    ResultSet resultSet = null;
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("active"));

        tableView.setItems(getAdmins());

        //To Previous Scene
     
        
    }    

    @FXML
    private void searchUsers(KeyEvent event) {
        System.out.println("Controllers.AdminsController.searchUsers()");
    }

    @FXML
    private void goToAddView(ActionEvent event) {
        System.out.println("Controllers.AdminsController.goToAddView()");
    }
    
    
    public ObservableList<Admin> getAdmins() {

        ObservableList<Admin> admins = FXCollections.observableArrayList();
        try {
           con = DbConnection.getConnection();
                   stmt = con.createStatement();
            resultSet = stmt.executeQuery("SELECT IdAdmin, nom, prenom, email, phone, active FROM Admin");

            while (resultSet.next()) {
                int id = resultSet.getInt("IdAdmin");
                String prenom = resultSet.getString("prenom");
                String nom = resultSet.getString("nom");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                int active = resultSet.getInt("active");

                System.out.println("ID: " + id + "  prenom: " + prenom + " nom: " + nom + " email: " + email + " phone: " + phone + " active: " + active);
                admins.add(new Admin(id, nom, prenom, email, phone, active));

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return admins;
    }
}
