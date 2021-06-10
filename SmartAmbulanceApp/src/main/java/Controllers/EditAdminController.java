/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.NewUserController.profileImGpath;
import static Controllers.NewUserController.saveSelectedImage;
import Include.Notification;
import MainConrollers.AdminMainController;
import Models.Admin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.smartambulanceapp.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class EditAdminController implements Initializable {

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
    private JFXButton saveBtn;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXDialog dialog;

    
    Admin d;
    AdminMainController adm;
    Notification nt = new Notification();
    
      File selectedFile = null;
    public static String userDirectory = System.getProperty("user.dir");
    public static String profileImGpath =userDirectory+"/src/main/resources/AdminProfileIMG/";
    
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
        
        userIV.setOnMouseClicked(Action -> {
            chooseImage();
        });
      saveBtn.setOnAction(Action -> {
            
        try {
            UpdateAdmin();
        } catch (IOException ex) {
            Logger.getLogger(NewUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
      
      
      //back button 
      
      returnBtn.setOnAction(Action -> {
            
            try {
               
                 Stage stage;
        Parent root;
        stage = (Stage) returnBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/AdminProfile.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

                
            } catch (IOException ex) {
                
            }
        });
      
      //end of back button
       
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
                System.out.println("Controllers.EditAdminController.chooseImage()");
            }
        }

    }
           
        private void pickImage(ActionEvent event) throws IOException {
       
            chooseImage();
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
         
         public boolean checkInputs(){
             if(nom.getText().equals(""))
                 return false;
             if(prenom.getText().equals(""))
                 return false;
             if(email.getText().equals(""))
                 return false;
             if(phone.getText().equals(""))
                 return false;
        return true;
                         
         }
         private String  checkifImagechoosed() throws IOException{
               String createImagePath="";
              if (selectedFile!=null) {
               createImagePath = saveSelectedImage(selectedFile);
              }
              return createImagePath;
         }
         
         private void UpdateAdmin() throws IOException, SQLException {
          if (checkInputs()) {
             
              String createImagePath=checkifImagechoosed();
              //String createImagePath = "path";
           d = new Admin(nom.getText(), prenom.getText(), email.getText(), phone.getText(),createImagePath);
       adm= new AdminMainController();
           boolean result = adm.UpdateProfile(d);
              if (result) {
                  nt.SuccessNotification("admin profile modifié avec succes", "le compte admin est Modifié");
                  System.out.println("Admin added succesfully");
              }else{
                  nt.SuccessNotification("Erreur l'admin profile n'est pas modifié", "Merci de remplir tous les champs");
               
              }
        
              }
         
    }
           
           
         public void showAdmin(Admin admin) {
            nom.setText(admin.getNom()) ;
            prenom.setText(admin.getPrenom()) ;
            email.setText(admin.getEmail()) ;
            phone.setText(admin.getPhone()) ;
      
        if (admin.getProfileImg()==null) {
            System.out.println("profile IMG 2");
         
            Image img = new Image("/AdminProfileIMG/default.png");
            userIV.setFill(new ImagePattern(img));
           
        }
        else {
            System.out.println("profile IMG 3");
         System.out.println(admin.getProfileImg());
            String imgPath = "/AdminProfileIMG/"+admin.getProfileImg();
           System.out.println(imgPath);
            Image img = new Image(imgPath);
            userIV.setFill(new ImagePattern(img));
          
        }

      

        }
    
}
