<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Purchase History</title>
<link href="https://unpkg.com/cirrus-ui" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ page import="model.TourRecord, model.TourRecordManager"%>
	<%
	ArrayList<TourRecord> salesRecord = (ArrayList<TourRecord>) request.getAttribute("reqProfileRecords");
	%>
	<h1 align="center" style="margin-top: 5rem;">Order History</h1>
	<table class="table bordered">
		<thead>
			<tr>
				<th><abbr title="OrderNumber">Order Number</abbr></th>
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
			for (TourRecord i : salesRecord) {
			%>
			<%
			int tID = i.getTourid();
			String tName = i.getTourname();
			int tQuantity = i.getQuantity();
			String tPurchasedAt = i.getPurchased_at();
			%>
			<tr>
				<th><%=tID%></th>
				<td><%=tName%></td>
				<td><%=tQuantity%></td>
				<td><%=tPurchasedAt%></td>
			</tr>
			<%
			}
			%>
			<%
			} catch (Exception e) {
			response.sendRedirect("/assignment2_jad_client/TourRecordShowOneController");
			}
			%>
		</tbody>
	</table>
	<%@ include file="footer.html"%>
</body>
</html>