/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.dao.AssignmentDao;
import com.example.dao.ProjectDao;
import com.example.dao.UserDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.model.UserBean;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hazzan
 */
public class LoginController extends HttpServlet {

    public void init() throws ServletException {
    }

    public void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, SQLException {

        String link = "";

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String userType = "";

        UserBean ub = new UserBean();
        ub.setUsername(username);
        ub.setPassword(password);

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        String val = (String) session.getAttribute("counter");
        int counter = 1;
        if (val != null) {
            counter = Integer.parseInt(val);
            counter++;
        }
        session.setAttribute("counter", String.valueOf(counter));

        if (UserDao.isValid(ub)) {
            link = "teacherView.jsp";
            userType = "teacher";
            ub = UserDao.getTeacherInfo((String) session.getAttribute("username"));
            session.setAttribute("assigns", AssignmentDao.getAllAssignmentsTeacher((String) session.getAttribute("username")));
            session.setAttribute("submissions", null);
        } else if (UserDao.isValidStudent(ub)) {
            link = "studentView.jsp";
            userType = "student";
            ub = UserDao.getStudentInfo((String) session.getAttribute("username"));
            session.setAttribute("year", ub.getYear());
            session.setAttribute("email", ub.getEmail());
            session.setAttribute("assigns", AssignmentDao.getAllAssignments());
            //session.setAttribute("submissions", ProjectDao.getSubmissions((String) session.getAttribute("username")));
        } else {
            link = "index.jsp";
        }
        session.setAttribute("password", ub.getPassword());
        session.setAttribute("fname", ub.getPassword());
        session.setAttribute("lname", ub.getPassword());
        session.setAttribute("userType", userType);
        session.setAttribute("currentAssign", null);
        RequestDispatcher view = request.getRequestDispatcher(link);
        view.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        UserBean ub = new UserBean();
        ub.setUsername("120301");
        ub.setPassword("Hazzan");
        System.out.println(UserDao.isValid(ub));

    }
}
