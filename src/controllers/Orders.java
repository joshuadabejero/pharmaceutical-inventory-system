/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;
import models.Order;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author johntroidabejero
 */
public class Orders {
    Database database = new Database();
    
    public String register(int orderid, java.util.Date receipt, int medicineid, int quantity, int cost, int customerid, int employee) throws SQLException {
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

    public Order searchById(int orderid) throws SQLException {
        Order order = new Order();
        database.connect();
        
        String sql = "SELECT orderid, receipt, medicineid, quantity, cost, customerid, employee, FROM employees WHERE orderid=?";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, orderid);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            order.orderid = result.getInt("orderid");
            order.receipt = result.getDate("receipt");
            order.medicineid = result.getInt("medicineid");
            order.quantity = result.getInt("quantity");
            order.cost = result.getInt("cost");
            order.customerid = result.getInt("customerid");
            order.employee = result.getInt("employee");
        }

        database.disconnect();
        
        return order;
}
    
public String updateById(int orderid, java.util.Date receipt, int medicineid, int quantity, int cost, int customerid, int employee) throws SQLException {
        database.connect();
        
        String result = null;
        String sql = "UPDATE order SET orderid=?, receipt=?, medicineid=?, quantity=?, cost=?, customerid=?, employee=? WHERE username=?";
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

    