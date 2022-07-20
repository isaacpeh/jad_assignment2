<!-- 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="controller.DatabaseConfig"%>
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
<link rel="stylesheet" href="./css/categories.css" />
<title>Categories</title>
</head>
<body>
	<%@ page import="model.Category, java.util.*"%>
	<%@ include file="header.jsp"%>
	<div class="hero-banner">
		<img
			src="https://images.unsplash.com/photo-1569949381669-ecf31ae8e613?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80" />
		<h1>Categories</h1>
	</div>
	<%
	List<Category> result = (ArrayList<Category>) request.getAttribute("reqCategories");

	try {
	%>
	<div class="tours-container row">
		<%
		for (Category i : result) {
		%>
		<div class="card-container col-4">
			<div class="card card--slide-up">
				<div class="card__container">
					<div class="card__image"
						style="background-image: url(./images/Category<%=i.getCatID()%>.jpeg);"></div>
				</div>
				<div class="card__mobile-title">
					<div class="content">
						<div class="tile">
							<div class="tile__container px-1">
								<p class="tile__title"><%=i.getCategoryName()%></p>
							</div>
						</div>
					</div>
				</div>
				<div class="card__body content px-1">
					<p><%=i.getDescription()%></p>
				</div>
				<div class="card__footer content m-0">
					<a href="tours?catid=<%=i.getCatID()%>"
						class="btn u-pull-right mr-2 bg-info text-white">Learn More</a>
				</div>
			</div>
		</div>
		<%
		}

		} catch (Exception ex) {
		response.sendRedirect("categories");
		}
		%>
	</div>
	
	<%@ include file="footer.html"%>
</body>
</html>