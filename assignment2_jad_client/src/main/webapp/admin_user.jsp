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

	<%@ page import="model.User, model.UserManager, java.util.*" %>
	<%@ include file="admin_header.jsp" %>
	
	<%	
		List<User> result = (List<User>) request.getAttribute("reqUsers");
	%>
		
	<%--space --%>
	<div class="space space--xl ..."></div>
	<div class="space space ..."></div>

	<%--title --%>
	<div style="text-align: center">
		<h1>Users</h1>
	</div>
	
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<table class="table bordered">
				<thead>
					<tr>
						<th>User ID</th>
						<th>User Name</th>
						<th>Contact</th>
						<th>Email</th>
						<th>Role</th>
						<th><a href="#filter-modal"><button class="btn-link">Filter</button></a></th>
					</tr>
				</thead>
				<tbody>
					<% try { %>	
						<% for (User i : result){  %>
							<tr>
								<% int uid = i.getUserid(); %>
								<% String uName = i.getUsername(); %>
								<% String uContact = i.getContact(); %>
								<% String uEmail = i.getEmail(); %>
								<% String uRole = i.getRole(); %>
								
								<td><%=uid %></td>
								<td><%=uName %></td>
								<td><%=uContact %></td>
								<td><%=uEmail %></td>
								<td><%=uRole %></td>
								<td><a href="#delete-<%=uid %>-modal"><button class="btn-danger">Delete User</button></a></td>
							
							</tr>
							
							<div class="modal" id="delete-<%=uid %>-modal">
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
										<div class="modal-title">Delete User</div>
									</div>
									<div class="modal-body">
										<div>
											<p>Delete <%=uName %>?</p>
										</div>
						
									</div>
									<div class="modal-footer u-text-right">
										<a href="#components" class="u-inline-block">
											<button class="btn--sm">Cancel</button>
										</a> <a class="u-inline-block">
											<form action="/assignment1_jad/UserDeleteController" method="post">
												<button class="btn--sm btn-danger" type="submit" name="delete_user" value=<%=uid %>>Delete</button>
											</form>
										</a>
									</div>
								</div>
							</div>
							
						<% } %>
					<% } catch (Exception ex) { %>
						<% response.sendRedirect("admin_user"); %>
					<% } %>
				</tbody>
			</table>
			<%--</form> --%>
			
							<%--Filter modal --%>
							<div class="modal modal-large" id="filter-modal">
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
										<div class="modal-title">Apply Filter</div>
									</div>
									<form>
										<div class="modal-body" style="width:500px">
											<input type="radio" name="userfilter" id="name" value="name" onclick="createUserNameInput()"><label for="name">Search User by name</label><br> 
											<div id="nameInputContainer"></div>
											<input type="radio" name="userfilter" id="phNumber" value="phNumber" onclick="createUserPhoneNumberInput()"><label for="phNumber" onclick="">Show users by phone number </label><br> 
											<div id="numberInputContainer"></div>
											<input type="radio" name="userfilter" id="inactive" value="inactive" onclick="deleteInputFields()"><label for="inactive">Show inactive User</label><br> 
											<input type="radio" name="userfilter" id="address" value="address" onclick="deleteInputFields()"><label for="address">Show user by address</label><br>
											
										</div>
										<div class="modal-footer u-text-right">
											<a class="u-inline-block">
												<button class="btn--sm btn-info" type="submit" id="applyBtn">Apply</button>
											</a>
										</div>
									</form>
								</div>
							</div>
		</div>
		<div class="col-1"></div>
	</div>
	<script>
		function createUserNameInput(){
			var new_input = "<label for='slotsInput'>Enter Name: </label><br><input type='text' name='nameInput' id='nameInput' oninput='validateNameInput()'><div id='nameErrMsg' style='color:red'></div>";
			$('#nameInputContainer').append(new_input);
			document.getElementById("numberInputContainer").innerHTML = "";
		}
		function createUserPhoneNumberInput(){
			var new_input = "<label for='numberInput'>Enter Number: </label><br><input type='text' name='numberInput' id='numberInput' oninput='validateNumberInput()'><div id='numberErrMsg' style='color:red'></div>";
			$('#numberInputContainer').append(new_input);
			document.getElementById("nameInputContainer").innerHTML = "";
		}
		function validateNameInput(){
			var nameInput = document.getElementById("nameInput").value;
			if(nameInput==""){
				document.getElementById("nameErrMsg").innerHTML = "Please enter valid input!";
			}
			else{
				document.getElementById("nameErrMsg").innerHTML = "";
			}
		}
		function validateNumberInput(){
			var slotsInput = parseInt(document.getElementById("numberInput").value);
			if(isNaN(slotsInput)){
				document.getElementById("numberErrMsg").innerHTML = "Please enter numbers only! eg.12341234";
			}
			else{
				document.getElementById("numberErrMsg").innerHTML = "";
			}
		}
		function deleteInputFields(){
			document.getElementById("nameInputContainer").innerHTML = "";
			document.getElementById("numberInputContainer").innerHTML = "";
		}
	</script>
	<%@ include file="footer.html"%>
</body>
</html>