/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.dao.AssignmentDao;
import com.example.dao.ProjectDao;
import com.example.model.AssignmentBean;
import com.example.model.ProjectBean;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hazzan
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        session.setAttribute("currentAssign", request.getParameter("assign_id"));
        
        if (action.equals("view")) {
            session.setAttribute("assignInfo", AssignmentDao.getAssignInfo((String) request.getSession().getAttribute("currentAssign")));
            AssignmentBean ab = (AssignmentBean) request.getSession().getAttribute("assignInfo");
            out.println("ACTIIIOOONNN" + action);
            out.println(ab.getFaculty_number());
            if (session.getAttribute("userType").equals("teacher")) {
                response.sendRedirect("teacherView.jsp");
            } else {
                session.setAttribute("subsInfo", ProjectDao.getProjInfo((String) session.getAttribute("currentAssign"), (String) session.getAttribute("username")));
                response.sendRedirect("studentView.jsp");
            }
        } else if (action.equals("viewsubmissions")) {
            session.setAttribute("submissions", ProjectDao.getSubmissions((String) session.getAttribute("currentAssign")));
            List<ProjectBean> pb = (List<ProjectBean>) session.getAttribute("submissions");
            response.sendRedirect("teacherView.jsp");
        }else if(action.equals("editassign")){
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String edit = request.getParameter("edit");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        out.println("ACTIIIOOOONNN:" + action);
        out.println("EDIT VA:LUUUEEE:" + edit);
        
        HttpSession session = request.getSession();
        if (action.equals("addassign")) {

            AssignmentBean ab = new AssignmentBean();
            String name = request.getParameter("name");
            String instruction = request.getParameter("instruction");
            String date = request.getParameter("deadline");
            String inputs = request.getParameter("inputs");
            String output = request.getParameter("output");
            String faculty_number = (String) session.getAttribute("username");

            ab.setName(name);
            ab.setInstruction(instruction);
            ab.setDeadline(date);
            ab.setInputs(inputs);
            ab.setOutput(output);
            ab.setFaculty_number(faculty_number);

            AssignmentDao.addAssignment(ab);
            session.setAttribute("currentAssign", null);
            
            response.sendRedirect("teacherView.jsp");
        } else if (action.equals("submitassign")) {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            ProjectBean pb = new ProjectBean();

            String file = request.getParameter("file");
            String language = request.getParameter("language");
            String formatedDate = dateFormat.format(date);
            String stud_num = (String) session.getAttribute("username");
            int assign_id = Integer.parseInt((String) session.getAttribute("currentAssign"));

            pb.setFile(file);
            pb.setLanguage(language);
            pb.setDate(formatedDate);
            pb.setStud_number(stud_num);
            pb.setAssign_id(assign_id);

            try {
                ProjectDao.addSubmission(pb);
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("currentAssign", null);
            response.sendRedirect("studentView.jsp");

        }else if(action.equals("editassign")){//Start Edit Query here
            AssignmentBean ab = new AssignmentBean();
            String name = request.getParameter("name");
            String ins = request.getParameter("instruction");
            String dead = request.getParameter("deadline");
            String assign_id = request.getParameter("assign_id");
            
            
            ab.setName(name);
            ab.setInstruction(ins);
            ab.setDeadline(dead);
            ab.setAssign_id(Integer.parseInt(assign_id));
            
            try {
                AssignmentDao.updateAssignment(ab);
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("assigns", AssignmentDao.getAllAssignmentsTeacher((String) session.getAttribute("username")));
            session.setAttribute("assignInfo", AssignmentDao.getAssignInfo((String) request.getSession().getAttribute("currentAssign")));
            response.sendRedirect("teacherView.jsp");
        }else if(action.equals("scoring")){
            String button = request.getParameter("button");
            if(button.equals("Submit")){
            out.println(button);
            }else if (button.equals("Back")){
            out.println(button);//FINISH THIS CODE!!!
            }
            
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
