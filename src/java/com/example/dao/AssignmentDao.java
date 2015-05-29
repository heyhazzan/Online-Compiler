/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.model.AssignmentBean;
import com.example.util.DbUtil;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Hazzan
 */
public class AssignmentDao extends HttpServlet {

    public static void addAssignment(AssignmentBean assign) throws IOException {
        DbUtil db = new DbUtil();
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "INSERT INTO assignment (name, instruction, deadline, inputs, output, faculty_number) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, assign.getName());
            stmt.setString(2, assign.getInstruction());
            stmt.setString(3, assign.getDeadline());
            stmt.setString(4, assign.getInputs());
            stmt.setString(5, assign.getOutput());
            stmt.setString(6, assign.getFaculty_number());
            stmt.executeUpdate();
            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public static void updateAssignment(AssignmentBean ab) throws SQLException, IOException {
        DbUtil db = new DbUtil();
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "UPDATE assignment SET name = ?, instruction=?, deadline=? WHERE assign_id=?");

            stmt.setString(1, ab.getName());
            stmt.setString(2, ab.getInstruction());
            stmt.setString(3, ab.getDeadline());
            stmt.setInt(4, ab.getAssign_id());
            
            System.out.println("NEW NAME!"+ab.getName());
            System.out.println("NEW INS!"+ab.getInstruction());

            stmt.executeUpdate();
            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static List<AssignmentBean> getAllAssignmentsTeacher(String faculty_number) throws IOException {
        List<AssignmentBean> assigns = new ArrayList();
        DbUtil db = new DbUtil();
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "SELECT * FROM assignment WHERE faculty_number = ?");
            stmt.setString(1, faculty_number);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AssignmentBean ab = new AssignmentBean();

                ab.setAssign_id(rs.getInt("assign_id"));
                ab.setName(rs.getString("name"));
                ab.setInstruction(rs.getString("instruction"));
                ab.setDeadline(rs.getString("deadline"));
                ab.setInputs(rs.getString("inputs"));
                ab.setOutput(rs.getString("output"));
                ab.setFaculty_number(rs.getString("faculty_number"));

                assigns.add(ab);
            }
            rs.close();
            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return assigns;
    }

    public static List<AssignmentBean> getAllAssignments() throws IOException {
        List<AssignmentBean> assigns = new ArrayList();
        DbUtil db = new DbUtil();
        try {
            Statement stmt = db.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM assignment");
            while (rs.next()) {
                AssignmentBean ab = new AssignmentBean();

                ab.setAssign_id(rs.getInt("assign_id"));
                ab.setName(rs.getString("name"));
                ab.setInstruction(rs.getString("instruction"));
                ab.setDeadline(rs.getString("deadline"));
                ab.setInputs(rs.getString("inputs"));
                ab.setOutput(rs.getString("output"));
                ab.setFaculty_number(rs.getString("faculty_number"));

                assigns.add(ab);
            }
            rs.close();
            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return assigns;
    }

    public static AssignmentBean getAssignInfo(String assign_id) throws IOException {
        DbUtil db = new DbUtil();
        AssignmentBean ab = null;
        try {

            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "SELECT * FROM assignment WHERE assign_id = ?");

            stmt.setString(1, assign_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ab = new AssignmentBean();
                ab.setAssign_id(rs.getInt("assign_id"));
                ab.setName(rs.getString("name"));
                ab.setInstruction(rs.getString("instruction"));
                ab.setDeadline(rs.getString("deadline"));
                ab.setInputs(rs.getString("inputs"));
                ab.setOutput(rs.getString("output"));
                ab.setFaculty_number(rs.getString("faculty_number"));
            }
            rs.close();
            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return ab;
    }

}
