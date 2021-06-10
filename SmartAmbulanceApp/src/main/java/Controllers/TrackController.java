/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import MainConrollers.PatientMainController;
import Models.AmbulanceTravel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

/**
 *
 * @author rachid dev
 */
public class TrackController implements Initializable {
    
    @FXML
    public WebView MapsWebView;
    @FXML
    private Label nomlbl;
    @FXML
    private Label maladielbl;

    PatientMainController p;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //MapsWebView.getEngine().load("https://www.google.com/maps/dir/ENSIAS,+Avenue+Mohamed+Ben+Abdellah+Regragui,+Rabat/ENSIAS+Valley,+Rabat/@33.984304,-6.8705449,17z/data=!3m1!4b1!4m13!4m12!1m5!1m1!1s0xda76ce7f9462dd1:0x2e9c39cfa1d9e8d7!2m2!1d-6.8676019!2d33.9843118!1m5!1m1!1s0xda76cdd61f5cec7:0xd389a0cc6af3df45!2m2!1d-6.8690314!2d33.9838848");
    }
    
    public void FillPatientData(AmbulanceTravel am){
        p = new PatientMainController();
      
        String FullNamePatient = am.getFullNamePatient();
        String[] parts = FullNamePatient.split(" ");
        String nom = parts[0];
        String prenom = parts[1];
        nomlbl.setText(FullNamePatient);
        maladielbl.setText(p.getMaladieById( p.getPatientByName(nom)));
    }
}
