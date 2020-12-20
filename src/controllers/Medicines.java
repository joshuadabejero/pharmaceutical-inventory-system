/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;
import models.Medicine;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author johntroidabejero
 */
public class Medicines {
    Database database = new Database();
    
    public String register(int medicineid, String name, String brand, int price, int quantity, int weight, String type, boolean steriod, String description, java.util.Date expiration, int medicationid) throws SQLException {
        database.connect();

        String result = null;
        String sql = "INSERT INTO medicines (medicineid, name, brand, price, quantity, weight, type, steriod, description, expiration, medicationid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, medicineid);
        statement.setString(2, name);
        statement.setString(3, brand);
        statement.setInt(4, price);
        statement.setInt(5, quantity);
        statement.setInt(6, weight);
        statement.setString(7, type);
        statement.setBoolean(8, steriod);
        statement.setString(9, description);
        java.sql.Date SQLDate = new java.sql.Date(expiration.getTime());
        statement.setDate(10, (java.sql.Date) SQLDate);
        statement.setInt(11, medicationid);
        
        int rowInserted = statement.executeUpdate();
        if (rowInserted > 0) {
            result = "Medicine was successfully registered!";
        }
        
        database.disconnect();
        
        return result;
    }

    public Medicine searchById(int medicineid) throws SQLException {
        Medicine medicine = new Medicine();
        database.connect();

        String sql = "SELECT medicineid, name, brand, price, quantity, weight, type, steriod, description, expiration, medicationid=? ";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, medicineid);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            medicine.medicineid = result.getInt("medicineid");
            medicine.name = result.getString("name");
            medicine.brand = result.getString("brand");
            medicine.quantity = result.getInt("quantity");
            medicine.weight = result.getInt("weight");
            medicine.type = result.getString("type");
            medicine.steriod = result.getBoolean("steriod");
            medicine.description = result.getString("description");
            medicine.expiration = result.getDate("expiration");
            medicine.medicationid = result.getInt("medicationid");
        }

        database.disconnect();

        return medicine;
    }

    public String updateById(int medicineid, String name, String brand, int price, int quantity, int weight, String type, boolean steriod, String description, java.util.Date expiration, int medicationid) throws SQLException {
        database.connect();
        
        String result = null;
        String sql = "UPDATE medicines SET medicineid=?, name=?, brand=?, price=?, quantity=?, weight=?, type=?, steriod=?, description=?, expiration=?, medicationid=?";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, medicineid);
        statement.setString(2, name);
        statement.setString(3, brand);
        statement.setInt(4, price);
        statement.setInt(5, quantity);
        statement.setInt(6, weight);
        statement.setString(7, type);
        statement.setBoolean(8, steriod);
        statement.setString(9, description);
        java.sql.Date SQLDate = new java.sql.Date(expiration.getTime());
        statement.setDate(10, (java.sql.Date) SQLDate);
        statement.setInt(11, medicationid);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            result = "An existing user was updated successfully!)";
        }

        database.disconnect();
        
        return result;
    }

    public String deleteById(int medicineid) throws SQLException {
        database.connect();
        
        String result = null;
        String sql = "DELETE FROM medicines WHERE medicineid=?";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, medicineid);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            result = "An employee was deleted successfully!";
        }
        
        database.disconnect();
        
        return result;
    }
}
