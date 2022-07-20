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
<title>Tours</title>
</head>
<body>
	<%@ page import="model.Tour, java.util.*" %>
	<%@ include file="header.jsp" %>
	
	<%--space --%>
	<div class="space space--xl ..."></div>
	<div class="space space ..."></div>

	<%--title --%>
	<div style="text-align: center">
		<h1>Tours🏌</h1>
	</div>
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<div class="grid grid-cols-3 u-gap-2">
	
	<%
		List<Tour> displayTours = (ArrayList<Tour>)request.getAttribute("reqTours");
		String pic_url = "";
		try {	
			for(Tour i : displayTours){
				if (i.getPicUrl().get(0).contains("default")){
					pic_url = "./images/default_tour.jpg";
				} else {
					pic_url = i.getPicUrl().get(0);
				}
				%>
					<div class="card card--slide-up ">
					<div class="card__container">
						<div class="card__image"
							style="background-image: url(<%=pic_url%>;);"></div>
					</div>
					<div class="card__mobile-title">
						<div class="content">
							<div class="tile">
								<div class="tile__container px-1">
									<p class="tile__title"><%=i.getTourName() %></p>
								</div>
							</div>
						</div>
					</div>
					<div class="card__body content px-1">
						<p><%=i.getdDescription() %></p>
					</div>
					<div class="card__footer content" style="text-align: center">
						<a href="#<%=i.getTourid() %>-modal"><button>More Pictures</button></a>
						<a href="TourShowOneController?tourid=<%= i.getTourid() %>"><button class="btn-info">More details</button></a>
					</div>

				</div>
				<div class="modal modal-large " id="<%=i.getTourid() %>-modal">
					<a href="#searchModalDialog" class="modal-overlay close-btn"
						aria-label="Close"></a>
					<div class="modal-content" role="document">
						<div class="modal-header">
							<a href="#components" class="u-pull-right" aria-label="Close"><span
							class="icon"><svg aria-hidden="true" focusable="false"	
							data-prefix="fas" data-icon="times"
							class="svg-inline--fa fa-times fa-w-11 fa-wrapper" role="img"
							xmlns="http://www.w3.org/2000/svg" viewBox="0 0 352 512">
							<path fill="currentColor"
								d="M242.72 256l100.07-100.07c12.28-12.28 12.28-32.19 0-44.48l-22.24-22.24c-12.28-12.28-32.19-12.28-44.48 0L176 189.28 75.93 89.21c-12.28-12.28-32.19-12.28-44.48 0L9.21 111.45c-12.28 12.28-12.28 32.19 0 44.48L109.28 256 9.21 356.07c-12.28 12.28-12.28 32.19 0 44.48l22.24 22.24c12.28 12.28 32.2 12.28 44.48 0L176 322.72l100.07 100.07c12.28 12.28 32.2 12.28 44.48 0l22.24-22.24c12.28-12.28 12.28-32.19 0-44.48L242.72 256z"></path></svg></span></a>
							<div class="modal-title"><%=i.getTourName() %></div>
						</div>
					<div class="modal-body">
						<% int picNum = i.getPicUrl().size();%>
						<%if(picNum == 1){
							%>
							<div>
								<img alt="" src="<%=i.getPicUrl().get(0) %>">
							</div>
						<%} 
						else if(picNum != 1 || picNum !=0) {%>
							<div class="grid grid-cols-2 u-gap-2">
							<%for(int z = 0; z< i.getPicUrl().size(); z++){ %>
								<div>
									<img alt="" src="<%=i.getPicUrl().get(z) %>">
								</div>
								<%} %>
							</div>
						<%} %>
							
					</div>
					<div class="modal-footer u-text-right">
							<a href="#components" class="u-inline-block">
							<button class="btn--sm">Close</button></a>
					</div>
				</div>
			</div>
				
				<%
			}	
		} catch (Exception ex){
			response.sendRedirect("tours");
		}
	%>
	</div>
		</div>
		<div class="col-1"></div>
	</div>
		<%@ include file="footer.html"%>
</body>
</html>