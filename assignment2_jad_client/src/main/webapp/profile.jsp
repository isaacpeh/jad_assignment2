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
<link rel="stylesheet" href="./css/profile.css" />
<title>User Profile</title>
</head>
<body>

	<%@ page import="model.User, model.UserManager"%>
	<%@ include file="header.jsp"%>

	<%
	User userProfile = (User) request.getAttribute("reqProfile");
	String username = "", email = "", contact = "", img = "", password = "";

	try {
		username = userProfile.getUsername();
		email = userProfile.getEmail();
		contact = userProfile.getContact();
		img = userProfile.getPicUrl();
		password = userProfile.getPassword();

	} catch (Exception ex) {
		response.sendRedirect("profile");
	}
	out.println("Profile Pic!:" + img + "<br>");

	request.setAttribute("old_password", password);
	request.setAttribute("image", img);
	%>

	<div class="page-container row h-screen p-0">
		<div class="customerWelcome col-6 text-white"
			style="display: flex; justify-content: center; align-items: center">
			<h1 style="font-size: 72px">
				Holla, <br /><%=username%>.
			</h1>
		</div>
		<div class="col-6">
			<div class="customerDetails mt-6">
				<div class="card w-80p p-4">
					<h2>User Profile</h2>
					<div class="profile_pic">
						<img src="./images/user_profile_pics/<%=img%>" alt="">
					</div>
					<form action="/assignment2_jad_client/UserUpdateController"
						method="post" enctype="multipart/form-data">
						<h6 class="m-0">Username</h6>
						<div class="row level">
							<input type="name" name="update_loginid" value=<%=username%> />
						</div>
						<h6 class="m-0">Email</h6>
						<div class="row level">
							<input type="email" name="update_email" value=<%=email%> />
						</div>
						<h6 class="m-0">Phone No.</h6>
						<div class="row level">
							<input type="text" name="update_contact" value=<%=contact%> />
						</div>
						<h6 class="m-0">Profile Pic</h6>
						<div class="row level">
							<input type="file" name="update_pic" /> <input type="hidden"
								name="current_pic" value=<%=img%> />
						</div>
						<h6 class="m-0">Password</h6>
						<div class="row level">
							<input type="password" name="update_password" value=<%=password%> />
						</div>
						<button class="btn-info text-white u-pull-right">CONFIRM
							EDIT</button>
					</form>
					<%
					String errCode = request.getParameter("errCode");
					System.out.println("errCode: " + errCode);
					String msg = "";
					UserManager um = new UserManager();
					if (errCode != null) {
						if (errCode.equals("incorrectType")) {
							msg = "Incorrect Contact Number Type";
						} else if (errCode.equals("failedAuthentication")) {
							msg = "Both passwords do not match!";
						} else if (errCode.equals("failedUpdate")) {
							msg = "Failed to update";
						} else if (errCode.equals("duplicateUpdate")) {
							msg = "Duplicate update";
						} else if (errCode.equals("userNotFound")) {
							msg = "Unable to find user";
						}
					%>
					<div class="row level text-danger w-100p">
						<h6 style="margin: auto;"><%=msg%></h6>
					</div>
					<%
					}
					%>
				</div>

			</div>
			<div style="display: flex; justify-content: center;">
				<div class="card w-80p purchaseHistory p-2" style="display: flex; justify-content: space-between; align-items: center;">
					<h3 class="m-0">View Purchase History</h3>
					<i class="fas fa-chevron-right fa-2x"></i>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>