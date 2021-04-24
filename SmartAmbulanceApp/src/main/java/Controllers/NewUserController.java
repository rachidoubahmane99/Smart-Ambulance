/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class NewUserController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button returnBtn;
    @FXML
    private Circle userIV;
    @FXML
    private JFXTextField nom;
    @FXML
    private Label fullnameStatus;
    @FXML
    private JFXTextField phone;
    @FXML
    private Label phoneStatus;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXToggleButton admin;
    @FXML
    private Label fullnameStatus11;
    @FXML
    private Label phoneStatus11;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXDialog dialog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
