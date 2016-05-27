<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mindfire.wsc.model.UserDTO" %>
<title>Delete Product Category</title>
<link href="/wsc/wscui/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="/wsc/wscui/css/bootstrap.min.css">

<script src="/wsc/wscui/js/jquery.js"></script>
<script src="/wsc/wscui/js/bootstrap.min.js"></script>
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
</head>
<body>
<script>
// wait for the DOM to be loaded 
$(document).ready(function() {		    
		var msg = "${msg}";		
		if(!("").equals(msg)) {
		alert(msg);
	    // close window
	    window.close();	    
	    
	    //Refresh the Window after Updates
	    window.onunload = refreshParent;
	    function refreshParent() {
	        window.opener.location.reload();
	    }
	 	// return
	    return false;
		}
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
				<form id="myForm" action="../../admin/product/editdeleteproductcategory" method="post">
					<p>&nbsp;</p>
					<strong>Category : </strong>	
				    <select class="btn btn-primary" name="categoryId" id="categoryId">
					   <option value="NONE">--Select Category--</option>
					   <c:forEach items="${productCategory}" var="productcategory">		
					   <option value="${productcategory.categoryId}">${productcategory.categoryName}</option>	
					   </c:forEach>		 				   
					</select> 
				    <p>&nbsp;</p>
					<button class="btn btn-primary" type="reset" value="Cancel" onclick="window.open('', '_self', ''); window.close();">Cancel</button>
					&nbsp;&nbsp;
					<input id="btn" class="btn btn-warning" type="submit" name="EditCategory" value="Edit" />&nbsp;&nbsp;
					<input id="btn" class="btn btn-danger" type="submit" name="DeleteCategory" value="Delete" 
					onClick="return confirm('Are you sure you want to delete this item?'');"/>
										
				</form>
			</div>
		</div>
	</div>
</body>
</html>