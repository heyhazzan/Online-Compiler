<%-- 
    Document   : index
    Created on : May 20, 2015, 11:30:04 AM
    Author     : Hazzan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <title>OC Portal</title>
        <style>
			.maincontent{}
			.white{
				border-radius: 10px;
				display: block;
				position: fixed;
				top: 20%;
				right: 120px;
				width: 15%;
				height: 50%;
				padding: 20px;
				background-color: white;
				z-index:1002;
				overflow: auto;
				opacity: .7;
			}
			.white-welcome{
				left:120px;
				top:7%;
				padding: 20px;
				width: 21%;
				text-align: center;
				height: 82%;
			}
		</style>
    </head>
    <body background = "css/img/welcomeBG.jpg">
        <div class = "maincontent">
			<div class = "white">
				<center>
				<hr>
				<h3>LOGIN!</h3>
				<hr><br>
				<form method = "POST" action = "Login">
					<input style = "width:90%;" type = "text" name = "username" placeholder = "Username" />
					<input style = "width:90%;" type = "password" name = "password" placeholder = "Password" /><hr>
					<input type = "submit" value = "Login" class = "btn btn-sm btn-primary" />
				</form>
			</center>
			</div>	
			<div class = "white white-welcome">
				<h3 style = "top:1%;left:25%;position:absolute;">WELCOME!</h3>
				<br><hr>
				<p style = "text-align: justify;">
						This is an online compiler. Destined to aid both teachers and students in submitting their projects on time, with the convience of technology, of course! With the help of this, teachers and students are able to submit, check and recieve the grades and projects on time! Go ahead and register! 
				</p>
				<hr>
				<form method = "POST" action = "Register">
					<input type = "text" name = "fname" placeholder = "First Name" />
					<input type = "text" name = "lname" placeholder = "Last Name" />
					<input type = "text" name = "stud_num" placeholder = "Student Number" />
					<input type = "password" name = "password" placeholder = "Desired Password" />
					<select name = "year" style = "width:100px;">
						<option value = "1">1st Year</option>
						<option value = "2">2nd Year</option>
						<option value = "3">3rd Year</option>
						<option value = "4">4th Year</option>
						<option value = "5">5th Year</option>
					</select>
					<input type = "text" name = "email" placeholder = "E-mail" />
                                        <!--insert select code for getting all the name of teachers-->
					<br><input type = "submit" value = "Register" class = "btn btn-sm btn-primary"/>
				</form>
			</div>
		</div>
    </body>
</html>

