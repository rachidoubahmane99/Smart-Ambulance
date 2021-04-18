package com.mycompany.smartambulanceapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
            
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
         scene = new Scene(root);
        scene.getStylesheets().add("/Styles/Styles.css");
        stage.setTitle("Smart Ambulance App");
       //stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}