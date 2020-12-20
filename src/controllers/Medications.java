/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;
import models.Orders;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
/**
 *
 * @author johntroidabejero
 */
public class Medications {
    Database database = new Database();
    
    public String register(int orderid, java.util.Date receipt, int medicineid, int quatinty, int cost, int customerid, int employee) throws SQLException {
        database.connect();

        String result = null;
        String sql = "INSERT INTO orders (orderid, receipt, medicineid, quantity, cost, customerid, employee) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, orderid);
        java.sql.Date SQLDate = new java.sql.Date(receipt.getTime());
        statement.setDate(2, (java.sql.Date) SQLDate);
        statement.setInt(3, medicineid);
        statement.setInt(4, quantity);
        statement.setInt(4, cost);
        statement.setInt(5, customerid);
        statement.setInt(6, employee);

        int rowInserted = statement.executeUpdate();
        if (rowInserted > 0) {
            result = "Order was successfully registered!";
        }
        
        database.disconnect();
        
        return result;
    }

    public Orders searchById(int employeeid) throws SQLException {
        Orders order = new Orders();
        database.connect();
        
        String sql = "SELECT orderid, receipt, medicineid, quatinty, cost, customerid, employee, FROM employees WHERE orderid=?";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, orderid);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            employee.orderid = result.getInt("orderid");
            employee.receipt = result.getDate("receipt");
            employee.medicineid = result.getInt("medicineid");
            employee.quatinty = result.getInt("quantity");
            employee.cost = result.getInt("cost");
            employee.customerid = result.getInt("customerid");
            employee.employee = result.getInt("employee");
        }

        database.disconnect();
        
        return employee;
}
    
public String updateById(int orderid, java.util.Date receipt, int medicineid, int quatinty, int cost, int customerid, int employee) throws SQLException {
        database.connect();
        
        String result = null;
        String sql = "UPDATE order SET orderid=?, receipt=?, medicineid=?, quatinty=?, cost=?, customerid=?, employee=? WHERE username=?;"
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, orderid);
        java.sql.Date SQLDate = new java.sql.Date(receipt.getTime());
        statement.setDate(2, (java.sql.Date) SQLDate);
        statement.setInt(3, medicineid);
        statement.setInt(4, quantity);
        statement.setInt(5, cost);
        statement.setInt(6, customerid);
        statement.setInt(7, employee);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            result = "An existing user was updated successfully!)";
        }
        
        database.disconnect();
        
        return result;
}

public String deleteById(int orderid) throws SQLException {
        database.connect();

        String result = null;
        String sql = "DELETE FROM employees WHERE orderid=?";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, orderid);
        
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            result = "An order was deleted successfully!";
        }
        
        database.disconnect();
        
        return result;
    }
}

    