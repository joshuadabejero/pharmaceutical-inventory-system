/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import models.Employee;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
/**
 *
 * @author joshuadabejero
 */
public class Employees {
    Database database = new Database();
    
    public String register(String name, String gender, java.util.Date birthday, String address, String cnumber, String email, String username, String password) throws SQLException {
        database.connect();
        
        String result = null;
        String sql = "INSERT INTO employees (name, gender, birthday, address, cnumber, email, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, gender);
        java.sql.Date SQLDate = new java.sql.Date(birthday.getTime());
        statement.setDate(3, (java.sql.Date) SQLDate);
        statement.setString(4, address);
        statement.setString(5, cnumber);
        statement.setString(6, email);
        statement.setString(7, username);
        statement.setString(8, password);
        
        int rowInserted = statement.executeUpdate();
        if (rowInserted > 0) {
            result = "Employee was successfully registered!";
        }
        
        database.disconnect();
        
        return result;
    }
    
    public Employee searchById(int employeeid) throws SQLException {
        Employee employee = new Employee();
        database.connect();
        
        String sql = "SELECT name, gender, birthday, address, cnumber, email, username, password, FROM employees WHERE employeeid=?";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, employeeid);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            employee.name = result.getString("name");
            employee.gender = result.getString("gender");
            employee.birthday = result.getDate("birthday");
            employee.address = result.getString("address");
            employee.cnunmber = result.getString("cnumber");
            employee.email = result.getString("email");
            employee.username = result.getString("username");
            employee.username = result.getString("password");
        }
        
        database.disconnect();
        
        return employee;
    }
    
    public String updateById(int employeeid, String name, String gender, java.util.Date birthday, String address, String cnumber, String email, String username, String password) throws SQLException {
        database.connect();
        
        String result = null;
        String sql = "UPDATE employees SET name=?, gender=?, birthday=?, address=?, cnumber=?, email=?, username=?, password=? WHERE username=?";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, gender);
        java.sql.Date SQLDate = new java.sql.Date(birthday.getTime());
        statement.setDate(3, (java.sql.Date) SQLDate);
        statement.setString(4, address);
        statement.setString(5, cnumber);
        statement.setString(6, email);
        statement.setString(7, username);
        statement.setString(8, password);
        statement.setInt(9, employeeid);
        
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            result = "An existing user was updated successfully!)";
        }
        
        database.disconnect();
        
        return result;
    }
    
    public String deleteById(int employeeid) throws SQLException {
        database.connect();
        
        String result = null;
        String sql = "DELETE FROM employees WHERE employeeid=?";
        PreparedStatement statement = database.connection.prepareStatement(sql);
        statement.setInt(1, employeeid);
        
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            result = "An employee was deleted successfully!";
        }
        
        database.disconnect();
        
        return result;
    }
}
