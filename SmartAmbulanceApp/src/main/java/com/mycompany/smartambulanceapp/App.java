package com.mycompany.smartambulanceapp;

import BdConnect.DbConnection;
import Models.Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    Connection con = null;
    public Statement stmt;
    public Stage primaryStage;
    
    
    ResultSet resultSet = null;
    @Override
    public void start(Stage stage) throws IOException {
            
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
         scene = new Scene(root);
        scene.getStylesheets().add("/Styles/Styles.css");
        stage.setTitle("Smart Ambulance App");
       //stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        Image logo = new Image("/Images/smart ambulance logo.png");
        //Image applicationIcon = new Image(getClass().getResourceAsStream("Images/Logo.png"));
        stage.getIcons().add(logo);
        stage.show();

    }
    
    //methode pour switcher entre les scens
    public static void switchView(String view) throws IOException {
        //parent vanuit MainApp laden
     

    }
    public void switchScene(String fxmlFile)
{

    FXMLLoader loader = new FXMLLoader(getClass()
            .getResource(fxmlFile));
    Parent root;
    try 
    {
        root = (Parent)loader.load();
   
        this.primaryStage.setScene(new Scene(root));
    } 
    catch (IOException e)
    {
        e.printStackTrace();
    }

}
    public static void main(String[] args) {
       
        launch(args);
    }

}