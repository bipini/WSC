<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<title>Change Password</title>
</head>
<body>
	<%@include file="header.jsp"%>	
	<div id="register" class="container-full">		
		<form class="formreg" action="/wsc/admin/user/UpdateUserPassword" method="post" >		
			<h2 class="form-signin-heading" align="center">Change Password</h2>			
			<fieldset>
				<table class="form-register">					
					<tr>
						<td>
							<b>Existing Password: </b>
						</td>
						<td>
							<input type="password" class="form-control" name="existingpwd" id="existingpwd" 
							required="true" placeholder="Existing Password"/> 																											
						</td>
					</tr>
					<tr>
						<td>
							<b>News Password: </b>
						</td>
						<td>
							<input type="password" class="form-control" name="password" id="password" 
							required="true" placeholder="New Password" />
						</td>
					</tr>
					<tr>
						<td>
							<b>Confirm Passowrd: </b>
						</td>
						<td>
							<input type="password" class="form-control" name="confpassword" id="confpassword" required="true" 
							placeholder="Confirm Password" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">							
							<span style="color:red"><c:out value="${msg}"/></span>						
						</td>
					</tr>
					<tr>
						<td>
							<button class="btn btn-lg btn-primary btn-block" type="reset" value="Reset">Reset</button>
						</td>
						<td>
							<button class="btn btn-lg btn-primary btn-block" type="submit" value="ChangePassword">Save</button>
						</td>						
					</tr>
				</table>			
			</fieldset>
		</form>
		
	</div>
	<!-- end login -->
</body>
</html>