/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainConrollers;

import BdConnect.DbConnection;
import Models.Ambulance;
import Models.AmbulanceTravel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rachid dev
 */
public class AmbulanceTravelMainController {
    Connection con = null;
    public Statement stmt;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    PatientMainController pmc;
    
    public boolean  addAmbulanceTravel(AmbulanceTravel am) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
         String query="insert into AmbulanceTravel(idAmbulance,idPatient,adress,travelDate,done) values ('"+am.getAmbulanceMatricule()+"',"+am.getIdPatient()+",'"+am.getAdressPatient()+"','"+am.getTravelDate()+"',"+am.getIsDone()+")";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }
    
    public boolean  deleteAmbulanceTravel(int idTravel) throws SQLException{
            con = DbConnection.getConnection();
                   stmt = con.createStatement();
                   String query="Delete from Patient WHERE idTravel= "+idTravel+"";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
    }
    
    
    public ObservableList<AmbulanceTravel> getAllAmbulancesTravel() {

        ObservableList<AmbulanceTravel> ambulancesTravel = FXCollections.observableArrayList();
        //String searchInput = searchTextField.getText();
        
        try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();
        resultSet =stmt.executeQuery("SELECT * FROM AmbulanceTravel ");
        pmc = new PatientMainController();
            while (resultSet.next()) {          
                int idTravel =resultSet.getInt("idTravel");
                String idAmbulance = resultSet.getString("idAmbulance");
                int idPatient = resultSet.getInt("idPatient");
                Date travelDate = resultSet.getDate("travelDate");
                String adress = resultSet.getString("adress");
                boolean done = resultSet.getBoolean("done");
                
                String fullName = pmc.getNameById(idPatient);
                //System.out.println(fullName);
                //System.out.println("ID: " + id_ambulance + "  id patient: " + id_patient + "  Date: " + TravelDate +" etat : " +etat );
                ambulancesTravel.add(new AmbulanceTravel(idTravel, idAmbulance, fullName, adress, travelDate, done));
                             
            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return ambulancesTravel;
    }
    
    
       //get Count
      public int CountRows() throws SQLException{
          int count =0;
          try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();

        resultSet =stmt.executeQuery("SELECT COUNT(*) FROM AmbulanceTravel");
        while (resultSet.next()) {
                  count = resultSet.getInt(1);
                  
              }
            } catch (SQLException ex) {
            System.out.println("Error");
        }
      return count;
      }
    
      public ArrayList<Integer> getCountTravels(){
          
          ArrayList<Integer> arrayCount = new ArrayList<>();
          Date datetravel = null;
          try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();

        resultSet =stmt.executeQuery("SELECT travelDate FROM AmbulanceTravel");
        while (resultSet.next()) {
                  datetravel = resultSet.getDate(1);
                  arrayCount.add(getTravelCountByDate(datetravel));
                  
                  
              }
            } catch (SQLException ex) {
            System.out.println("Error");
        }
             return arrayCount;
      }
      
      
      public int getTravelCountByDate(Date date){
          int count =0;
          try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();

        resultSet =stmt.executeQuery("SELECT COUNT(*) FROM AmbulanceTravel  WHERE travelDate = '"+date+"'");
        while (resultSet.next()) {
                  count = resultSet.getInt(1);
                  
              }
            } catch (SQLException ex) {
            System.out.println("Error");
        }
             return count;
      }
      
      public ArrayList<Date> getAllTravelDates(){
          
          ArrayList<Date> arrayDates = new ArrayList<>();
          Date datetravel = null;
          try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();

        resultSet =stmt.executeQuery("SELECT travelDate FROM AmbulanceTravel");
        while (resultSet.next()) {
                  datetravel = resultSet.getDate(1);
                  arrayDates.add(datetravel);
                  
                  
              }
            } catch (SQLException ex) {
            System.out.println("Error");
        }
             return arrayDates;
      }
      
    
}
