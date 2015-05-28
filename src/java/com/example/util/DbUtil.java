/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



/**
 *
 * @author Hazzan
 */
public class DbUtil {

    private Connection connection = null;

    public Connection getConnection() throws SQLException, IOException {
        if (connection != null) {
            return connection;
        } else {
            try {
                Properties prop = new Properties();
                InputStream input = 
                        DbUtil.class.getClassLoader().getResourceAsStream("./db.properties");
                prop.load(input);
                
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String pass = prop.getProperty("pass");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, pass);
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("SQL Error!");
                e.printStackTrace();
            }
            return connection;
        }
    }

    public boolean disconnect() {
        try {
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
