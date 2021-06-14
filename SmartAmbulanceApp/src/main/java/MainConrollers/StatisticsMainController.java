/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainConrollers;

import BdConnect.DbConnection;
import Models.Statistique;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rachid dev
 */
public class StatisticsMainController {
     Connection con = null;
        public Statement stmt;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Statistique st ;
    
        
       
            
        
        
        // get Full Name by Id
      public Statistique getTablesCount() throws SQLException {
          AdminMainController ad = new AdminMainController();
          AmbulanceMainController amb = new AmbulanceMainController();
          AmbulanceTravelMainController ambT = new AmbulanceTravelMainController();
          PatientMainController pt = new PatientMainController();
            
        st = new Statistique(ad.CountRows(), amb.CountRows(),ambT.CountRows(),pt.CountRows());

        return st;
    }
    
}
