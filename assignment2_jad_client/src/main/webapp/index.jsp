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
<title>Home</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0" />
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge;" />
<link href="https://unpkg.com/cirrus-ui" type="text/css"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito+Sans:200,300,400,600,700"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/carousel.css">

</head>
<body>
	
	<%@ page import="model.Category, java.util.*" %>
	<%@ include file="header.jsp" %>
	
	<%
		List<Category> result = (ArrayList<Category>) request.getAttribute("reqCategories");

		if (request.getSession().getAttribute("sessUserID") != null){
			out.println("Welcome UserID: " + request.getSession().getAttribute("sessUserID"));
		};
	%>
	
	
	<%--Carousel --%>

	<div>
		<!-- Slideshow container -->
		<div class="slideshow-container">

			<!-- Full-width images with number and caption text -->
			<div class="mySlides fade">
				<div class="numbertext">1 / 3</div>
				<img src="images/image1.jpg">
			</div>

			<div class="mySlides fade">
				<div class="numbertext">2 / 3</div>
				<img src="images/image2.jpg">
			</div>

			<div class="mySlides fade">
				<div class="numbertext">3 / 3</div>
				<img src="images/image3.jpg">
			</div>

			<!-- Next and previous buttons -->
			<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a class="next"
				onclick="plusSlides(1)">&#10095;</a>
		</div>
		<br>

		<!-- The dots/circles -->
		<div style="text-align: center">
			<span class="dot" onclick="currentSlide(1)"></span> 
			<span class="dot" onclick="currentSlide(2)"></span> 
			<span class="dot" onclick="currentSlide(3)"></span>
		</div>
		<script type="text/javascript" src="js/carousel.js"></script>
	</div>

	<%--space --%>
	<div class="space space--lg ..."></div>

	<%--search label --%>
	<div class="row">
		<div class="col-2"></div>
		<div class="col-8">
			<h3>Search your Tour 🛫</h3>
		</div>
		<div class="col-2"></div>
	</div>
	<%--search --%>

	<div class="row">
		<div class="col-2"></div>
		<div class="col-8">
			<form action="/assignment2_jad_client/tours" method="get">
				<div class="form-group">
						<input class="form-group-input input--lg" style="width: 250%" type="text" id="search" name="key"> 
						<select id="category" name="catid" class="form-group-input input--lg" placeholder="Choose one">
						<option value="0">Category</option>
						<% try { %>
			
							<% for (Category i : result){  %>
								<% int catid = i.getCatID(); %>
								<% String category = i.getCategoryName(); %>
								<option value=<%=catid %>><%=category %></option>
							<% } %>
							
						<% } catch (Exception ex) { %>
								<% response.sendRedirect("index"); %>
						<% } %>
					</select>
					<button class="form-group-btn btn-info btn--lg" type="submit">Go</button>
				</div>
			</form>
		</div>
		<div class="col-2"></div>
	</div>
	<%--space --%>
	<div class="space space--lg ..."></div>
	<div class="space space--lg ..."></div>

	<%--Category --%>
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<div class="grid grid-cols-3 u-gap-2">
				<%
					try {
						for(int i=0; i < 2; i++){ 
							
					%>
						<div class="card">
							<div class="card__container">
								<div class="card__image"
									style="background-image: url(./images/Category<%=i+1 %>.jpeg);"></div>
							</div>
							<div class="card__mobile-title">
								<div class="content">
									<div class="tile">
										<div class="tile__container" style="text-align:center; margin-top:5px">
											<p class="tile__title"><%=result.get(i).getCategoryName() %></p>
										</div>
									</div>
								</div>
							</div>
							<div class="card__footer content" style="text-align:center">
								<a href=tours?catid=<%=result.get(i).getCatID() %>&key=><button>Find out more</button></a>
							</div>
						</div>
						<% }
						
					} catch (Exception ex){
					
					}
					
				%>
				
				
				<div style="text-align:center">
					<div style="margin-top:150px">
					<h1><a href="categories.jsp">More Categories.➡</a></h1>
					</div>
				</div>
			</div>

		</div>
		<div class="col-1"></div>
	</div>
<%@ include file="footer.html" %>
</body>
</html>