/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BdConnect.DbConnection;
import Controllers.*;
import Include.Notification;
import MainConrollers.AdminMainController;
import Models.Admin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import animatefx.animation.BounceIn;
import animatefx.animation.Tada;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import jakarta.swing.Action;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private JFXPasswordField password;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXDialog dialog;
    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton backBtn;

     File selectedFile = null;
    public static String userDirectory = System.getProperty("user.dir");
    public static String profileImGpath =userDirectory+"/src/main/resources/AdminProfileIMG/";
     
    Admin d;
    AdminMainController adm;
    Notification nt = new Notification();
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    userIV.setOnMouseClicked(Action -> {
            chooseImage();
        });
      saveBtn.setOnAction(Action -> {
            
        try {
            insertAdmin();
        } catch (IOException ex) {
            Logger.getLogger(NewUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
    
    }    
   
    public void inserAdmin(){
        System.out.println("Controllers.NewUserController.inserAdmin()");
    }
    
        public void chooseImage()
    {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Select a .JPG .PNG .GIF image", "*.jpg", "*.png", "*.gif")
        );

        selectedFile = fileChooser.showOpenDialog(saveBtn.getScene().getWindow());

        if (selectedFile != null) {
            try {
                userIV.setFill(new ImagePattern(new Image(
                        selectedFile.toURI().toString(),
                        userIV.getCenterX(), userIV.getCenterY(), false, false)));
                    //animateNode(new BounceIn(userIV));                
            }
            catch (Exception e) {
                System.out.println("Controllers.NewUserController.chooseImage()");
            }
        }

    } 
     
        
       
        private void pickImage(ActionEvent event) throws IOException {
       
            chooseImage();
        }
        
        public ResourceBundle bundle;
        
         public void loadDialog(JFXDialogLayout layout, boolean btnIncluded, Button defaultBtn){
        
        stackPane.setVisible(true);
        JFXButton btn = new JFXButton(bundle.getString("okay"));
        btn.setDefaultButton(true);
        defaultBtn.setDefaultButton(false);
        btn.setOnAction(Action -> {
            dialog.close();
            stackPane.setVisible(false);
            btn.setDefaultButton(false);
            defaultBtn.setDefaultButton(true);
        });
        if(btnIncluded){
            layout.setActions(btn);
        }    
        dialog = new JFXDialog(stackPane, layout , JFXDialog.DialogTransition.CENTER);
        dialog.setOverlayClose(false);
        dialog.show();
        
    }
         
           public static void initLayout(JFXDialogLayout layout, String header, String body, String icon){
                String IMAGES_PATH = "/App/images/";
        Image image = new Image(IMAGES_PATH + icon);
        ImageView imageView = new ImageView(image);
        Label label = new Label(header);
        label.graphicProperty().setValue(imageView);
        layout.setHeading(label);
        layout.setBody(new Text(body));        
        
    }
            
      public void customDialog(String title, String body, String icon, boolean btnIncluded, Button defaultBtn){
            JFXDialogLayout layout = new JFXDialogLayout();
            initLayout(layout, title, body, icon);
            
            loadDialog(layout, btnIncluded, defaultBtn);
    }
      
      
      public  boolean checkInputs()
    {
         String WAIT_SMALL = "wait_small.png";
    
    String ERROR_SMALL = "error_small.png";
    
    String INFO_SMALL = "info_small.png";

    String QUESTION_SMALL = "question_small.png";
        
        if (nom.getText().trim().equals("")) {
            customDialog(bundle.getString("missing_fields"), bundle.getString("missing_fields_msg"),ERROR_SMALL, true, saveBtn);
            return false;
        }
        else if(!nom.getText().matches("^[\\p{L} .'-]+$")){
            customDialog(bundle.getString("invalid_name"), bundle.getString("invalid_name_msg"), ERROR_SMALL, true, saveBtn);
            return false;              
        }        
        else if(prenom.getText().trim().equals("") || password.getText().trim().equals("")){
            customDialog(bundle.getString("missing_fields"), bundle.getString("missing_fields_msg"), ERROR_SMALL, true, saveBtn);
            return false;            
            
        }
         else if(email.getText().trim().equals("")){
            customDialog(bundle.getString("missing_fields"), bundle.getString("missing_fields_msg"), ERROR_SMALL, true, saveBtn);
            return false;            
            
        }
        else if(!prenom.getText().trim().matches("^[a-zA-Z0-9._-]{5,30}$")){
            customDialog(bundle.getString("invalid_username"), bundle.getString("invalid_username_msg"), ERROR_SMALL, true, saveBtn);
            return false;              
        }
        else if(!password.getText().trim().matches("^[a-zA-Z0-9._-]{7,30}$")){
            customDialog(bundle.getString("invalid_password"), bundle.getString("invalid_password_msg"), ERROR_SMALL, true, saveBtn);
            return false;              
        }
        else if(!phone.getText().trim().matches("^0[5-7][0-9]{8}$") && !phone.getText().equals("")){
            customDialog(bundle.getString("invalid_phone"), bundle.getString("invalid_phone_msg"), ERROR_SMALL, true, saveBtn);
            return false;              
        }
        
        return true;

    }

      public static String generateImagePath(File selectedFile)
    {

        java.util.Date date = new java.util.Date();
 
        SimpleDateFormat sdf = new SimpleDateFormat("Y-M-d-hh-mm-ss");
        
        String fileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
 
        //return profileImGpath+ + sdf.format(date) + fileExtension;
          return sdf.format(date) + fileExtension;      
    }
    
         public static String saveSelectedImage(File selectedFile) throws FileNotFoundException, IOException
    {
        String GeneratedpathImage = generateImagePath(selectedFile);
        String createImagePath = profileImGpath+GeneratedpathImage;

            FileOutputStream out;
            try (FileInputStream in = new FileInputStream(selectedFile)) {
                out = new FileOutputStream(createImagePath);
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            }
            out.close();


//ImageIO.write(img, format, new File(createImagePath));

//File file = new File(createImagePath);
        return GeneratedpathImage;
    }
         
    private void insertAdmin() throws IOException, SQLException {
          //if (checkInputs()) {
              String createImagePath = saveSelectedImage(selectedFile);
              //String createImagePath = "path";
           d = new Admin(nom.getText(), prenom.getText(), email.getText(), phone.getText(), 1, password.getText(),createImagePath);
       adm= new AdminMainController();
           boolean result = adm.addAdmin(d);
              if (result) {
                  nt.SuccessNotification("admin added suucesfully", "le compte admin est active");
                  System.out.println("Admin added succesfully");
              }else{
                  nt.SuccessNotification("Erreur l'admin n'est pas ajouté", "Merci de remplir tous les champs");
               
              }
         
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
    @FXML
    private void GoToBackView(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
    stage = (Stage) backBtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admins.fxml"));
      root = loader.load();
    Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        
}
    
}
