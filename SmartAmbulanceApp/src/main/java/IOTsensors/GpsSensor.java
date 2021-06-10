/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOTsensors;

import BdConnect.DbConnection;
import Models.Sensor;
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
public class GpsSensor {
    
    Connection con = null;
    public Statement stmt;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public List<String> getSensors() throws SQLException{
     con = DbConnection.getConnection();
                   stmt = con.createStatement();
        String query="select * from GpsSensor";
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
