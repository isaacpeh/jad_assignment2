<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ page import="model.TourRecord, model.TourRecordManager"%>
	<!-- TODO: Need to check why customerOrders is getting null -->
	<%
	List<TourRecord> customerOrders = (List<TourRecord>) request.getAttribute("reqProfileRecords");
	%>
	<h1 align="center" style="margin-top: 5rem;">Order History</h1>
	<table class="table bordered mx-4">
		<thead>
			<tr>
				<th><abbr title="OrderNumber">Order Number</abbr></th>
				<th><abbr title="TourName">Tour Name</abbr></th>
				<th><abbr title="TicketQuantity">Ticket Quantity</abbr></th>
				<th><abbr title="PurchaseDate">Purchase Date</abbr></th>
			</tr>
		</thead>
		<tbody>
			<%for (TourRecord i : customerOrders) { %>
				<% int tID = i.getTourid(); %>
				<% String tName = i.getTourname(); %>
				<% int tQuantity = i.getQuantity(); %>
				<% String tPurchasedAt = i.getPurchased_at(); %>
			<tr>
				<th><%=tID %></th>
				<td><%=tName %></td>
				<td><%=tQuantity %></td>
				<td><%=tPurchasedAt %></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<%@ include file="footer.html"%>
</body>
</html>