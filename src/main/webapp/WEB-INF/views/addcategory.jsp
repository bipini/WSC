<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mindfire.wsc.model.UserDTO" %>
<title>Add Category</title>

<script type="text/javascript">
function loadXMLDoc()
{
var categoryName = document.getElementById("categoryName").value;
var urls="/wsc/admin/product/addproduct/"+categoryName;
//alert(uname); 
if (categoryName == "") {
    document.getElementById("err").innerHTML = "";
    return;
  }
xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
  if (xhttp.readyState == 4 && xhttp.status == 200) {
    document.getElementById("err").innerHTML = xhttp.responseText;
    var res = xhttp.responseText;
    if(res.indexOf("Category Already Exist, <br>Please try another") > -1) {      
    	  document.getElementById("categoryName").value = "";    	  
    	  }  
    else {
    	window.closeWindow();
    }
  }
};
xhttp.open("GET", urls, true);
xhttp.send();
}
</script>
<link href="/wsc/wscui/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="/wsc/wscui/css/bootstrap.min.css">

<script src="/wsc/wscui/js/jquery.js"></script>
<script src="/wsc/wscui/js/bootstrap.min.js"></script>
</head>
<body>
<%
	// It is used to check the Session for null or not
	if (session.getAttribute("userSession") == null
			&& !request.getRequestURL().toString()
					.endsWith("/login.jsp")) {
		response.sendRedirect("/wsc/login");
	}
	
	//This page is specific to Admin User only
	UserDTO userDto = (UserDTO)session.getAttribute("userSession");
	if(!("admin").equalsIgnoreCase(userDto.getRole())) {
		response.sendRedirect("/wsc/login");
	}
%>
<script>
$(document).ready(function() {
	  $("form").submit(function() {
	    var $form = $(this);
	    // submit form
	    $.post($form.attr('action'), $form.serializeArray());
	    // alert
	    alert("The request has been submitted.");
	    // close window
	    window.close();	    
	    
	    //Refresh the Window after Updates
	    window.onunload = refreshParent;
	    function refreshParent() {
	        window.opener.location.reload();
	    }
	 	// return
	    return false;
	  });
	});
</script>
<div style="background-color: #3D88C9; color: #FFF; height: 60px !important; min-height: 10% !important;">
	<div style="float: left;">
		<a href="/wsc/"><img alt="Logo" src="/wsc/wscui/images/wsc_logo.png"></a>
	</div>
	<%
		if (session.getAttribute("userSession") != null) {
	%>
	<div id="loged_in_user">
		<a href="/wsc/logout" style="color: #FFF;">Logout</a>		
	</div>
	<%
		}
	%>
</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-12" align="center">
				<form action="../../admin/product/savecategoryname" method="post">
					<p>&nbsp;</p>
					<strong>Enter New Category : </strong>	
					<input type="text" class="form-control" name="categoryName" id="categoryName" required="true" 
					placeholder="New Category" size="100"  onblur="loadXMLDoc()" />
					<p><span id="err"></span></p>
					<button class="btn btn-primary" type="submit" value="AddCategory">Add New Category</button>					
				</form>
			</div>
		</div>
	</div>
</body>
</html>