/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainConrollers;

import BdConnect.DbConnection;
import Models.Ambulance;
import Models.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rachid dev
 */
public class AmbulanceMainController {
    
    Connection con = null;
    public Statement stmt;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public boolean  addAmbulance(Ambulance am) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
         String query="insert into Ambulance(matricule,NomChafeur,GpsSensor,Temperateur,HearBeat) values ('"+am.getMatricule()+"','"+am.getNomChafeur()+"','"+am.getGpsSensor()+"','"+am.getTemperateur()+"','"+am.getHearBeat()+"')";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }
     public boolean  EditAmbulance(Ambulance am) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
         String query="UPDATE Ambulance SET  NomChafeur = '"+am.getNomChafeur()+"', GpsSensor = '"+am.getGpsSensor()+"', Temperateur = '"+am.getTemperateur()+"', HearBeat = '"+am.getHearBeat()+"', etat = '"+am.getEtat()+"' WHERE matricule = '"+am.getMatricule()+"'";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }
        public boolean  DeleteAmbulance(Ambulance am) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
         String query="Delete From Ambulance WHERE matricule = '"+am.getMatricule()+"'";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }

        public ObservableList<Ambulance> getAllAmbulances() {

        ObservableList<Ambulance> ambulances = FXCollections.observableArrayList();
        //String searchInput = searchTextField.getText();
        
        try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();
        resultSet =stmt.executeQuery("SELECT * FROM Ambulance ");
            while (resultSet.next()) {
                String matricule = resultSet.getString("matricule");
                String NomChaufeur = resultSet.getString("NomChafeur");
                String GpsSensor = resultSet.getString("GpsSensor");
                String Temperateur = resultSet.getString("Temperateur");
                String HearBeat = resultSet.getString("HearBeat");
                boolean etat = resultSet.getBoolean("etat");
                

                System.out.println("ID: " + matricule + "  nom chaufeur: " + NomChaufeur );
                ambulances.add(new Ambulance(matricule,NomChaufeur,GpsSensor,Temperateur,HearBeat,etat));

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return ambulances;
    }
        
        
    
        
        // get All ambulances Liste
        public List<String> getAmbulancesList() throws SQLException{
     con = DbConnection.getConnection();
                   stmt = con.createStatement();
        String query="SELECT * FROM Ambulance ";
        ResultSet rs=stmt.executeQuery(query);
      List<String> ambulances = new ArrayList<>();
        
        
        while (rs.next()) {
           String ambulanceMatricule=rs.getString("matricule");
         ambulances.add(ambulanceMatricule);
       }

       return ambulances;
    }
    
        
        // get HeatBeat Sensor Serie by Id
      public String getHeartBeatSensorById(String matricule) {

       String SensorSerie="";
        try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();
        //String query="select * from Admin where nom like'"+searchInput+"' ";
        resultSet =stmt.executeQuery("SELECT * FROM Ambulance where matricule ='"+matricule+"'");
        
            while (resultSet.next()) {
                SensorSerie = resultSet.getString("HearBeat");
                

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return SensorSerie;
    }
     
      
       // get Temperateur Sensor Serie by Id
      public String getTemperateurSensorById(String matricule) {

       String SensorSerie="";
        try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();
        //String query="select * from Admin where nom like'"+searchInput+"' ";
        resultSet =stmt.executeQuery("SELECT * FROM Ambulance where matricule ='"+matricule+"'");
        
            while (resultSet.next()) {
                SensorSerie = resultSet.getString("Temperateur");
                

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return SensorSerie;
    }
     
      // get Gps Sensor Serie by Id
      public String getGpsSensorById(String matricule) {

       String SensorSerie="";
        try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();
        //String query="select * from Admin where nom like'"+searchInput+"' ";
        resultSet =stmt.executeQuery("SELECT * FROM Ambulance where matricule ='"+matricule+"'");
        
            while (resultSet.next()) {
                SensorSerie = resultSet.getString("GpsSensor");
                

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return SensorSerie;
    }
      
      
      //get Count
      public int CountRows() throws SQLException{
          int count=0;
          try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();

        resultSet =stmt.executeQuery("SELECT COUNT(*) FROM Ambulance");
        while (resultSet.next()) {
                  count = resultSet.getInt(1);
                  
              }
            } catch (SQLException ex) {
            System.out.println("Error");
        }
      return count;
      }
      
      
}
