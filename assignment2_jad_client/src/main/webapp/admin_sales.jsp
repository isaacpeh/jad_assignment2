<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://unpkg.com/cirrus-ui" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous" />
<title>Insert title here</title>
</head>
<body>
	<%@ page import="model.*, model.Category,java.util.*"%>
	<%
	// sales management results
	List<TourRecord> displaySalesTour = (ArrayList<TourRecord>) request.getAttribute("mgmtSalesTours");
	List<User> displaySalesUser = (ArrayList<User>) request.getAttribute("mgmtSalesUsers");
	%>
	<%@ include file="header.jsp"%>
	<h1 align="center" style="margin-top: 5rem">Sales Management</h1>
	<div class="row px-4">
		<h4 class="m-0 mr-1" style="line-height: 2;">Sort By</h4>
		<form style='display: flex;'>
			<div class="col-12 level-item input-control">
				<select class="select" placeholder="Choose one" name="filterChoice">
					<option value="top">Top 10 Users</option>
					<option value="topCategory">Best and Least Selling
						Categories</option>
					<option value="date">Sales By Date</option>
					<option value="tourByUsers">Tour bought by user</option>
					<option value="byTour">Order by Tour ASC</option>
				</select>
			</div>
			<button class="mt-1 bg-primary text-white">OK</button>
		</form>
	</div>
	<%
	String filterChoice = request.getParameter("filterChoice");
	if (filterChoice != null) {
		if (filterChoice.equals("top")) {
	%>
	<div class='px-4'>
		<form action="/assignment2_jad_client/admin_sales_filter" method="get">
			<input type="text" id="category" name="salesfilter" value="topValue"
				style="display: none;" />
			<button class='text-white bg-warning'>By Value</button>
		</form>
		<form action="/assignment2_jad_client/admin_sales_filter" method="get">
			<input type="text" id="category" name="salesfilter" value="topOrders"
				style="display: none;" />
			<button class='text-white bg-warning'>By Orders</button>
		</form>
	</div>
	<%
	} else if (filterChoice.equals("date")) {
	%>
	<div class='px-4 row'>
		<form class="col-4"
			action="/assignment2_jad_client/admin_sales_filter" method="get">
			<h6>Please Choose the Start Date and End Date</h6>
			<label>Date From:</label> <input type="date" name="dateFrom">
			<label class='mt-2'>Date To:</label> <input type="date" name="dateTo">
			<button class='mt-2 bg-warning text-white'>confirm</button>
		</form>
	</div>
	<%
	} else if (filterChoice.equals("tourByUsers")) {
	%>
	<div class='px-4 row'>
		<form class="col-4"
			action="/assignment2_jad_client/admin_sales_filter" method="get">
			<h6>Please input Tour ID:</h6>
			<input type="text" name="salesTourid">
			<button class='mt-2 bg-warning text-white'>Search</button>
		</form>
	</div>
	<%
	} else if (filterChoice.equals("topCategory")) { %>
	<div class='px-4'>
		<form action="/assignment2_jad_client/admin_sales_filter" method="get">
			<input type="text" id="category" name="salesfilter" value="topCategory"
				style="display: none;" />
			<button class='text-white bg-warning'>CONFIRM CHOICE</button>
		</form>
	</div>
	<% } else if (filterChoice.equals("byTour")) { %>
	<div class='px-4'>
		<form action="/assignment2_jad_client/admin_sales_filter" method="get">
			<input type="text" id="category" name="salesfilter" value="byTour"
				style="display: none;" />
			<button class='text-white bg-warning'>CONFIRM CHOICE</button>
		</form>
	</div>
	<% 
	}
	}
	%>

	<%
	if (request.getParameter("dateFrom") != null && request.getParameter("dateTo") != null) {
	%>
	<table class="table bordered">
		<thead>
			<tr>
				<th><abbr title="OrderNumber">Tour Name</abbr></th>
				<th><abbr title="TourName">Customer Purchased</abbr></th>
				<th><abbr title="TicketQuantity">Ticket Quantity</abbr></th>
				<th><abbr title="PurchaseDate">Purchase Date</abbr></th>
			</tr>
		</thead>
		<tbody>
			<%
			try {
			%>
			<%
			for (TourRecord i : displaySalesTour) {
			%>
			<%
			String tName = i.getTourname();
			String uName = i.getUsername();
			int tQuantity = i.getQuantity();
			String tPurchasedAt = i.getPurchased_at();
			%>
			<tr>
				<th><%=tName%></th>
				<td><%=uName%></td>
				<td><%=tQuantity%></td>
				<td><%=tPurchasedAt%></td>
			</tr>
			<%
			}
			%>
			<%
			} catch (Exception e) {
			response.sendRedirect("/assignment2_jad_client/admin_sales_filter");
			}
			%>
		</tbody>
	</table>
	<%
	}
	%>

	<%
	if (request.getParameter("salesfilter") != null && request.getParameter("salesfilter").equals("topValue")) {
	%>
	<h3 class='px-4'>By Value</h3>
	<table class="table bordered">
		<thead>
			<tr>
				<th><abbr title="UserID">User ID</abbr></th>
				<th><abbr title="Username">Username</abbr></th>
				<th><abbr title="TourID">Value</abbr></th>
			</tr>
		</thead>
		<tbody>
			<%
			try {
			%>
			<%
			for (User i : displaySalesUser) {
			%>
			<%
			int uID = i.getUserid();
			String uName = i.getUsername();
			double uValue = 0;
			if (i.getValue() > 0) {
				uValue = i.getValue();
			}
			%>
			<tr>
				<th><%=uID%></th>
				<td><%=uName%></td>
				<%
				if (i.getValue() > 0) {
				%>
				<td><%=uValue%></td>
				<%
				}
				%>
			</tr>
			<%
			}
			%>
			<%
			} catch (Exception e) {
			response.sendRedirect("/assignment2_jad_client/admin_sales_filter");
			}
			%>
		</tbody>
	</table>
	<%
	}
	%>
	
	<%
	if (request.getParameter("salesfilter") != null && request.getParameter("salesfilter").equals("topOrders")) {
	%>
	<h3 class='px-4'>By Orders</h3>
	<table class="table bordered">
		<thead>
			<tr>
				<th><abbr title="UserID">User ID</abbr></th>
				<th><abbr title="Username">Username</abbr></th>
				<th><abbr title="TourID">No. of Purchases</abbr></th>
			</tr>
		</thead>
		<tbody>
			<%
			try {
			%>
			<%
			for (User i : displaySalesUser) {
			%>
			<%
			int uID = i.getUserid();
			String uName = i.getUsername();
			int uQuantity = 0;
			if (i.getPurchases() > 0) {
				uQuantity = i.getPurchases();
			}
			%>
			<tr>
				<th><%=uID%></th>
				<td><%=uName%></td>
				<%
				if (i.getPurchases() > 0) {
				%>
				<td><%=uQuantity%></td>
				<%
				}
				%>
			</tr>
			<%
			}
			%>
			<%
			} catch (Exception e) {
			response.sendRedirect("/assignment2_jad_client/admin_sales_filter");
			}
			%>
		</tbody>
	</table>
	<%
	}
	%>
	
	<%
	if (request.getParameter("salesfilter") != null && request.getParameter("salesfilter").equals("topCategory")) {
	%>
	<h3 class='px-4'>Best and Least selling Categories</h3>
	<table class="table bordered">
		<thead>
			<tr>
				<th><abbr title="UserID">Category ID</abbr></th>
				<th><abbr title="Username">Category Name</abbr></th>
				<th><abbr title="TourID">Total Purchases</abbr></th>
				<th><abbr title="TourName">Total Orders</abbr></th>
			</tr>
		</thead>
		<tbody>
			<%
			try {
			%>
			<%
			for (TourRecord i : displaySalesTour) {
			%>
			<%
			int cID = i.getCategoryid();
			String cName = i.getCategory();
			int purchasedQuantity = i.getTotal_purchases();
			int totalOrders = i.getTotal_orders();
			%>
			<tr>
				<th><%=cID%></th>
				<td><%=cName%></td>
				<td><%=purchasedQuantity%></td>
				<td><%=totalOrders%></td>
			</tr>
			<%
			}
			%>
			<%
			} catch (Exception e) {
			response.sendRedirect("/assignment2_jad_client/admin_sales_filter");
			}
			%>
		</tbody>
	</table>
	<%
	}
	%>
	
	<%
	if (request.getParameter("salesTourid") != null) {
	%>
	<h3 class='px-4'>Users who booked a certain tour</h3>
	<table class="table bordered">
		<thead>
			<tr>
				<th><abbr title="UserID">User ID</abbr></th>
				<th><abbr title="Username">Username</abbr></th>
				<th><abbr title="TourID">Tour ID</abbr></th>
				<th><abbr title="TourName">Total Name</abbr></th>
				<th><abbr title="Quantity">Quantity</abbr></th>
				<th><abbr title="PurchasedAt">Purchased At</abbr></th>
			</tr>
		</thead>
		<tbody>
			<%
			try {
			%>
			<%
			for (TourRecord i : displaySalesTour) {
			%>
			<%
			int uID = i.getUserid();
			String uName = i.getUsername();
			int tID = i.getTourid();
			String tName = i.getTourname();
			int quantity = i.getQuantity();
			String purchasedAt = i.getPurchased_at();
			%>
			<tr>
				<th><%=uID%></th>
				<td><%=uName%></td>
				<td><%=tID%></td>
				<td><%=tName%></td>
				<td><%=quantity%></td>
				<td><%=purchasedAt%></td>
			</tr>
			<%
			}
			%>
			<%
			} catch (Exception e) {
			response.sendRedirect("/assignment2_jad_client/admin_sales_filter");
			}
			%>
		</tbody>
	</table>
	<%
	}
	%>
	
	<%
	if (request.getParameter("salesfilter") != null && request.getParameter("salesfilter").equals("byTour")) {
	%>
	<h3 class='px-4'>Order by Tours ASC</h3>
	<table class="table bordered">
		<thead>
			<tr>
				<th><abbr title="UserID">User ID</abbr></th>
				<th><abbr title="Username">Username</abbr></th>
				<th><abbr title="TourID">Tour ID</abbr></th>
				<th><abbr title="TourID">Tour Name</abbr></th>
				<th><abbr title="TourID">Quantity</abbr></th>
				<th><abbr title="TourID">Purchased At</abbr></th>
			</tr>
		</thead>
		<tbody>
			<%
			try {
			%>
			<%
			for (TourRecord i : displaySalesTour) {
			%>
			<%
			int uID = i.getUserid();
			String uName = i.getUsername();
			int tID = i.getTourid();
			String tName = i.getTourname();
			int quantity = i.getQuantity();
			String purchasedAt = i.getPurchased_at();
			%>
			<tr>
				<th><%=uID%></th>
				<td><%=uName%></td>
				<td><%=tID%></td>
				<td><%=tName%></td>
				<td><%=quantity%></td>
				<td><%=purchasedAt%></td>
			</tr>
			<%
			}
			%>
			<%
			} catch (Exception e) {
			response.sendRedirect("/assignment2_jad_client/admin_sales_filter");
			}
			%>
		</tbody>
	</table>
	<%
	}
	%>
	<%@ include file="footer.html"%>
</body>
</html>