<%-- 
    Document   : teacherView
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
        <title>Student's View</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <style>
            .maincontent{}
            .white{
                border-radius: 10px;
                display: block;
                position: fixed;
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
                z-index:10044;
                overflow: auto;
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
                document.getElementsByName(obj).removeAttribute(att);
            }
            function addAtt(obj, att) {
                document.getElementsByName(obj).createAttribute(att);
            }
            function setAtt(obj, att, value) {
                document.getElementsByName(obj).setAttribute(att, value);
            }
        </script>
    </head>
    <body background = "css/img/viewsBG.jpg"
          <%
              if (session.getAttribute("currentAssign") != null) {
          %>
          onload ="enter('viewAssign')"
          <% }%>
          >
        <div class = "maincontent">
            <div class = "white" align = "center">
                <div class = "white white-table">
                    <!--code for inside table-->
                    <table class="table table-hover table-condensed">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Assignment Name</th>
                                <th>Deadline</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items = "${assigns}" var = "assign">
                                <tr onclick="document.location = 'UserController?action=view&assign_id=<c:out value = '${assign.assign_id}'/>';
                                        enter('viewAssign');" style="cursor:pointer;">
                                    <td><c:out value = "${assign.assign_id}"/></td>
                                    <td><c:out value = "${assign.name}"/></td>
                                    <td><c:out value = "${assign.deadline}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class = "col-md-6">
                    <h1 align = "left">Welcome, <%=session.getAttribute("fname")%>!</h1><hr>
                </div>
                <form method = "POST" action = "Logout">
                    <input style = "position:absolute;top:3%;right:20%;" type = "submit" value = "Logout" class = "btn btn-sm btn-primary" />
                </form>
                <form method = "POST" action = "/">
                    <input style = "position:absolute;top:3%;right:5%;" type = "submit" value = "Add Assignments" class = "btn btn-sm btn-primary" />
                </form>
            </div>
        </div>

        <!--POPUP FORMS-->
        <div class = "pop" id="viewAssign" align = "center">
            <button type="button" class="close" onclick = "cls('viewAssign');">[&times;]</button><br>
            <!--inside the add Assignments-->
            <h3>Assignment Form</h3><hr>
            <form method = "POST" action = "UserController">
                <c:set value = "${subsInfo}" var = "subs"/>
                <c:set value = "${assignInfo}" var = "obj"/>
                <input type = "hidden" name = "assign_id" value = "<c:out value = "${obj.assign_id}"/>"/>
                <input disabled type = "text" name = "name" placeholder = "Assignment Name" value ="<c:out value = "${obj.name}"/>" /><br>
                <textarea disabled name = "instruction" placeholder = "Instruction" rows = 4 ><c:out value = "${obj.instruction}"/></textarea><br>
                <input disabled type = "date" name = "deadline" placeholder = "Deadline(YYYY-MM-DD)" value ="<c:out value = "${obj.deadline}"/>" /><br>
                <input disabled type = "text" name = "file" value = "<c:out value = "${subs.score}"/>" placeholder ="Score" />
                <hr>
                
                <input type = "file" name = "file" <c:if test="${subs.score != null}">disabled</c:if> /><br><br>
                <select name = "language" style = "width:200px;" <c:if test="${subs.score != null}">disabled</c:if> >
			<option value = "java">Java</option>
                </select>
                    <hr>
                    <input type = "hidden" value = "submitassign" name = "action" />
                    <input class = "btn btn-sm btn-primary" type = "submit" name = "email" value = "Submit" <c:if test="${subs.score != null}">disabled</c:if> />
                </form>
        </div>
    </body>
</html>
<%
    } else {
        response.sendRedirect("index.jsp");
    }
%>