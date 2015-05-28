/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.model.AssignmentBean;
import com.example.model.UserBean;
import com.example.util.DbUtil;
import java.io.IOException;
import static java.lang.Math.E;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserDao extends HttpServlet {

    /**
     * CHECKS IF USER IS VALID
     *
     * @param user
     * @return
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static boolean isValid(UserBean user) throws SQLException, IOException {
        boolean result = false;
        DbUtil db = new DbUtil();

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "SELECT * FROM teacher_account WHERE faculty_number = ? and password = ?");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result = true;
                //put code for traversing the resultset and set the fname and lname of UserBean
            }
            rs.close();
            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            System.out.println("Connection Problem.");
        }
        
        return result;
    }

    public static boolean isValidStudent(UserBean user) throws SQLException, IOException {
        boolean result = false;
        DbUtil db = new DbUtil();

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "SELECT * FROM stud_account WHERE stud_number = ? and password = ?");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result = true;
                //put code for traversing the resultset and set the fname and lname of UserBean
            }
            rs.close();
            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            System.out.println("Connection Problem.");
        }
        return result;
    }
    
    public static UserBean getTeacherInfo(String username) throws IOException{
        DbUtil db = new DbUtil();
        UserBean ub = null;
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "SELECT * FROM teacher_account WHERE faculty_number = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ub = new UserBean();
                ub.setUsername(username);
                ub.setPassword(rs.getString("password"));
                ub.setFname(rs.getString("fname"));
                ub.setLname(rs.getString("lname"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return ub;
    }
    
    public static UserBean getStudentInfo(String username) throws IOException{
        DbUtil db = new DbUtil();
        UserBean ub = null;
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "SELECT * FROM stud_account WHERE stud_number = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ub = new UserBean();
                ub.setUsername(username);
                ub.setPassword(rs.getString("password"));
                ub.setFname(rs.getString("fname"));
                ub.setLname(rs.getString("lname"));
                ub.setYear(rs.getInt("year"));
                ub.setEmail(rs.getString("email"));
                
                //out.println(rs.getInt("year"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return ub;
    }

    public static boolean checkAvailable(UserBean user) throws SQLException, IOException {
        boolean result = true;
        DbUtil db = new DbUtil();
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "SELECT * FROM stud_account WHERE stud_number = ?");
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }


    public static void addStudent(UserBean user) throws SQLException, IOException {
        DbUtil db = new DbUtil();
        if (UserDao.checkAvailable(user)) {
            try {
                PreparedStatement stmt = db.getConnection().prepareStatement(""
                        + "INSERT INTO stud_account (stud_number, password, fname, lname, year, email) VALUES (?,?,?,?,?,?)");
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getFname());
                stmt.setString(4, user.getLname());
                stmt.setInt(5, user.getYear());
                stmt.setString(6, user.getEmail());
                stmt.executeUpdate();
                stmt.close();
                db.disconnect();
            } catch (SQLException e) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        System.out.println("USER ALREADY REGISTERED!");
    }

    public static void main(String[] args) throws SQLException, IOException {
        //check for errors here
        UserDao.getStudentInfo("123");
    }
}
