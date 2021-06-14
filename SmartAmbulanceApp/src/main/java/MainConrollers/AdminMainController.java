/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainConrollers;

import BdConnect.DbConnection;
import Controllers.LoginController;
import Models.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import static javax.management.Query.value;

/**
 *
 * @author rachid dev
 */
public class AdminMainController {
        
    Connection con = null;
    public Statement stmt;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public boolean  addAdmin(Admin d) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
         String query="insert into Admin(nom, prenom, email, mdp, phone,profileImg,active) values ('"+d.getNom()+"','"+d.getPrenom()+"','"+d.getEmail()+"','"+d.getMdp()+"','"+d.getPhone()+"','"+d.getProfileImg()+"','"+d.getActive()+"')";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }
    public boolean  DesactiverAdmin(Admin d) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
         String query="UPDATE Admin SET active = 0 WHERE email= '"+d.getEmail()+"'";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }
    //change password method
    
     public boolean  ChangePass(String oldpass, String newpass) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
                 Admin  Adm = new Admin();
                 Adm = showAdminByEmail();
         String query="UPDATE Admin SET mdp = '"+newpass+"' WHERE email= '"+Adm.getEmail()+"'";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
       
    }
     
      //Update Profile method
    
     public boolean  UpdateProfile(Admin ad) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
                 Admin  Adm = new Admin();
                 Adm = showAdminByEmail();
                 if (ad.getProfileImg()!="") {
             String query="UPDATE Admin SET nom = '"+ad.getNom()+"', prenom = '"+ad.getPrenom()+"', email = '"+ad.getEmail()+"', phone = '"+ad.getPhone()+"', profileImg = '"+ad.getProfileImg()+"' WHERE email= '"+Adm.getEmail()+"'";
              int nbUpdated = stmt.executeUpdate(query);
            return nbUpdated!=0;
                 }else{
                     String query="UPDATE Admin SET nom = '"+ad.getNom()+"', prenom = '"+ad.getPrenom()+"', email = '"+ad.getEmail()+"', phone = '"+ad.getPhone()+"' WHERE email= '"+Adm.getEmail()+"'";
              int nbUpdated = stmt.executeUpdate(query);
            return nbUpdated!=0;
                 }
         
        
       
    }
    
    
    public int showadminId(String email, String mdp){
         int  adminId=0;
            int active =1;
            
         String sql = "SELECT * FROM Admin Where email = ? and mdp = ? and active = ?";
         try {
             con = DbConnection.getConnection();
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, mdp);
                 preparedStatement.setInt(3, active);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                   adminId= resultSet.getInt("idAdmin");
                }
                } catch (SQLException ex) {
             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
         }
         return adminId;
    }
    /*
      public Admin  showAdmin(Admin d) throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
        Preferences userPreferences = Preferences.userRoot();
        String info = userPreferences.get("adminEmail","No Email");
         String query="select * from Admin where  cne='"+cne+"' ";
        ResultSet rs=stmt.executeQuery(query);
        Etudiant e =null;
          if (rs.next()) {
              String login = rs.getString("login");
            String mdp = rs.getString("password");
            String cin = rs.getString("cin");
           String nom=rs.getString("nom");
           String prenom=rs.getString("prenom");
           String Cne=rs.getString("cne");
  
            e = new Etudiant(login, mdp, cin, nom, prenom,Cne);
          }
          return e;
    }
      */
      public Admin  showAdminByEmail() throws SQLException{
         con = DbConnection.getConnection();
                   stmt = con.createStatement();
        Preferences userPreferences = Preferences.userRoot();
        String info = userPreferences.get("adminEmail","No Email");
        //String info ="miola@gmail.com";
         String query="select * from Admin where  email='"+info+"' ";
        ResultSet rs=stmt.executeQuery(query);
        Admin e =null;
          if (rs.next()) {
              int id = rs.getInt("idAdmin");
              String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
           String phone=rs.getString("phone");
           String profile=rs.getString("profileImg");
  
            e = new Admin(id, nom, prenom, email, phone, profile);
          }
          return e;
    }
      
      
             //get Count
      public int CountRows() throws SQLException{
          int count = 0;
          try {
           con = DbConnection.getConnection();
            stmt = con.createStatement();
            
        resultSet =stmt.executeQuery("SELECT COUNT(*) FROM Admin");
              while (resultSet.next()) {
                  count = resultSet.getInt(1);
                  
              }
            } catch (SQLException ex) {
            System.out.println("Error");
        }
        
      return count;
      }
}
