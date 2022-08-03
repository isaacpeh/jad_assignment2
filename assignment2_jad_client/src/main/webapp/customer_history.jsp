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
			<tr>
				<th>1</th>
				<td>The Great Tour</td>
				<td>2</td>
				<td>2 August 2022</td>
			</tr>
		</tbody>
	</table>
	<%@ include file="footer.html"%>
</body>
</html>