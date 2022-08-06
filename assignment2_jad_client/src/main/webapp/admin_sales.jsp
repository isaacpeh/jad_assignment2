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
	<%
	// sales management results
	List<TourRecord> displaySalesTour = (ArrayList<TourRecord>) request.getAttribute("mgmtSalesTours");
	List<TourRecord> displaySalesUser = (ArrayList<TourRecord>) request.getAttribute("mgmtSalesUsers");
	%>
	<%@ include file="header.jsp"%>
	<%@ page import="model.TourRecord, model.TourRecordManager"%>
	<h1 align="center" style="margin-top: 5rem">Sales Management</h1>
	<div class="row px-4">
		<h4 class="m-0 mr-1" style="line-height: 2;">Sort By</h4>
		<form style='display: flex;'>
			<div class="col-12 level-item input-control">
				<select class="select" placeholder="Choose one" name="filterChoice">
					<option value="top">Top 10 Users</option>
					<option value="date">Sales By Date</option>
					<option value="tourByUsers">Tour bought by user</option>
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
			<input type="text" id="category" name="salesfilter" value="top"
				style="display: none;" />
			<button class='text-white bg-warning'>Are you sure?</button>
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
		  <h6>Please input Tour ID: </h6> <input type="text" name="salesTourid">
		  <button class='mt-2 bg-warning text-white'>Search</button>
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
	if (request.getParameter("salesTourid") != null) {
	%>
		<table class="table bordered">
		<thead>
			<tr>
				<th><abbr title="UserID">User ID</abbr></th>
				<th><abbr title="Username">Username</abbr></th>
				<th><abbr title="TourID">Tour ID</abbr></th>
				<th><abbr title="TourName">Tour Name</abbr></th>
				<th><abbr title="TicketQuantity">Ticket Quantity</abbr></th>
				<th><abbr title="PurchaseDate">Purchase Date</abbr></th>
			</tr>
		</thead>
		<tbody>
			<%
			try {
			%>
			<%
			for (TourRecord i : displaySalesUser) {
			%>
			<%
			int uID = i.getUserid();
			int tID = i.getTourid();
			String tName = i.getTourname();
			String uName = i.getUsername();
			int tQuantity = i.getQuantity();
			String tPurchasedAt = i.getPurchased_at();
			%>
			<tr>
				<th><%=uID%></th>
				<td><%=uName%></td>
				<td><%=tID%></td>
				<td><%=tName%></td>
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
	<% } %>
	<%@ include file="footer.html"%>
</body>
</html>