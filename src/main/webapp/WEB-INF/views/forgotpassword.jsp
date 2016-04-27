<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/wsc/wscui/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="/wsc/wscui/css/bootstrap.min.css">

<script src="/wsc/wscui/js/jquery.js"></script>
<script src="/wsc/wscui/js/bootstrap.min.js"></script>
<title>Forgot Password</title>

<script language="javascript" type="text/javascript">
$(document).ready(function() {	
	$('#email, #r1').click(function(){
        $('#phone').prop('disabled', true);
        $('#email').prop('disabled', false);
	});
	$('#phone, #r2').click(function(){
        $('#email').prop('disabled', true);
        $('#phone').prop('disabled', false);
	});
});

</script>

</head>
<body>
<div style="background-color: #3D88C9; color: #FFF; height: 60px !important; min-height: 10% !important;">
	<div style="float: left;">
		<a href="/wsc/"><img alt="Logo" src="/wsc/wscui/images/wsc_logo.png"></a>
	</div>	
	<div id="loged_in_user">
		<img src="/wsc/wscui/images/user.png" alt="User" class="img-circle"  width="22px" height="22px"/> &nbsp;&nbsp;&nbsp;<a href="/wsc/" style="color: #FFF;">Logout</a>
		<ul class="nav">
			<li><img src="/wsc/wscui/images/setting.png" alt="Setting" />
				<ul>
					<li><a href="/wsc/">Change Password</a></li>
					<li><a href="/wsc/">Edit Your Profile</a></li>
				</ul>
			</li>
		</ul>
	</div>	
</div>
<div id="login" class="container-full">
		
		<form action="/wsc/user/sendpassword" method="post" class="form-signin">
		<h2 class="form-signin-heading" >Forgot Password ?</h2>
			<fieldset>
				<p>					
					<input type="text" class="form-control" name="userName" required="true" placeholder="User Name" maxlength="40" />
				</p>
				<p>&nbsp;</p>
				<div class="anything">
				<p>	
					<strong>Send to Email</strong>				
					<input name="emailr" type="radio" id="r1" value="email" />
					<input type="email" class="form-control" name="email" id="email" required="true" placeholder="Enter Your Email" maxlength="100" />
				</p>				
				<span class="grey" style="display:block;text-align: center;"> OR </span>
				<p>					
					<strong>Send to Phone</strong>	
					<input name="emailr" type="radio" id="r2" value="phone" />
					<input type="text" class="form-control" id="phone" name="phone" id="phone" required="true" 
					placeholder="Enter Your 10 digit Phone" maxlength="15" />					
				</p>
				</div>
				<p>
					<button class="btn btn-lg btn-primary btn-block" type="submit" value="Submit">Submit</button>
				</p>
				<p><span style="color:red"><c:out value="${msg}"/></span></p>						
										
			</fieldset>
		</form>
		
	</div>
</body>
</html>