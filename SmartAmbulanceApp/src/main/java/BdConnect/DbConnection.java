/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BdConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rachid dev
 */
public class DbConnection {
     
    public static final String USERNAME = "XFUdwTJBF2";
    public static final String PASSWORD = "8k74KNK3no";
    public static final String CONN = "jdbc:mysql://remotemysql.com:3306/XFUdwTJBF2";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN,USERNAME,PASSWORD);
    }
}
