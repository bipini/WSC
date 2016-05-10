<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Manage User</title>
<script type="text/javascript">
function loadXMLDoc()
{
var uname = document.getElementById("userName").value;
var urls="/wsc/admin/user/checkUsername/"+uname;
//alert(uname); 
if (uname == "") {
    document.getElementById("err").innerHTML = "";
    return;
  }
xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
  if (xhttp.readyState == 4 && xhttp.status == 200) {
    document.getElementById("err").innerHTML = xhttp.responseText;
    var res = xhttp.responseText;
    if(res.indexOf("User Already Exist") > -1) {      
    	  document.getElementById("userName").value = "";    	  
    	  }
  }
};
xhttp.open("GET", urls, true);
xhttp.send();
}
</script>
</head>
<body>
	<%@include file="header.jsp"%>
	<script type="text/javascript">
	    $(function()
	    {	
	    	var inp = $("#userName").val();
	    	if(jQuery.trim(inp).length > 0)
	    	{	        
	        $('#userName').attr('readonly', 'true'); // mark it as read only
	        //$('#userName').blur(); 
	        $('#userName').css('background-color' , '#DEDEDE'); // change the background color
	    	}
	
	    });
	</script>
	<div id="register" class="container-full">		
		<form class="formreg" action="/wsc/admin/user/save" method="post" >		
			<h2 class="form-signin-heading" align="center">User Registration Form</h2>			
			<fieldset>
				<table class="form-register">					
					<tr>
						<td>
							<b>UserName: </b>
						</td>
						<td>
							<input type="text" class="form-control" name="userName" id="userName" required="true" placeholder="User Name" 
							maxlength="50" value="${userdetail.userName}" onblur="loadXMLDoc()"/>	
							<span id="err"> </span>						
							<font color='red'><form:errors path='userName' /></font>
						</td>
					</tr>
					<tr>
						<td>
							<b>Password: </b>
						</td>
						<td>
							<input type="password" class="form-control" name="password" required="true" placeholder="Password"
							maxlength="20" value="${userdetail.password}" />
							<font color='red'><form:errors path='password' /></font>
						</td>
					</tr>
					<tr>
						<td>
							<b>Email: </b>
						</td>
						<td>
							<input type="email" class="form-control" name="email" required="true" placeholder="Email" maxlength="100"
							value="${userdetail.email}"/>
							<font color='red'><form:errors path='email' /></font>
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
	<!-- end login -->
</body>
</html>