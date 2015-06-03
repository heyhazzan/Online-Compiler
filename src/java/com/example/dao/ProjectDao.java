/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.model.AssignmentBean;
import com.example.model.ProjectBean;
import com.example.util.DbUtil;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hazzan
 */
public class ProjectDao {

    public static boolean isDeadline(String deadline) throws ParseException {
        boolean result = true;
        if (deadline != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dl = format.parse(deadline);
            Date today = new Date();

            out.println("DEADLINE: " + deadline);
            out.println("FORMATED DEADLINE: " + dl);
            out.println("TODAYYYY DEADLINE: " + today);
            out.println("COMPARE RESULT: " + today.compareTo(dl));
            if (today.compareTo(dl) < 0) {
                result = false;
            } else {
                result = true;
            }   
        }
        return result;
    }

    public static void addSubmission(ProjectBean pb) throws SQLException, IOException {
        DbUtil db = new DbUtil();
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "INSERT INTO project (file, language, date, score, stud_number, assign_id) VALUES (?, ?, ?, NULL, ?, ?)");
            stmt.setString(1, pb.getFile());
            stmt.setString(2, pb.getLanguage());
            stmt.setString(3, pb.getDate());
            stmt.setString(4, pb.getStud_number());
            stmt.setInt(5, pb.getAssign_id());
            stmt.executeUpdate();

            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static List<ProjectBean> getSubmissions(String id) throws IOException {
        List<ProjectBean> submissions = new ArrayList();
        DbUtil db = new DbUtil();
        int assign_id = Integer.parseInt(id);
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "SELECT * FROM project WHERE assign_id = ?");
            stmt.setInt(1, assign_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProjectBean pb = new ProjectBean();
                pb.setFile(rs.getString("file"));
                pb.setLanguage(rs.getString("language"));
                pb.setDate(rs.getString("date"));
                pb.setScore(rs.getDouble("score"));
                pb.setStud_number(rs.getString("stud_number"));
                pb.setAssign_id(rs.getInt("assign_id"));

                submissions.add(pb);
            }
            rs.close();
            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return submissions;
    }

    public static ProjectBean getProjInfo(String assign_id, String stud_number) throws IOException {
        DbUtil db = new DbUtil();
        ProjectBean pb = null;
        try {

            PreparedStatement stmt = db.getConnection().prepareStatement(""
                    + "SELECT * FROM project WHERE assign_id = ? AND stud_number = ?");

            stmt.setString(1, assign_id);
            stmt.setString(2, stud_number);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pb = new ProjectBean();
                pb.setFile(rs.getString("file"));
                pb.setLanguage(rs.getString("language"));
                pb.setDate(rs.getString("date"));
                pb.setScore(rs.getDouble("score"));
                pb.setStud_number(rs.getString("stud_number"));
                pb.setAssign_id(rs.getInt("assign_id"));
            }
            rs.close();
            stmt.close();
            db.disconnect();
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return pb;
    }

    public static void main(String[] args) throws IOException, ParseException {
        //code tester
        String deadline = "1990-03-03";
        isDeadline(deadline);
    }
}
