/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOTsensors;

import BdConnect.DbConnection;
import static IOTsensors.HearBeatSensor.st;
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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rachid dev
 */
public class TemperateurSensor implements Runnable{
    
    
    
    Connection con = null;
    public Statement stmt;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
     static String st ="";
    
      static String Serie;

    public TemperateurSensor(String serie){
        this.Serie=serie;
  
    }
     public TemperateurSensor(){
        
  
    }
     
    @Override
    public void run() {
        File file = new File("src/main/resources/TemperateurSensor/"+Serie);
        BufferedReader br = null;


        int port = 3000;
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
        String query="select * from TemperatureSensor";
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
