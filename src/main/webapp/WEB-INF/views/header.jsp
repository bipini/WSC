<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<img src="<%=photo%>" alt="${userSession.userName}" class="img-circle"  width="22px" height="22px"/> ${userSession.userName}&nbsp;&nbsp;&nbsp;<a href="/wsc/logout" style="color: #FFF;">Logout</a>
		<ul class="nav">
			<li><img src="/wsc/wscui/images/setting.png" alt="Setting" />
				<ul>
					<li><a href="/wsc/admin/user/changepassword/${userSession.userName}">Change Password</a></li>
					<li><a href="/wsc/admin/user/editprofile/${userSession.userName}">Edit Your Profile</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<%
		}
	%>
</div>
