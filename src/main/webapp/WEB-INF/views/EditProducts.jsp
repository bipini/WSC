<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mindfire.wsc.model.UserDTO" %>
<title>Edit Products</title>
<link href="/wsc/wscui/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="/wsc/wscui/css/bootstrap.min.css" />

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
	
	// Show the Indivisual Photo
	String photo = "/wsc/wscui/images/user.png";
	if(session.getAttribute("userPhoto") != null) {
		photo = (String)session.getAttribute("userPhoto");
		photo = "/wsc/resources/images/"+photo;
	}
%>
<div
	style="background-color: #3D88C9; color: #FFF; height: 60px !important; min-height: 10% !important;">
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

<script type="text/javascript">
function loadXMLDoc()
{
var pname = document.getElementById("productname").value;
var oriPname = "${productdetails.productname}";
var urls="/wsc/admin/product/checkProductname/"+pname;
//alert(oriPname); 
if (pname == "") {
    document.getElementById("err").innerHTML = "Please add Unique Product Name";
    return;
  }
xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
  if (xhttp.readyState == 4 && xhttp.status == 200) {
    document.getElementById("err").innerHTML = xhttp.responseText;
    var res = xhttp.responseText;
    if(res.indexOf("Product Name Already Exist") > -1) {      
    	  document.getElementById("productname").value = oriPname;    	  
    	  }
  }
};
xhttp.open("GET", urls, true);
xhttp.send();
}

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
</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-12" align="center">				
				<form class="formreg" action="/wsc/admin/product/updateProducts" method="post">		
					<h2 class="form-signin-heading" align="center">User Registration Form</h2>			
					<fieldset>
						<table class="form-register">					
							<tr>
								<td width="30%">
									<b>Product Number: </b>
								</td>
								<td>
									<input type="text" class="form-control" name="productNumber" id="productNumber" required="true" placeholder="Product Number" 
									maxlength="40" value="${productdetails.productNumber}" readonly />
									<input type="hidden" name="categoryId" value="${productdetails.categoryId}" />
								</td>
							</tr>
							<tr>
								<td>
									<b>Product Name: </b>
								</td>
								<td>
									<input type="text" class="form-control" name="productname" id="productname" required="true" placeholder="Product Name"
									maxlength="90" value="${productdetails.productname}" onchange="loadXMLDoc()"/>
									<span id="err" style="color:red"></span>								
								</td>
							</tr>
							<tr>
								<td>
									<b>Quantity: </b>
								</td>
								<td>
									<input type="text" class="form-control" name="quantity" required="true" placeholder="Quantity" maxlength="10"
									value="${productdetails.quantity}"/>									
								</td>
							</tr>
							<tr>
								<td>
									<b>Cost Price: </b>
								</td>
								<td>
									<input type="text" class="form-control" name="costprice" required="true" placeholder="Cost Price" maxlength="10"
									value="${productdetails.costprice}"/>									
								</td>
							</tr>
							<tr>
								<td>
									<b>Selling Price: </b>
								</td>
								<td>
									<input type="text" class="form-control" name="sellingprice" required="true" placeholder="Selling Price" maxlength="10"
									value="${productdetails.sellingprice}"/>									
								</td>
							</tr>
							<tr>
								<td>
									<button class="btn btn-lg btn-primary btn-block" type="reset" value="Cancel" onclick="window.open('', '_self', ''); window.close();">Cancel</button>
								</td>
								<td>
									<button class="btn btn-lg btn-primary btn-block" type="submit" value="Register">Save</button>
								</td>						
							</tr>
						</table>			
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>