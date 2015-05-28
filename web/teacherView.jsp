<%-- 
Document   /* global id */

: teacherView
Created on : May 17, 2015, 8:41:50 PM
Author     : Hazzan
--%>
<%
    response.setHeader("Cache-Control", "no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    if (session.getAttribute("username") != null) {
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher View</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <script src="js/bootstrap.js"></script>
        <style>
            .maincontent{}
            .white{
                border-radius: 10px;
                display: block;
                position: absolute;
                top: 3%;
                left: 1.5%;
                width: 93.7%;
                height: 95%;
                background-color: white;
                padding-left: 20px;
                padding-right: 20px;
                z-index:1002;
                overflow: auto;
                opacity: .5;
            }
            .white-table{
                top:17%;
                left:3%;
                width:90.5%;
                height:72%;
                background-color: #70FFF0;
                padding:20px;
                opacity:1;
                z-index:1003;
            }
            .pop{
                display: none;
                position: relative;
                margin: auto auto;
                top:10%;
                width: 25%;
                height: 70%;
                padding: 1%;
                border: 10px solid #3c3d3d;
                background-color: white;
                z-index:1004;
                overflow: auto;
            }
            .submissionsPop{
                width:70%;
                height:65%;
                top:12%;
                left:13%;
                z-index:1005;
                position: absolute;
                background-color: white;
            }
            .subs-table{
                z-index:1006;
                background-color: #AEC6CF;
                border-radius: 5px;
            }
        </style>
        <script>
            function enter(param)
            {
                document.getElementById(param).style.display = "block";
            }
            function cls(param)
            {
                document.getElementById(param).style.display = "none";
            }
            function remAtt(obj, att) {
                document.getElementByid(obj).removeAttribute(att);
            }
            function addAtt(obj, att, value) {
                document.getElementById(obj).setAttribute(att, value);
            }
            function setAtt(obj,value) {
                document.getElementById(obj).value = value;
            }
            function disabledOff(obj,value) {
                document.getElementById(obj).disabled = value;
            }
            function editButton() {
                if (document.getElementById('ed').value == "Save"){
                    addAtt("nm", "disabled", "disabled");
                    addAtt("ins", "disabled", "disabled");
                    addAtt("dead", "disabled", "disabled");
                    addAtt("in", "disabled", "disabled");
                    addAtt("out", "disabled", "disabled");
                    document.getElementById("subs").disabled = false;
                    setAtt("ed","Edit");
                }else{
                    addAtt("subs", "disabled", "disabled");
                    document.getElementById("nm").disabled = false;
                    document.getElementById("ins").disabled = false;
                    document.getElementById("dead").disabled = false;
                    document.getElementById("in").disabled = false;
                    document.getElementById("out").disabled = false;
                    setAtt("ed","Save");
                }

            }
        </script>
    </head>

    <body background = "css/img/viewsBG.jpg"
          <%
              if (session.getAttribute("currentAssign") != null) {
          %>
          onload ="enter('viewAssign');
          <%
              }
              if (session.getAttribute("submissions") != null) {
          %>
                  enter('viewSubs');
          <%
              }
          %>
          ">
        <div class = "maincontent">

            <div class = "white" align = "center">
                <div class = "white white-table">
                    <!--CREATE TABLE FOR THE ASSIGNMENTS HERE!-->
                    <table class="table table-hover table-condensed">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Assignment Name</th>
                                <th>Deadline</th>
                                <th>Inputs</th>
                                <th>Output</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items = "${assigns}" var = "assign">
                                <tr onclick="document.location = 'UserController?action=view&assign_id=<c:out value = '${assign.assign_id}'/>';
                                        enter('viewAssign');" style="cursor:pointer;">
                                    <td><c:out value = "${assign.assign_id}"/></td>
                                    <td><c:out value = "${assign.name}"/></td>
                                    <td><c:out value = "${assign.deadline}"/></td>
                                    <td><c:out value = "${assign.inputs}"/></td>
                                    <td><c:out value = "${assign.output}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <h1 align = "left">Welcome, <%=session.getAttribute("fname")%>!</h1><hr>
                <form method = "POST" action = "Logout">
                    <input style = "position:absolute;top:3%;right:20%;" type = "submit" value = "Logout" class = "btn btn-sm btn-primary" />
                </form>
                <button style = "position:absolute;top:3%;right:5%;" class = "btn btn-sm btn-primary" onclick = "enter('addAssign')">
                    Add Assignment
                </button>
            </div>
        </div>





        <!--POPUP FORMS-->
        <!--VIEW ASSIGN-->
        <div class = "pop" id="viewAssign" align = "center">
            <button type="button" class="close" onclick = "cls('viewAssign');">[&times;]</button><br>
            <!--inside the add Assignments-->
            <h3>Assignment Form</h3><hr>
            <form method = "POST" action = "UserController">
                <c:set value = "${assignInfo}" var = "obj" />
                <input type = "hidden" name = "assign_id" value = "<c:out value = "${obj.assign_id}"/>"/>
                <input disabled type = "text" name = "name" id = "nm" placeholder = "Assignment Name" value ="<c:out value = "${obj.name}"/>" /><br>
                <textarea disabled name = "instruction" id = "ins" placeholder = "Instruction" rows = 5 ><c:out value = "${obj.instruction}"/></textarea><br>
                <input disabled type = "date" name = "deadline"  id = "dead" placeholder = "Deadline(YYYY-MM-DD)" value ="<c:out value = "${obj.deadline}"/>" /><br>
                <input disabled type = "file" name = "inputs" id = "in" placeholder = "Test Cases" value ="<c:out value = "${obj.inputs}"/>" /><br>
                <input disabled type = "file" name = "output" id = "out" placeholder = "Expected Output" value ="<c:out value = "${obj.output}"/>" /><br>
                <input type = "hidden" value = "editassign" name = "action"/><hr>
                <input style = "width:110px" type = "submit" id = "ed" name = "edit" value = "Edit" class = "btn btn-sm btn-primary" onclick = "editButton();"/>
              IDEA HERE GET THE VALUE IF BUTTON EDIT AND MAKE IF STATEMENT IN USER CONTROLLER! :D
                <button style = "width:110px" type="button" id = "subs" class = "btn btn-sm btn-primary" onclick = "document.location = 'UserController?action=viewsubmissions&assign_id=<c:out value = '${obj.assign_id}'/>';
                        enter('viewSubs');">Submissions</button>
            </form>
        </div>


        <!--VIEW SUBMISSIONS-->
        <div class = "pop submissionsPop" id="viewSubs" align = "center">
            <button type="button" class="close" onclick = "cls('viewSubs');">[&times;]</button>
            <!--inside the add Assignments-->
            <h3>Submissions</h3>
            <div class = "white-table subs-table">
                <table class="table table-hover table-condensed">
                    <thead>
                        <tr>
                            <th>Student Number</th>
                            <th>Date Submitted</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items = "${submissions}" var = "sub">
                            <tr style="cursor:pointer;">
                                <td><c:out value = "${sub.stud_number}"/></td>
                                <td><c:out value = "${sub.date}"/></td>
                                <td><c:out value = "${sub.score}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <%
                if (session.getAttribute("submissions") != null) {
                    session.setAttribute("submissions", null);
                }
            %>
        </div>



        <div class = "pop" id="addAssign" align = "center">
            <button type="button" class="close" onclick = "cls('addAssign')">[&times;]</button><br>
            <!--inside the add Assignments-->
            <h3>Assignment Form</h3><hr>
            <form method = "POST" action = "UserController">
                <input type = "text" name = "name" placeholder = "Assignment Name" /><br>
                <textarea name = "instruction" placeholder = "Instruction" rows = 5></textarea><br>
                <input type = "date" name = "deadline" placeholder = "Deadline(YYYY-MM-DD)"/><br>
                <input type = "file" name = "inputs" placeholder = "Test Cases" /><br>
                <input type = "file" name = "output" placeholder = "Expected Output" /><br>
                <input type = "hidden" value = "addassign" name = "action" />
                <input type = "submit" name = "email" value = "Add" />
            </form>
        </div>
    </body>
</html>
<%
    } else {
        response.sendRedirect("index.jsp");
    }
%>