<!-- 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 -->

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
<link rel="stylesheet" href="./css/tour_details.css" />
<title>Tour Details</title>
</head>
<body>
	<%@ page import="model.Tour, java.util.*, model.TourManager"%>
	<%@ include file="header.jsp"%>
	
	<%
	Tour tour = (Tour) request.getAttribute("reqTour");
	String tourname = "", tourdSummary = "", tourbSummary = "",tourid="";
	
	double tourPrice = 0;
	int slotsAvailable = 0;

	try {
		tourid = Integer.toString(tour.getTourid());
		tourname = tour.getTourName();
		tourdSummary = tour.getdDescription();
		tourbSummary = tour.getdDescription();
		tourPrice = tour.getPrice();
		slotsAvailable = tour.getSlotsAvailable();
	} catch (Exception ex) {
		response.sendRedirect("TourShowOneController");
	}
	%>

	<div class="hero-banner">
		<img
			src="https://images.unsplash.com/photo-1642509963447-1349649101f2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80" />
		<h1><%=tourname %></h1>
	</div>
	<div class="tour-container row mt-2">
		<div class="col-6 offset-3">
			<div class="tour-tags">
				<div class="tag tag--primary">NEW</div>
			</div>
			<div class="tour-content mt-2">
				<h1><%=tourname%></h1>
				<h5>Tour Summary</h5>
				<p><%=tourdSummary%></p>
			</div>
			<hr />
			<div class="tour-pricing mt-2" style="display: flex; justify-content: space-between;">
				<h5>Tour Price</h5>
				<h5 class="text-info">$<%=tourPrice %></h5>
			</div>
			<div class="tour-availability" style="display: flex; justify-content: space-between;">
				<h5>Slots Available</h5>
				<%if (slotsAvailable == 0)  { %>
								<h5 class="text-danger"><%=slotsAvailable %></h5>
				<% } else if (slotsAvailable <= 10) {%>
												<h5 class="text-warning"><%=slotsAvailable %></h5>
				<% }  else { %>
												<h5 class="text-success"><%=slotsAvailable %></h5>
				<% } %>

			</div>
		</div>
		<div class="col-3">
			<div class="card p-2">
				<div class="card-header"
					style="display: flex; justify-content: space-between;">
					<h6>Tour Precautions</h6>
					<i class="fas fa-exclamation-triangle"></i>
				</div>
				<hr>
				<div class="tour-precaution-details mb-1">
					<p>This tour will have certain precautions that you would have
						to abide by due to prevent from falling sick or getting into
						accidents</p>
					<a href="!#">Learn More</a>
				</div>
				<hr></hr>
				<div class="enrollment">
					<p>If all is good and wish to enroll, you can do so by clicking
						the button below!</p>
					<div class="row" style="justify-content:space-between">
					
						
					<div class="col-12">
						<a href="CartAddController?tourid=<%=tourid %>">
							<button class="btn-link" >Add to cart</button>
						</a>
					</div>
					
					</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>