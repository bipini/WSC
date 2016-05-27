<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mindfire.wsc.model.UserDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	
	// This page is specific to Admin User only
	UserDTO userDto = (UserDTO)session.getAttribute("userSession");
	if(!("admin").equalsIgnoreCase(userDto.getRole())) {
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
		
	<div class="container-fluid">
	  <h1 align="center">Wholesale Sales Connect</h1>
	  <p><a href="/wsc/admin/user">Add User</a></p>
	  <p><span style="color:red"><c:out value="${msg}"/></span></p>
	  <div class="row">
	    <div class="col-sm-5">
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
	        rowList: [5, 10, 15,20,25],  
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
            return "<a href='<%=request.getContextPath()%>/admin/user/delete/" + rowdata.id + "' class='ui-icon ui-icon-closethick' onClick=\"return confirm(\'Are you sure you want to delete this item?\');\"></a>";
        }        
		});
		</script>
		<table id="theGrid"></table>
		<div id="gridPager"></div>
		</div>
	    </div>
<script>
$(document).ready(function(){    
    $('.dropdown-menu li').on('click', function(){		 
         var pid = $('span', this).text();
         //alert(pid);		      
        $.ajax({
            dataType : "json",
            url : '/wsc/admin/product/getcategorydetail/'+pid,
            headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            type : 'GET',
            success : function(data) {                
                var exHTMl = '<thead><tr><td>Product Number</td><td>Product Name</td><td>Product Quantity</td><td>Cost Price</td><td>Selling Price</td><td>Edit/Delete</td></tr></thead><tbody>';
                var trHTML = '';                
                $.each(data,function(key, item) {
                	trHTML += '<tr><td>' + item.productNumber + '</td><td>' + item.productname + '</td><td>' + item.quantity + '</td><td>' + item.costprice + '</td><td>' + item.sellingprice + 
                	'</td><td><a href="javascript:void(0);" onClick="popup(\'/wsc/admin/product/editproducts/'+
                			item.productNumber+'?categoryId='+pid+'\', \'window\',500,500)"><img src="/wsc/wscui/images/edit.png" alt="Edit" /></a>&nbsp;&nbsp;<a href="/wsc/admin/product/deleteproducts/'+
                			item.productNumber+'" onClick="return confirm(\'Are you sure you want to delete this item?\');"><img src="/wsc/wscui/images/delete.png" alt="Delete" /></a></td></tr>';
                });
                $('#records_table').empty();
                $('#records_table').append(exHTMl);
                $('#records_table').append(trHTML).append('</tbody>');                
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) { 
            	var errHTML = 'Products are not added to this category, Please check later'; 
            	 $('#records_table').empty()
                 $('#records_table').append(errHTML);
            	 alert("Status: " + textStatus); alert("Error: " + errorThrown); 
            } 
        });
	});
    $('#records_table').DataTable();
});
</script>
	    <div class="col-sm-7">
	    	<script>
				function popup(url, title, w, h) {
				var left = (screen.width/2)-(w/2);
				var top = (screen.height/2)-(h/2);
				return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
				} 			
			</script> 
			<div class="row">
				<div class="col-sm-6 col-md-6" style="border-right: 1px solid #ccc;">
					<p><strong>Category</strong><br>
			    	<a href="javascript:void(0);" onClick="popup('/wsc/admin/product/addproductcategory', 'window',300,300)" class="btn btn-info" role="button">Add Product Category</a>&nbsp;&nbsp;
			    	<a href="javascript:void(0);" onClick="popup('/wsc/admin/product/editdelproductcategory', 'window',300,300)" class="btn btn-info" role="button">Edit/Delete Category</a>
			    	</p>
				</div>
				<div class="col-sm-6 col-md-6">
					<p><strong>Products</strong><br>
					<a href="javascript:void(0);" onClick="popup('/wsc/admin/product/addproductsToCategory', 'window',500,600)" class="btn btn-info" role="button">Add Products to Category</a>
				</div>
			</div>			
	    	<p>&nbsp;</p>    	
	    	  
	    	  <div class="dropdown" align="center">
	    	  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Select Categories
			    <span class="caret"></span></button>			   
			    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1" align="center">
			      <c:forEach items="${productCategory}" var="productcategory">					            
		            <li id="getit" role="presentation"><a role="menuitem" tabindex="-1" href="#"><span>${productcategory.categoryId}</span>${productcategory.categoryName}</a></li>	
			      </c:forEach>		      
			    </ul>
 			    <p>&nbsp;</p> 
			    <table id="records_table" class="table table-striped table-bordered table-hover">			    	
			    	
			    </table>		    
			  </div>
	    </div>
	  </div>
	</div>
</body>
</html>