/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOTsensors;

import BdConnect.DbConnection;
import static IOTsensors.GpsSensor.st;
import Models.Patient;
import Models.Sensor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rachid dev
 */
public class HearBeatSensor implements Runnable{
    
    Connection con = null;
    public Statement stmt;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
     static String st ="";
    static String Serie;

    public HearBeatSensor(String serie){
        this.Serie=serie;
  
    }
      public HearBeatSensor(){
      
    }
    
    public String getSerie(){
        return this.Serie;
    }
    public void setSerie(String serie){
         this.Serie=serie;
    }
     
     
    @Override
    public void run() {
        //seriep = this.getSerie();
        System.out.println("src/main/resources/HearBeatSensors/"+Serie);
        File file = new File("src/main/resources/HearBeatSensors/"+Serie);
         //System.out.println(seriep);
        BufferedReader br = null;


        int port = 1000;
        ServerSocket sersoc = null;
        try {
            br = new BufferedReader(new FileReader(file));
            sersoc = new ServerSocket(port);

            Socket soc = sersoc.accept();
            OutputStream flux = soc.getOutputStream() ;
            OutputStreamWriter sortie = new OutputStreamWriter (flux) ;
            while (true) {
                st = br.readLine();
                sortie.write(st+"\n") ;
                sortie.flush(); // pour forcer l'envoi de la ligne
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public List<String> getSensors() throws SQLException{
     con = DbConnection.getConnection();
                   stmt = con.createStatement();
        String query="select * from HearbeatSensor";
        ResultSet rs=stmt.executeQuery(query);
      List<String> sensors = new ArrayList<>();
        //Arr<Sensor> sensors= new   LinkedList<Sensor> ();
        
        while (rs.next()) {
           String sensorSerie=rs.getString("SensorSerie");
          
           Sensor s = new Sensor(sensorSerie);
           sensors.add(s.getSensorSerie());
       }

       return sensors;
    }
    
}
