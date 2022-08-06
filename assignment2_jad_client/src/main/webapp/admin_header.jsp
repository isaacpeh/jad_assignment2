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
<title>Insert title here</title>

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
	<%@ page import="java.util.*" %>
		
	<%-- Header --%>
	<div class="header header-fixed unselectable header-animated">
		<div class="header-brand">
			<div class="nav-item no-hover">
				<a href="index.jsp"><h6 class="title">Tourism.</h6></a>
			</div>
			<div class="nav-item nav-btn" id="header-btn">
				<span></span> <span></span> <span></span>
			</div>
		</div>
		<div class="header-nav" id="header-menu">
			<% 	try{
				int userid = (int)session.getAttribute("sessUserID");
				//Without login
				if(userid==0){
					
				%>
					<div class="nav-right">
						
						<div class="nav-item">
							<a href="login.jsp">Login</a>
						</div>
					</div>
				<%
					
				}
				//with login
				else{
					//Admin user
					
						%>
							<div class="nav-right">
								<div class="nav-item has-sub toggle-hover" id="dropdown">
					                <a class="nav-dropdown-link">Admin Menu</a>
					                <ul class="dropdown-menu dropdown-animated" role="menu">
					                <li role="menu-item"><a href="admin_tours">Admin Tours</a></li>
							        <li role="menu-item"><a href="admin_user">Admin Users</a></li>
					                </ul>
					            </div>
								
								<div class="nav-item text-center">
									<a href="profile.jsp"> <i class="fa fa-user-circle-o"
										style="font-size: 20px"></i>
									</a>
								</div>
								<div class="nav-item">
									<a href="login.jsp?errCode=logout">Logout</a>
								</div>
							</div>
						<%
						
						
					}
				
				
			//if session values are null(not logged in)
			}catch(NullPointerException e){
				%>
				<div class="nav-right">
								<div class="nav-item">
									<a href="login.jsp">Login</a>
								</div>
							</div>
				<%
			}
				
			%>	
				
			
		</div>
	</div>
</body>
</html>