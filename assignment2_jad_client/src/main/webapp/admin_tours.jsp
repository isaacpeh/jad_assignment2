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


</head>
<body>
	<%@ page import="model.Tour, model.Category,java.util.*"%>
	<%@ include file="admin_header.jsp"%>
	
	<%--space --%>
	<div class="space space--xl ..."></div>
	<div class="space space ..."></div>

	<%--title --%>
	<div class="row">
		<div class="col-1"></div>
		<div class="col-5" style="text-align: center" ><h1>Admin Tours</h1></div>
		<div class="col-5" style="text-align: right">
		<a href="#add-category-modal"><button class="btn-info">Add category</button></a>
		<a href="#delete-category-modal"><button class="btn-danger">Delete category</button></a>
		<a href="#Add-modal"><button class="btn-success">Add Tour</button></a>
		</div>
		<div class="col-1"></div>
	</div>
	
	<div class="space space--lg ..."></div>

	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<table class="table bordered">
				<thead>
					<tr>
						<th>Tour ID</th>
						<th>Tour Name</th>
						<th>Brief description</th>
						<th>detailed description</th>
						<th>price</th>
						<th>Available Slots</th>
						<th>Image path</th>
						<th></th>
						<th></th>
					</tr>	
				</thead>
				<%
				List<Tour> displayTours = (ArrayList<Tour>) request.getAttribute("reqTours");
				List<Category> displayCategory = (ArrayList<Category>) request.getAttribute("reqCategories");

				boolean redirect = false;
				try {
					for (Tour i : displayTours) {
				%>

				<tbody>
					<tr>
						<th><%=i.getTourid()%></th>
						<td><%=i.getTourName()%></td>
						<td><%=i.getbDescription()%></td>
						<td><%=i.getdDescription()%></td>
						<td><%=i.getPrice()%></td>
						<td><%=i.getSlotsAvailable()%></td>
						<td>
							<%
							for (int z = 0; z < i.getPicUrl().size(); z++) {
								out.println(i.getPicUrl().get(z));
							}
							%>
						</td>
						<td><a href="#update-<%=i.getTourid() %>-modal"><button class="btn-info">UPDATE</button></a></td>
						<td><a href="#delete-<%=i.getTourid() %>-modal"><button class="btn-primary">DELETE</button></a></td>
					</tr>
					<%--Delete modal --%>
					<div class="modal" id="delete-<%=i.getTourid() %>-modal">
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
										<div class="modal-title">Delete Tour</div>
									</div>
									<div class="modal-body">
										<div>
											<p>Delete <%=i.getTourName() %>?</p>
										</div>
						
									</div>
									<div class="modal-footer u-text-right">
										<a href="#components" class="u-inline-block">
											<button class="btn--sm">Cancel</button>
										</a> 
										<a class="u-inline-block">
											<form action="/assignment1_jad/TourDeleteController" method="post">
												<button class="btn--sm btn-danger" type="submit" name="delete_tour" value=<%=i.getTourid() %>>Delete</button>
											</form>
										</a>
									</div>
								</div>
							</div>
							
					<%--Update modal --%>
					<div class="modal --lg" id="update-<%=i.getTourid() %>-modal">
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
								<div class="modal-title">Update Tour</div>
							</div>
							<div class="modal-body">
								<form action="/assignment1_jad/TourUpdateController" method="post">
									Tour Name: <input type="text" name="update_tourName" value="<%=i.getTourName() %>"> <br>
									Brief Description: <input type="text" name="update_tourBDescription" value="<%=i.getbDescription() %>">
									<br> Detailed Description: <input type="text"
										name="update_tourDDescription" value="<%=i.getdDescription() %>"> <br> 
										Tour Price: <input type="text" name="update_tourPrice" value="<%=i.getPrice() %>"> <br> Slots
									Available: <input type="text" name="update_tourSlots" value="<%=i.getSlotsAvailable() %>"> <br>
									<br> Category : <br>
				
									<%
									try {
									%>
									<%
										for (Category j : displayCategory) {
										%>
											<%
											String catName = j.getCategoryName();
											%>
											<%
											String catDes = j.getDescription();
											%>
											<%
											int catid = j.getCatID();
											
											
											
												if(i.getCategoryid().contains(catid)){
													%>
													<input type="checkbox" name="update_tourCategory" value=<%=catid%> checked>
													<% 
												}
												else{
													%>
													<input type="checkbox" name="update_tourCategory" value=<%=catid%> >
											
													<% 
												}
											%>
											<label><%=catName%></label><br>
											<%
											
										}
										%>
									<%
									} catch (Exception ex) {
									%>
									<%
									redirect = true;
									%>
									<%
									}
									%>
				
									Image URL: <%for (int z = 0; z < i.getPicUrl().size(); z++) {
										%>
										<input type="text" name="update_tourImg" value="<%=i.getPicUrl().get(z) %>"> <br>
										<% 
									} %>
								
							</div>
							<div class="modal-footer u-text-right">
								<a class="u-inline-block">
									<button class="btn--sm btn-info" type="submit">Update</button>
								</a>
							</div>
							</form>
						</div>
					</div>
				</tbody>
				
				<%
				}
				} catch (Exception ex) {
				redirect = true;
				}
				%>
			</table>
		</div>
		<div class="col-1"></div>
	</div>

	<%--Add new Tour modal --%>
	<div class="modal" id="Add-modal">
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
				<div class="modal-title">Add Tour</div>
			</div>
			<div class="modal-body">
				<form action="/assignment1_jad/TourAddController" method="post">
					Tour Name: <input type="text" name="new_tourName"> <br>
					Brief Description: <input type="text" name="new_tourBDescription">
					<br> Detailed Description: <input type="text"
						name="new_tourDDescription"> <br> Tour Price: <input
						type="text" name="new_tourPrice"> <br> Slots
					Available: <input type="text" name="new_tourSlots"> <br>
					<br> Category : <br>

					<%
					try {
					%>
					<%
						for (Category i : displayCategory) {
						%>
							<%
							String catName = i.getCategoryName();
							%>
							<%
							String catDes = i.getDescription();
							%>
							<%
							int catid = i.getCatID();
							%>
							<input type="checkbox" name="new_tourCategory" value=<%=catid%>>
							<label><%=catName%></label><br>
							<%
						}
						%>
					<%
					} catch (Exception ex) {
					%>
					<%
					redirect = true;
					%>
					<%
					}
					%>
					
					Image URL: <a class="btn ml-24" onclick="add()">add more image</a>
					<div id="new_input_field">
						<br> <input type="text" class="inputField" name="new_tourImg" id="1">
					</div>
					<div id="container">
						<p id="alertMsg" style="color:red"></p>
					</div>
					<script>
					function add(){
						  const element = document.getElementById("new_input_field");
						  
						  var currentInput = element.lastElementChild.id;
						  if(document.getElementById(""+currentInput).value.length == 0){
							  document.getElementById('alertMsg').innerHTML="Please fill in the blank!";
						  }	
						  else{
						  currentInput++
						  document.getElementById('alertMsg').innerHTML="";
					      var new_input="<br> <input type='text' name='new_tourImg' id='"+currentInput+"'>";
					      $('#new_input_field').append(new_input);
					      currentInput++
					      }
					    }
					</script>
				
			</div>
			<div class="modal-footer u-text-right">
				<a class="u-inline-block">
					<button class="btn--sm btn-info" type="submit">Add</button>
				</a>
			</div>
			</form>
		</div>
	</div>
	
	<%--Add new category modal --%>
	<div class="modal" id="add-category-modal">
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
				<div class="modal-title">Add category</div>
			</div>
			<div class="modal-body">
				<form action="/assignment1_jad/CategoryAddController" method="post">
					Category Name: <input type="text" name="new_category"> <br>
					Description: <input type="text" name="new_description">
				
			</div>
			<div class="modal-footer u-text-right">
				<a class="u-inline-block">
					<button class="btn--sm btn-info" type="submit">Add</button>
				</a>
			</div>
			</form>
		</div>
	</div>
	
	<%--Delete Category modal --%>
	<div class="modal" id="delete-category-modal">
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
				<div class="modal-title">Delete Category</div>
			</div>
			<div class="modal-body">
				<form action="/assignment1_jad/CategoryDeleteController" method="post">
				<%for(Category i : displayCategory){
					%>	
					 <input type="radio" name="delete_category" value="<%=i.getCatID() %>"><%=i.getCategoryName() %><br>
				<% }%>
				
				
			</div>
			<div class="modal-footer u-text-right">
				<a class="u-inline-block">
					<button class="btn--sm btn-info" type="submit">Delete</button>
				</a>
			</div>
			</form>
		</div>
	</div>

	<%
	if (redirect) {
		//System.out.println("admin_tours");
	}
	%>

</body>
</html>