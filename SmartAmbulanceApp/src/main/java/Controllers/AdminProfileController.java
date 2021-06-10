/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.NewUserController.saveSelectedImage;
import Include.Notification;
import MainConrollers.AdminMainController;
import Models.Admin;
import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import com.jfoenix.controls.JFXButton;
import com.mycompany.smartambulanceapp.App;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class AdminProfileController implements Initializable {

    @FXML
    private Circle adminIV;
    @FXML
    private Label nom;
    @FXML
    private Label clientPhone;
    @FXML
    private Label prenom;
    @FXML
    private Label regCommerce;
    @FXML
    private Label email;
    @FXML
    private Label clientNIF;
    @FXML
    private Label telephone;
    @FXML
    private Label clientAI;
    @FXML
    private JFXButton updateadminBtn;
    @FXML
    private JFXButton deleteAdminBtn;
    @FXML
    private JFXButton changepassBtn;
    
    public static String userDirectory = System.getProperty("user.dir");
    public static String profileImGpath =userDirectory+"/src/main/resources/AdminProfileIMG/default.png";
    Notification nt = new Notification();

     Admin d;
    AdminMainController adm;
    App app = new App();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        adm = new AdminMainController();
      
        try {
            showAdmin(adm.showAdminByEmail());
        } catch (SQLException ex) {
            Logger.getLogger(AdminProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // disible admin account 
         deleteAdminBtn.setOnAction(Action -> {
            
        try {
            DisableAdmin();
        } catch (IOException ex) {
            Logger.getLogger(NewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (SQLException ex) {
                Logger.getLogger(AdminProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
        
       
        }
        @FXML
        private void goToChangePassView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
        stage = (Stage) changepassBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/changePassword.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        }
        @FXML
        private void GoToUpdateView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
        stage = (Stage) updateadminBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/EditAdmin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        }
    
    
     public void showAdmin(Admin admin) {
       
        

        if(!admin.getNom().trim().equals(""))
            clientPhone.setText(admin.getNom()) ;

        if(!admin.getPrenom().trim().equals(""))
            regCommerce.setText(admin.getPrenom()) ;
        

        if(!admin.getEmail().trim().equals(""))
            clientAI.setText(admin.getEmail()) ;
        
        if(!admin.getPhone().trim().equals(""))
            clientNIF.setText(admin.getPhone()) ;
         System.out.println("profile IMG");
         System.out.println(admin.getProfileImg());
         
         
        if (admin.getProfileImg()==null) {
            System.out.println("profile IMG 2");
         
            Image img = new Image("/AdminProfileIMG/default.png");
            adminIV.setFill(new ImagePattern(img));
           /* 
            adminIV.setFill(new ImagePattern(new Image(
                    ClassLoader.class.getResourceAsStream(profileImGpath),
                    adminIV.getCenterX(), adminIV.getCenterY(), false, false)));
*/
        }
        else {
            System.out.println("profile IMG 3");
         System.out.println(admin.getProfileImg());
            String imgPath = "/AdminProfileIMG/"+admin.getProfileImg();
           System.out.println(imgPath);
            Image img = new Image(imgPath);
            adminIV.setFill(new ImagePattern(img));
            /*
            adminIV.setFill(new ImagePattern(new Image(
                    new File(admin.getProfileImg()).toURI().toString(),
                    adminIV.getCenterX(), adminIV.getCenterX(), false, false)));
*/
        }

      

        }
     
     
     
     //desactiver le compte
     private void DisableAdmin() throws IOException, SQLException {
          
           d = new Admin(clientPhone.getText(), regCommerce.getText(), clientAI.getText(), clientNIF.getText());
       adm= new AdminMainController();
           boolean result = adm.DesactiverAdmin(d);
              if (result) {
                  nt.SuccessNotification("admin account removed  suucesfully", "le compte admin est active");
                  System.out.println("Admin removed succesfully");
              }else{
                  nt.SuccessNotification("Erreur le compte est  non desactiver ", "Merci de Resseyer a nouveau");
               
              }
         
    }
      
    
}
