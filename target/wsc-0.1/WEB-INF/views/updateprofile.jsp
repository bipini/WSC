<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.mindfire.wsc.model.UserDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Details</title>
<% 
System.out.println("@@@@@@@@@@@"+request.getRequestURL());
UserDTO userDto = (UserDTO)session.getAttribute("userSession");

if(userDto == null) {		
		response.sendRedirect("/login");	
} 
%>
</head>
<body>
<%@include file="header.jsp"%>
<script type="text/javascript">
var frm = document.forms[0];

if (request.getParameter("Register") != null) {

    var pageName = "/wsc/sales/user/updateUser";
    frm.action = pageName;
    frm.submit();
    
 }
 else if (request.getParameter("Upload") != null) {
	 var pageName = "/wsc/sales/user/doUpload";
	 frm.action = pageName;
     frm.submit();
  }
 else {
	
	}
 </script>
	<div id="register" class="container-full">		
		<div class="col-sm-6" align="right">
		<h2 class="form-signin-heading" align="center">Update User Details</h2>
		<form class="formreg" action="/wsc/sales/user/updateUser" method="post" >		
			<fieldset>
				<table class="form-register">					
					<tr>
						<td>
							<b>UserName: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="userName" id="userName" required="true" placeholder="User Name" 
							value="<%=userDto.getUserName()%>" readonly="readonly"/>														
						</td>
					</tr>
					<tr>
						<td>
							<b>Email: </b>
						</td>
						<td>
							<input type="email" class="form-control" name="email" required="true" placeholder="Email" 
							value="<%=userDto.getEmail()%>"/>
							<input type="hidden" name="password" id="password" value="<%=userDto.getPassword()%>"/>	
							<input type="hidden" name="userRole" id="password" value="<%=userDto.getUserRole()%>"/>							
						</td>
					</tr>
					<tr>
						<td>
							<b>First Name: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="firstName" required="true" 
							placeholder="First Name" value="<%=userDto.getFirstName()%>"/>
						</td>
					</tr>
					<tr>
						<td>
							<b>Last Name: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="lastName" required="true" placeholder="Last Name" 
							value="<%=userDto.getLastName()%>"/>
						</td>
					</tr>
					<tr>
						<td>
							<b>Phone: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="phone" required="true" placeholder="Phone" 
							value="<%=userDto.getPhone()%>"/>
						</td>
					</tr>
					<tr>
						<td>
							<b>Pin Code: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="pincode" required="true" placeholder="Pincode"
							value="<%=userDto.getPincode()%>" />
						</td>
					</tr>
					<tr>
						<td>
							<b>Sex: </b>
						</td>
						<td>
							<input type="radio" name="sex" value="Male"
							<%=(!("Male").equals(userDto.getSex())?" checked=\"checked\"":"")%>/>Male
							
							<input type="radio" name="sex" value="Female" 
							<%=(!("Female").equals(userDto.getSex())?" checked=\"checked\"":"")%>/>Female
						</td>
					</tr>
					<tr>
						<td>
							<b>Address: </b>
						</td>
						<td>
							<input type="textarea" class="form-control" name="address" 
							value="<%=userDto.getAddress()%>"/>							
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
		<div class="col-sm-6" align="left">		
		<h2 class="form-signin-heading" align="center">Upload Your Profile photo</h2>
        <form method="post" action="/wsc/sales/user/doUpload" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>Upload Your Photo:</td>
                    <td><input type="file" name="filecontent" id="filecontent" size="50" /></td>
                </tr>                
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Upload" /></td>
                </tr>
            </table>
        </form>
        </div>
	</div>
	<!-- end login -->
</body>
</html>