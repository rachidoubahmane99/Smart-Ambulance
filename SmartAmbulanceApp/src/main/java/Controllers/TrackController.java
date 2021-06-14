/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import IOTsensors.GpsSensor;
import IOTsensors.HearBeatSensor;
import IOTsensors.TemperateurSensor;
import Include.Beep;
import Include.SoundAlert;
import MainConrollers.AmbulanceMainController;
import MainConrollers.PatientMainController;
import Models.AmbulanceTravel;
import Models.Patient;
import com.jfoenix.controls.JFXButton;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javax.sound.sampled.LineUnavailableException;


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
    @FXML
    private Label gpsCord;
    @FXML
    private Label heratbeat;
    @FXML
    private Label heartbSerie;
    @FXML
    private Label tempSerieLbl;
     @FXML
    private Label temperateurlbl;
     @FXML
    private HBox Heartbeathbx;
     @FXML
    private HBox TmpHbox;
    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton backBtn;
    

    PatientMainController p;
    AmbulanceMainController amc;
    //for tracking
    static String st ="";
    static  String st2="";
    static  String st3="";
    
    private static String HeartBeatSerie ="";
     private static String TempSerie ="";
      private static String GpsSerie ="";
    //ScheduledExecutorService scheduledExecutorService2;
    
     private int count = 0;
   // private final Text text = new Text(Integer.toString(count));

    private void incrementCount() {
        count++;
        nomlbl.setText(Integer.toString(count));
    }
    
    
    // end of test code
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //MapsWebView.getEngine().load("https://www.google.com/maps/dir/ENSIAS,+Avenue+Mohamed+Ben+Abdellah+Regragui,+Rabat/ENSIAS+Valley,+Rabat/@33.984304,-6.8705449,17z/data=!3m1!4b1!4m13!4m12!1m5!1m1!1s0xda76ce7f9462dd1:0x2e9c39cfa1d9e8d7!2m2!1d-6.8676019!2d33.9843118!1m5!1m1!1s0xda76cdd61f5cec7:0xd389a0cc6af3df45!2m2!1d-6.8690314!2d33.9838848");
    // longrunning operation runs on different thread
       
    
   
    }
    
    
    
    public void lunchThread(String Heartb ,String gps , String tmp) throws IOException{
        
         HearBeatSensor it1= new HearBeatSensor(Heartb);
        Thread th1 = new Thread(it1);
        th1.start(); 
        GpsSensor it2= new GpsSensor(gps);
        Thread th2 = new Thread(it2);
        th2.start(); 
        TemperateurSensor it3= new TemperateurSensor(tmp);
        Thread th3 = new Thread(it3);
        th3.start(); 
        
    
        BufferedReader br = null;
        BufferedReader br2 = null;
        BufferedReader br3 = null;
        String hote = "127.0.0.1" ;
        int port = 1000 ;
        int port2 = 2000 ;
        int port3 = 3000 ;
        Socket soc = null;
        Socket soc2 = null;
        Socket soc3 = null;
        
     
            soc = new Socket(hote, port);
            soc2 = new Socket(hote, port2);
            soc3 = new Socket(hote, port3);
            InputStream flux = soc.getInputStream();
            InputStream flux2 = soc2.getInputStream();
            InputStream flux3 = soc3.getInputStream();
            br = new BufferedReader(new InputStreamReader(flux));
            br2 = new BufferedReader(new InputStreamReader(flux2));
            br3 = new BufferedReader(new InputStreamReader(flux3));
            BufferedReader finalBr = br;
            BufferedReader finalBr2 = br2;
            BufferedReader finalBr3 = br3;
            
            try {
             
                     
                    //System.out.println(st2);
                
                    Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        try {
                            st= finalBr.readLine();
                            st2= finalBr2.readLine();
                            st3= finalBr3.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(TrackController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (Integer.parseInt(st)>100 || Integer.parseInt(st)<50 ) {
                            SoundAlert Sa = new SoundAlert();
                            try {
                                Sa.tone(20, 3000,7.0);
                            } catch (LineUnavailableException ex) {
                                Logger.getLogger(TrackController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Heartbeathbx.setStyle("-fx-background-color: #FFCC00;");
                        }
                        heratbeat.setText(st);
                        
                        // Test For Temperature : 
                        if (Integer.parseInt(st3)>100 || Integer.parseInt(st3)<30 ) {
                            SoundAlert Sa = new SoundAlert();
                            try {
                                Sa.tone(20, 3000,7.0);
                            } catch (LineUnavailableException ex) {
                                Logger.getLogger(TrackController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            TmpHbox.setStyle("-fx-background-color: #FFCC00;");
                        }
                        
                        //end of test alert
                        
                        
                        System.out.println(" HeartBeat : "+st2);
                        temperateurlbl.setText(st3);
                        
                        System.out.println(" temperateurlbl : "+st3);
                        MapsWebView.getEngine().load("https://www.google.com/maps/dir/"+st2);
                        gpsCord.setText(st2);
                        System.out.println(st2);
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
    
                    
                    
                    
            }catch(Exception e){
                e.printStackTrace();
            }
         
        
    }
    
    public void FillPatientData(AmbulanceTravel am) throws IOException{
        p = new PatientMainController();
        amc = new AmbulanceMainController();
        String FullNamePatient = am.getFullNamePatient();
        HeartBeatSerie = amc.getHeartBeatSensorById(am.getAmbulanceMatricule());
        GpsSerie = amc.getGpsSensorById(am.getAmbulanceMatricule());
        TempSerie = amc.getTemperateurSensorById(am.getAmbulanceMatricule());
        String[] parts = FullNamePatient.split(" ");
        String nom = parts[0];
        String prenom = parts[1];
        nomlbl.setText(FullNamePatient);
        maladielbl.setText(p.getMaladieById(p.getPatientByName(nom)));
        heartbSerie.setText(HeartBeatSerie);
        tempSerieLbl.setText(TempSerie);
       
        lunchThread(HeartBeatSerie,GpsSerie,TempSerie);
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AmbulancesTravel.fxml"));
      root = loader.load();
    Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        
}
}
  