/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joshuadabejero
 */
public class Database {
    Connection connection;
    void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/pis_17122020?user=root&password=");
            System.out.println("MYSQL: Connected Successfully");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
