<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ page import="com.mindfire.wsc.model.UserDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<% 
System.out.println("@@@@@@@@@@@"+request.getRequestURL());
UserDTO userDto = (UserDTO)session.getAttribute("userSession");
if(userDto != null) {
	if(("admin").equalsIgnoreCase(userDto.getRole())) {
		response.sendRedirect("admin");
	}
	if(("user").equalsIgnoreCase(userDto.getRole())) {
		response.sendRedirect("sales");
	}	
} 
%>
</head>
<body>
<%@include file="header.jsp"%>
	<div id="login" class="container-full">
		
		<form action="login" method="post" class="form-signin">
		<h2 class="form-signin-heading" >Please Sign in</h2>
			<fieldset>
				<p>
					<font color='red'><form:errors path='userName' /></font>
					<input type="text" class="form-control" name="userName" required="true" placeholder="User Name" maxlength="40">
				</p>
				<p>
					<font color='red'><form:errors path='password' /></font>
					<input type="password" class="form-control" name="password" required="true" placeholder="Password" maxlength="20">
				</p>
				
				<p>
					<button class="btn btn-lg btn-primary btn-block" type="submit" value="Login">Login</button>
				</p>
				<p>
					<a href="/wsc/user/forgotpassword">Forgot Password?</a>
				</p>
			</fieldset>
		</form>
		
	</div>
	<!-- end login -->
</body>
</html>