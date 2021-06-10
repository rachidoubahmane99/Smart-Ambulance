/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BdConnect.DbConnection;
import Models.Admin;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.smartambulanceapp.App;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class AdminsController implements Initializable {
    
    App app = new App();
    Connection con = null;
    public Statement stmt;
    
    ResultSet resultSet = null;
    
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
    private void searchAdmins(KeyEvent event) {
        ObservableList<Admin> admins = FXCollections.observableArrayList();
        String searchInput = searchTextField.getText();

        try {
             con = DbConnection.getConnection();
                   stmt = con.createStatement();
        //String query="select * from Admin where nom like'"+searchInput+"' ";
        resultSet =stmt.executeQuery("SELECT IdAdmin, nom, prenom, email, phone, active FROM Admin where nom like'"+searchInput+"' ");

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

            for (int i = 0; i < admins.size(); i++) {
                System.out.println(admins.get(i));
            }
            tableView.setItems(admins);

        } catch (SQLException ex) {
            Logger.getLogger(AdminsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void goToAddView(ActionEvent event) throws IOException {
        
        //NewUserController.edit = false;

        //app.switchScene("/fxml/NewUser.fxml");
        //System.out.println("Controllers.AdminsController.goToAddView()");
         Stage stage;
        Parent root;
stage = (Stage) AddNewAdminBtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/NewUser.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
/*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewUser.fxml"));
    Parent root;
    try 
    {
        root = (Parent)loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Styles/Styles.css");
         scene.getStylesheets().add("/Styles/buttons.css");
               Stage stage = new Stage();
                  stage.setScene(scene);
               stage.setResizable(true);
               stage.show();
        app.primaryStage.setScene(scene);
        
    } 
    catch (IOException e)
    {
        e.printStackTrace();
    }
*/
}
    
        
    public ObservableList<Admin> getAdmins() {

        ObservableList<Admin> admins = FXCollections.observableArrayList();
        String searchInput = searchTextField.getText();
          if (searchInput!="") {
            
        }
        try {
           con = DbConnection.getConnection();
           AdminsController admin = new AdminsController();
         
           resultSet = admin.executeSearchQuery(searchInput);
                  // stmt = con.createStatement();
            //resultSet = stmt.executeQuery("SELECT IdAdmin, nom, prenom, email, phone, active FROM Admin");

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
    
    public ResultSet executeSearchQuery(String searchInput) throws SQLException {
        
        con = DbConnection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * "
                + "FROM Admin WHERE IdAdmin LIKE ? ESCAPE '!' OR prenom "
                + "LIKE ? ESCAPE '!' OR nom LIKE ? ESCAPE '!' OR email LIKE ? "
                + "ESCAPE '!' OR active LIKE ? ESCAPE '!' OR phone LIKE ? ESCAPE '!'");
        String search = searchInput
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");

        preparedStatement.setString(1, "%" + search + "%");
        preparedStatement.setString(2, "%" + search + "%");
        preparedStatement.setString(3, "%" + search + "%");
        preparedStatement.setString(4, "%" + search + "%");
        preparedStatement.setString(5, "%" + search + "%");
        preparedStatement.setString(6, "%" + search + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
    
}
