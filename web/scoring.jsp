<%-- 
Document   /* global id */

: teacherView
Created on : May 17, 2015, 8:41:50 PM
Author     : Hazzan
--%>
<%@page import="com.example.dao.ProjectDao"%>
<%
    response.setHeader("Cache-Control", "no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    if (session.getAttribute("username") == null) {
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comparison</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <script src="js/bootstrap.js"></script>
        <style>
            .maincontent{
            }
            .white{
                border-radius: 10px;
                display: block;
                position: absolute;
                top: 3%;
                width: 40%;
                height: 90%;
                background-color: white;
                padding:10px;
                z-index:1002;
                overflow: auto;
                opacity: .7;
            }
            .teacher{
                right: 1.5%;
            }
            .student{
                left: 1.5%;
            }
            .scoring{
                padding:3px;
                width: 12.5%;
                height: 32%;
                top: 62.9%;
                left: 43.5%;
            }
        </style>
    </head>

    <body background = "css/img/viewsBG.jpg">
        <div class = "maincontent">
            <div class="white teacher" align = "center">
                as
            </div>
            <div class = "white scoring" align = 'center'>
                <form method="POST" action="UserController">
                    <hr><input type = "text" name = "score" placeholder = "Score" style = "width:90"/><hr>
                    <input type = "hidden" name = "action" value = "scoring"/>
                    <input class = "btn btn-sm btn-primary" name = "button" type = "submit" value = "Submit"/><br><br>
                    <input class = "btn btn-sm btn-primary" name = "button" type = "submit" value = "Back"/>
                    </form>
                
            </div>

            <div class="white student" align = "center">
                hellol
            </div>
        </div>
    </body>
</html>
<%
    } else {
        response.sendRedirect("index.jsp");
    }
%>