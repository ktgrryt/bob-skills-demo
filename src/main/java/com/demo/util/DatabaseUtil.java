package com.demo.util;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Singleton
@Startup
public class DatabaseUtil {
    
    private static final String DB_URL = "jdbc:derby:memory:userdb;create=true";
    
    @PostConstruct
    public void init() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            
            String createTable = "CREATE TABLE users (" +
                    "id BIGINT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255), " +
                    "age INT)";
            
            stmt.execute(createTable);
            
            String insertData = "INSERT INTO users (id, name, email, age) VALUES " +
                    "(1, 'John Doe', 'john@example.com', 30), " +
                    "(2, 'Jane Smith', 'jane@example.com', 25)";
            
            stmt.execute(insertData);
            
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Made with Bob
