<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<script src="/wsc/wscui/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="/wsc/wscui/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<!-- The jqGrid language file code-->
<script type="text/javascript" src="/wsc/wscui/js/grid.locale-en.js"></script>

</head>
<body>
	
	<div class="jgrid">
		<table id="theGrid"></table>
		<div id="gridPager"></div>		
		
		<script language="javascript">
		$(document).ready(function () {
	    'use strict';
	     var gidData = [
	            {id: "1", orderdate: "2013-10-01", customer: "customer",  price: "200.00", vat: "10.00", completed: true, shipment: "TN", total: "210.00"},
	            {id: "2", orderdate: "2013-10-01", customer: "customer2",  price: "300.00", vat: "20.00", completed: false, shipment: "FE", total: "320.00"},
	            {id: "3", orderdate: "2011-07-30", customer: "customer3",  price: "400.00", vat: "30.00", completed: false, shipment: "FE", total: "430.00"},
	            {id: "4", orderdate: "2013-10-04", customer: "customer4",  price: "200.00", vat: "10.00", completed: true, shipment: "TN", total: "210.00"},
	            {id: "5", orderdate: "2013-11-31", customer: "customer5",  price: "300.00", vat: "20.00", completed: false, shipment: "FE", total: "320.00"},
	            {id: "6", orderdate: "2013-09-06", customer: "customer6",  price: "400.00", vat: "30.00", completed: false, shipment: "FE", total: "430.00"},
	            {id: "7", orderdate: "2011-08-30", customer: "customer7",  price: "200.00", vat: "10.00", completed: true, shipment: "TN", total: "210.00"},
	            {id: "8", orderdate: "2013-10-03", customer: "customer8",  price: "300.00", vat: "20.00", completed: false, shipment: "FE", total: "320.00"},
	            {id: "9", orderdate: "2013-09-01", customer: "customer9",  price: "400.00", vat: "30.00", completed: false, shipment: "TN", total: "430.00"},
	            {id: "10", orderdate: "2013-09-08", customer: "customer10", price: "702.00", vat: "30.00", completed: true, shipment: "IN", total: "530.00"},
	            {id: "11", orderdate: "2013-09-08", customer: "customer11",  price: "500.00", vat: "30.00", completed: false, shipment: "FE", total: "530.00"},
	            {id: "12", orderdate: "2013-09-10", customer: "customer12",  price: "500.00", vat: "30.00", completed: false, shipment: "FE", total: "530.00"}
	        ],
	        theGrid = $("#theGrid"),
	        numberTemplate = {formatter: 'number', align: 'right', sorttype: 'number'},
	        horizontalScrollPosition = 0,
	        selectedRow = null;
	 
	    theGrid.jqGrid({
	        datatype: 'gidData',
	        data: gidData,
	        colNames: ['Customer', 'Date',  'Price', 'VAT', 'Total', 'Completed', 'Shipment'],
	        colModel: [
	            {name: 'customer', index: 'customer', width: 90},
	            {name: 'orderdate', index: 'orderdate', width: 100, align: 'center', sorttype: 'date',
	                formatter: 'date', formatoptions: {newformat: 'd-M-Y'}, datefmt: 'd-M-Y'},
	            {name: 'price', index: 'price', width: 55, template: numberTemplate},
	            {name: 'vat', index: 'vat', width: 42, template: numberTemplate},
	            {name: 'total', index: 'total', width: 50, template: numberTemplate},
	            {name: 'completed', index: 'completed', width: 30, align: 'center', formatter: 'checkbox',
	                edittype: 'checkbox', editoptions: {value: 'Yes:No', defaultValue: 'Yes'}},
	            {name: 'shipment', index: 'shipment', width: 80, align: 'center', formatter: 'select',
	               edittype: 'select', editoptions: {value: 'FE:FedEx;TN:TNT;IN:Intime;us:USPS', defaultValue: 'Intime'}}                  
	        ],
	        gridview: true,             
	        rownumbers: false,
	        rowNum: 10,
	        rowList: [5, 10, 15],
	        pager: '#gridPager',
	        viewrecords: true,
	        multiSort:true,
	        sortname: 'orderdate asc, price',
	        sortorder: 'asc',           
	        caption: 'Multi-column Sorting',
	        height: '100%'
	    });
	});
		</script>
	</div>
	
</body>
</html>