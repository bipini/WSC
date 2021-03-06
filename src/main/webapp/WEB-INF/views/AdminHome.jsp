<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>

<!-- JQuery CSS -->
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/redmond/jquery-ui.css" type="text/css" />
 
<!-- jqGrid CSS -->
<link rel="stylesheet" href="/wsc/wscui/css/ui.jqgrid.css" type="text/css" />
 
<!-- The actual JQuery code -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<!-- The JQuery UI code -->
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
<!-- The jqGrid language file code-->
<script type="text/javascript" src="/wsc/wscui/js/grid.locale-en.js"></script>
<!-- The atual jqGrid code -->
<script type="text/javascript" src="/wsc/wscui/js/jquery.jqGrid.js"></script>

</head>
<body>	
<!-- Header Starts-->
<link href="/wsc/wscui/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="/wsc/wscui/css/bootstrap.min.css">

<script src="/wsc/wscui/js/bootstrap.min.js"></script>
<%
	// It is used to check the Session for null or not
	if (session.getAttribute("userSession") == null
			&& !request.getRequestURL().toString()
					.endsWith("/login.jsp")) {
		response.sendRedirect("/wsc/login");
	}
%>
<div style="background-color: #3D88C9; color: #FFF; height: 60px !important; min-height: 10% !important;">
	<div style="float: left;">
		<a href="/wsc/"><img alt="Logo" src="/wsc/wscui/images/wsc_logo.png"></a>
	</div>
	<%
		// Show the Indivisual Photo
		String photo = "/wsc/wscui/images/user.png";
		if(session.getAttribute("userPhoto") != null) {
			photo = (String)session.getAttribute("userPhoto");
			photo = "/wsc/resources/images/"+photo;
		}	
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

	<div id="main-warper" class="container-full">
		<p>
			<c:out value='${msg}' />
		</p>
		<div style="width: 25%; text-align: center;">
			<a href="admin/user">Add User</a>
			<!-- <table class="wsc-table" >
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th colspan="2">Action</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.userName}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td><a href="admin/user/edit/${user.userId}">Edit</a></td>
						<td>&nbsp;</td>
						<td><a href="admin/user/delete/${user.userId}">Delete</a></td>
					</tr>

				</c:forEach>
			</table>-->
		</div>
	</div>
		
	<div class="jgrid">				
		
		<script language="javascript">
		$(document).ready(function () {		
					    	 
	    'use strict';
	     var gidData = [	                    
					<c:forEach items="${users}" var="user">	
					{id: "${user.userId}", name: "${user.userName}", email: "${user.email}"},		            
		            </c:forEach>
	        ],
	        theGrid = $("#theGrid"),
	        numberTemplate = {formatter: 'number', align: 'right', sorttype: 'number'},
	        horizontalScrollPosition = 0,
	        selectedRow = null;	 	
	     
	    	 
	    theGrid.jqGrid({
	    	url: 'admin/user/edit/',
	    	editurl:'admin/user/edit/${sval}',
	    	deleteurl:'admin/user/delete/${sval}',
	    	datatype: 'local',
	        data: gidData,
	        colNames: ['Name', 'Email', 'Edit', 'Delete'],
	        colModel: [
	            {name: 'name', index: 'name', width: 150, align: 'center', editable: true},
	            {name: 'email', index: 'email', width: 200, align: 'center', editable: true},
	            {name:'edit',search:false,index:'userId',width:30,sortable: false,formatter: editLink},
                {name:'delete',search:false,index:'userId',width:40,sortable: false,formatter: deleteLink},                                                       
	        ],
	        gridview: true,             
	        rownumbers: false,
	        rowNum: 10,
	        rowList: [5, 10, 15],	        
	        pager: '#gridPager',
	        viewrecords: true,
	        multiSort:true,
	        sortname: 'name asc, email',
	        sortorder: 'asc',           
	        caption: 'User Detail Sorting',
	        height: '100%'
	    });
	    function editLink(cellValue, options, rowdata, action)
        {
            return "<a href='<%=request.getContextPath()%>/admin/user/edit/" + rowdata.id + "' class='ui-icon ui-icon-pencil' ></a>";
        }
        function deleteLink(cellValue, options, rowdata, action)  {            
            return "<a href='<%=request.getContextPath()%>/admin/user/delete/" + rowdata.id + "' class='ui-icon ui-icon-closethick' ></a>";
        }        
		});
		</script>
		<table id="theGrid"></table>
		<div id="gridPager"></div>
	</div>
	
</body>
</html>