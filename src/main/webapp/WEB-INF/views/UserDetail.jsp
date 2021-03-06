<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ page import="com.mindfire.wsc.model.UserDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
<% 
System.out.println("@@@@@@@@@@@"+request.getRequestURL());
UserDTO userDto = (UserDTO)session.getAttribute("userSession");

if(userDto != null) {	
	if(userDto.getFirstName() != null && userDto.getLastName() != null) {
		response.sendRedirect("/sales/user");
	}		
} 
%>
</head>
<body>
<%@include file="header.jsp"%>
	<div id="register" class="container-full">		
		<div class="col-sm-12" align="right">
		<form class="formreg" action="/wsc/sales/user/updateUser" method="post" >		
			<h2 class="form-signin-heading" align="center">User Registration Form</h2>			
			<fieldset>
				<table class="form-register">					
					<tr>
						<td>
							<b>UserName: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="userName" id="userName" required="true" placeholder="User Name" 
							maxlength="40" value="<%=userDto.getUserName()%>" readonly="readonly"/>														
						</td>
					</tr>
					<tr>
						<td>
							<b>Email: </b>
						</td>
						<td>
							<input type="email" class="form-control" name="email" required="true" placeholder="Email" 
							maxlength="100" value="<%=userDto.getEmail()%>"/>
							<font color='red'><form:errors path='email' /></font>
							<input type="hidden" name="password" id="password" value="<%=userDto.getPassword()%>"/>							
						</td>
					</tr>
					<tr>
						<td>
							<b>First Name: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="firstName" required="true" placeholder="First Name" maxlength="50" />
							<font color='red'><form:errors path='firstName' /></font>
						</td>
					</tr>
					<tr>
						<td>
							<b>Last Name: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="lastName" required="true" placeholder="Last Name" maxlength="50"/>
							<font color='red'><form:errors path='lastName' /></font>
						</td>
					</tr>
					<tr>
						<td>
							<b>Phone: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="phone" required="true" placeholder="Phone" maxlength="14"/>
							<font color='red'><form:errors path='phone' /></font>
						</td>
					</tr>
					<tr>
						<td>
							<b>Pin Code: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="pincode" required="true" placeholder="Pincode" maxlength="6"/>
							<font color='red'><form:errors path='pincode' /></font>
						</td>
					</tr>
					<tr>
						<td>
							<b>Sex: </b>
						</td>
						<td>
							<input type="radio" name="sex" value="Male" checked="checked" />Male
							<input type="radio" name="sex" value="Female" />Female
						</td>
					</tr>
					<tr>
						<td>
							<b>Address: </b>
						</td>
						<td>
							<input type="textarea" class="form-control" name="address" maxlength="600"/>	
							<font color='red'><form:errors path='address' /></font>						
						</td>
					</tr>					
					<tr>
						<td>
							<button class="btn btn-lg btn-primary btn-block" type="reset" value="Reset">Reset</button>
						</td>
						<td>
							<button class="btn btn-lg btn-primary btn-block" type="submit" value="Register">Save</button>
						</td>						
					</tr>
				</table>			
			</fieldset>
		</form>
		</div>
		
		<!-- <div class="col-sm-6" align="left">
		<h1>Upload Your Profile photo</h1>
        <form method="post" action="/wsc/sales/user/doUpload" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>Upload Your Photo:</td>
                    <td><input type="file" name="filecontent" size="50" /></td>
                </tr>                
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Upload" /></td>
                </tr>
            </table>
        </form>
        </div>-->
	</div>
	<!-- end login -->
</body>
</html>