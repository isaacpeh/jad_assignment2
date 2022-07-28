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

<title>Login to Tourism.</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div class="page-container row h-screen p-0">
		<div class="loginForm col-6"
			style="display: flex; justify-content: center; align-items: center">
			<div class="card w-80p p-4">
				<h2>Welcome Back!</h2>
				<p>Excited to book your next trip? Make sure to not miss out on
					our discounts for the month of June! Definitely don't want to miss
					it!</p>
					
				<form action="/assignment2_jad_client/VerifyUserController" method="get">
					<h6 class="m-0">Username</h6>
					<div class="row level">
						<input type="name" name="loginid" required/>
					</div>
					<h6 class="m-0">Password</h6>
					<div class="row level">
						<input type="password" name="password" required />
					</div>
					<button class="btn-info mt-2 u-pull-right">SIGN IN</button>
				</form>
				<div class="mt-3">
					<p>
						New here?<a href="./signup.jsp" class="u u-LR"> Sign up here</a>
					</p>
				</div>
				
				<%
				String errCode = request.getParameter("errCode");
				if (errCode != null && errCode.equals("failedLogin")) {
				%>
				<div class="row level text-danger w-100p">
					<h6 style="margin: auto;">Please input valid details!</h6>
				</div>
				<%
				} else if (errCode != null && errCode.equals("logout")) {
					session.invalidate();
					response.sendRedirect("login.jsp");
				}
				%>
			</div>
		</div>
		<div class="customersFeedback col-6 bg-info text-white">
			<h1
				style="font-size: 96px; transform: translate(-50%, -60%); right: 15%"
				class="u-absolute u-top-50p">
				Book a <br />Dream.
			</h1>
			<div class="feedback w-60p">
				<p class="u-absolute w-30p" style="bottom: 15%; right: 5%">
					Tourism. is the best website to book your tours, they helped me
					consolidate the details of the tours which helped me save time
					doing any research! Furthermore, they have many attractive
					discounts throughout the year. Thus, highly recommended!</p>
			</div>
			<div class="tile m-0 level u-absolute"
				style="right: 5%; bottom: 10%;">
				<div class="tile__icon">
					<figure class="avatar">
						<img
							src="https://images.unsplash.com/profile-1495427732560-fe5248ad6638?dpr=1&amp;auto=format&amp;fit=crop&amp;w=256&amp;h=256&amp;q=60&amp;cs=tinysrgb&amp;crop=faces&amp;bg=fff" />
					</figure>
				</div>
				<div class="tile__container">
					<p class="tile__title m-0">Nathan Dumlao</p>
					<p class="tile__subtitle m-0">
						<a href="!#" class="text-white">@nate_dumlao</a>
					</p>
				</div>
			</div>
		</div>
	</div>
		<%@ include file="footer.html"%>
</body>
</html>