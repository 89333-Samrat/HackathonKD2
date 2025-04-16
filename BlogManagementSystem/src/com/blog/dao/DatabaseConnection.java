package com.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/blog_system";
    private static final String USER = "root";
    private static final String PASSWORD = "yourpassword"; // Change to your MySQL password
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found");
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

/*GRANT ALL PRIVILEGES ON blog_system.* TO 'root'@'localhost';
FLUSH PRIVILEGES;*/

