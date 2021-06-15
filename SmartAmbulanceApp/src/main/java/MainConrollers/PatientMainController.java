/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainConrollers;

import BdConnect.DbConnection;
import Controllers.AdminsController;
import Models.Admin;
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
public class PatientMainController {
    
    
     Connection con = null;
    public Statement stmt;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public boolean  addPatient(Patient p) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
         String query="insert into Patient(nom, prenom, maladie, MedicalNotes, Adress,phone,JoinDate) values ('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getMaladie()+"','"+p.getMedicalNotes()+"','"+p.getAdress()+"','"+p.getPhone()+"','"+p.getJoinDate()+"')";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }
     public boolean  EditPatient(Patient p) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
         String query="UPDATE Patient SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"', maladie = '"+p.getMaladie()+"', MedicalNotes = '"+p.getMedicalNotes()+"', Adress = '"+p.getAdress()+"', phone = '"+p.getPhone()+"', JoinDate = '"+p.getJoinDate()+"' WHERE idPatient= "+p.getIdPatient()+"";
         //String query="insert into Patient(nom, prenom, maladie, MedicalNotes, Adress,phone,JoinDate) values ('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getMaladie()+"','"+p.getMedicalNotes()+"','"+p.getAdress()+"','"+p.getPhone()+"','"+p.getJoinDate()+"')";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }
      public boolean  DeletePatient(int idPatient) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
         String query="Delete from Patient WHERE idPatient= "+idPatient+"";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }
      
      public int getPatientByName(String name) {

       int id =0;
        try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();
        resultSet =stmt.executeQuery("SELECT * FROM Patient where Nom ='"+name+"'");
        
            while (resultSet.next()) {
                id = resultSet.getInt("idPatient");
                

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return id;
    }
    
      
      // get Full Name by Id
      public String getNameById(int id) {

       String FullName="";
        try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();
        //String query="select * from Admin where nom like'"+searchInput+"' ";
        resultSet =stmt.executeQuery("SELECT * FROM Patient where idPatient ="+id+"");
        
            while (resultSet.next()) {
                FullName = resultSet.getString("nom")+" "+resultSet.getString("prenom");
                

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return FullName;
    }
      
      
      //get patient Maldie by Id
      // get Full Name by Id
      public String getMaladieById(int id) {

       String maladie="";
        try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();
        resultSet =stmt.executeQuery("SELECT * FROM Patient where idPatient ="+id+"");
        
            while (resultSet.next()) {
                maladie = resultSet.getString("maladie");
                

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return maladie;
    }
      
    
    public ObservableList<Patient> getAllPatients() {

        ObservableList<Patient> patients = FXCollections.observableArrayList();
        //String searchInput = searchTextField.getText();
        
        try {
           con = DbConnection.getConnection();
          
         
           //resultSet = executeSearchQuery(search);
                  // stmt = con.createStatement();
            //resultSet = stmt.executeQuery("SELECT IdAdmin, nom, prenom, email, phone, active FROM Admin");
            stmt = con.createStatement();
        //String query="select * from Admin where nom like'"+searchInput+"' ";
        resultSet =stmt.executeQuery("SELECT * FROM Patient ");
            while (resultSet.next()) {
                int id = resultSet.getInt("idPatient");
                String prenom = resultSet.getString("prenom");
                String nom = resultSet.getString("nom");
                String maladie = resultSet.getString("maladie");
                String medicalNotes = resultSet.getString("MedicalNotes");
                 String Adress = resultSet.getString("Adress");
                String phone = resultSet.getString("phone");
                Date joinDate = resultSet.getDate("JoinDate");

                System.out.println("ID: " + id + "  prenom: " + prenom + " nom: " + nom );
                patients.add(new Patient(id, nom, prenom, maladie, medicalNotes, Adress,phone,joinDate));

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return patients;
    }
    
    
    public ObservableList<Patient> getPatients(String search) {

        ObservableList<Patient> patients = FXCollections.observableArrayList();
        //String searchInput = searchTextField.getText();
          if (search!="") {
            
        }
        try {
           con = DbConnection.getConnection();
          
         
           resultSet = executeSearchQuery(search);
                  // stmt = con.createStatement();
            //resultSet = stmt.executeQuery("SELECT IdAdmin, nom, prenom, email, phone, active FROM Admin");

            while (resultSet.next()) {
                int id = resultSet.getInt("idPatient");
                String prenom = resultSet.getString("prenom");
                String nom = resultSet.getString("nom");
                String maladie = resultSet.getString("maladie");
                String medicalNotes = resultSet.getString("MedicalNotes");
                 String Adress = resultSet.getString("Adress");
                String phone = resultSet.getString("phone");
                Date joinDate = resultSet.getDate("JoinDate");

                System.out.println("ID: " + id + "  prenom: " + prenom + " nom: " + nom );
                patients.add(new Patient(id, nom, prenom, maladie, medicalNotes, Adress,phone,joinDate));

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return patients;
    }
    
    
      public ResultSet executeSearchQuery(String searchInput) throws SQLException {
        
        con = DbConnection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * "
                + "FROM Patient WHERE idPatient LIKE ? ESCAPE '!' OR prenom "
                + "LIKE ? ESCAPE '!' OR nom LIKE ? ESCAPE '!' OR phone LIKE ? "
                + "ESCAPE '!' OR maladie LIKE ? ESCAPE '!' OR Adress LIKE ? ESCAPE '!'");
        String search = searchInput
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
        
        preparedStatement.setInt(1,Integer.parseInt(search));
        preparedStatement.setString(2, "%" + search + "%");
        preparedStatement.setString(3, "%" + search + "%");
        preparedStatement.setString(4, "%" + search + "%");
        preparedStatement.setString(5, "%" + search + "%");
        preparedStatement.setString(6, "%" + search + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

      
       // get All Patients Full Name Liste
        public List<String> getPatientsList() throws SQLException{
     con = DbConnection.getConnection();
                   stmt = con.createStatement();
        String query="SELECT * FROM Patient ";
        ResultSet rs=stmt.executeQuery(query);
      List<String> patients = new ArrayList<>();
        
        
        while (rs.next()) {
           String NomPatient=rs.getString("nom");
             String PrenomPatient=rs.getString("prenom");
         patients.add(NomPatient+" "+PrenomPatient);
       }

       return patients;
    }
        
        
          //get Count
      public int CountRows() throws SQLException{
          int count =0;
          try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();

        resultSet =stmt.executeQuery("SELECT COUNT(*) FROM Patient");
        while (resultSet.next()) {
                  count = resultSet.getInt(1);
                  
              }
            } catch (SQLException ex) {
            System.out.println("Error");
        }
      return count;
      }
}
