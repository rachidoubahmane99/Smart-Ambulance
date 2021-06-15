/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BdConnect.DbConnection;
import Include.FilterTable;
import Include.Notification;
import MainConrollers.PatientMainController;
import Models.Admin;
import Models.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.smartambulanceapp.App;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author rachid dev
 */
public class PatientsController implements Initializable {

    
     App app = new App();
     Patient p;
    PatientMainController pa;
    Notification nt = new Notification();
   
    
    ResultSet resultSet = null;
    
    
    
    @FXML
    private JFXTextField searchTextField;
    @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient,Integer> idColumn;
    @FXML
    private TableColumn<Patient,String> NomColumn;
    @FXML
    private TableColumn<Patient,String> prenomColumn;
    @FXML
    private TableColumn<Patient,String> maladieColumn;
    @FXML
    private TableColumn<Patient,String> medicalNotesColumn;
    @FXML
    private TableColumn<Patient,String> adressColumn;
    @FXML
    private TableColumn<Patient,String> phoneColumn;
    @FXML
    private TableColumn<Patient,Date> joinDateColumn;
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXButton updatebtn;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton export;

    Patient pat;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pa = new PatientMainController();
         tableView.setItems(pa.getAllPatients());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idPatient"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        maladieColumn.setCellValueFactory(new PropertyValueFactory<>("maladie"));
        medicalNotesColumn.setCellValueFactory(new PropertyValueFactory<>("medicalNotes"));
        adressColumn.setCellValueFactory(new PropertyValueFactory<>("Adress"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));

        FilterTable filter = new FilterTable();
        filter.addTextFilter(pa.getAllPatients(), searchTextField, tableView);
        
        export.setOnAction(Action -> {
            
        try {
            export();
        } catch (IOException ex) {
                Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
    }
       
        
    
    
    

    @FXML
    private void goToAddView(ActionEvent event) throws IOException {
       
         Stage stage;
        Parent root;
stage = (Stage) addbtn.getScene().getWindow();
     root = FXMLLoader.load(getClass().getResource("/fxml/AddPatient.fxml"));
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

}
     @FXML
    private void GoToUpdateView(ActionEvent event) throws IOException {
       Patient p;
         Stage stage;
        Parent root;
    stage = (Stage) updatebtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditPatient.fxml"));
      root = loader.load();
      EditPatientController controller = loader.getController();
      p = tableView.getSelectionModel().getSelectedItem();
      
      controller.FillInputes(p);
    Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        

}
    
    
        @FXML
    private void GoToDeleteView(ActionEvent event) throws IOException{
      Parent root ;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeleteConfirmation.fxml"));
     Parent root1;
        root1 = (Parent)loader.load();
      DeleteConfirmationController controller = loader.getController();
      pat = tableView.getSelectionModel().getSelectedItem();
      controller.SetIdPatient(pat);
            Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        
}
    
    
    
    // back and home Method
        @FXML
    private void GoToHomeView(ActionEvent event) throws IOException {
       Patient p;
         Stage stage;
        Parent root;
    stage = (Stage) updatebtn.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
      root = loader.load();
    Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        
}
  
     public void export() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tableView.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tableView.getColumns().get(j).getText());
        }

        for (int i = 0; i < tableView.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableView.getColumns().size(); j++) {
                if(tableView.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(tableView.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }

        FileOutputStream fileOut = new FileOutputStream("Exported.xls");
        workbook.write(fileOut);
        fileOut.close();
         nt .SuccessNotification("File Exporté avec Sucess", "File Exported Successfully");
         System.out.println("File exported");
        //Platform.exit();
    }
    
    
}
