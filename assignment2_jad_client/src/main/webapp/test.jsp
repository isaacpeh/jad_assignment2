<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="model.*, model.Category,java.util.*"%>
	<%
		List<Tour> displayTours = (ArrayList<Tour>) request.getAttribute("reqTours");
		List<User> displayUser = (ArrayList<User>) request.getAttribute("reqUsers");
	try{
		for(Tour i : displayTours){
			out.println("Tour ID: " + i.getTourid() + "<br>");
			out.println("Tour Name: " + i.getTourName() + "<br>");
			out.println("Slots: " + i.getSlotsAvailable() + "<br>");
			out.println("<br>---------------<br>");
		}
	} catch (Exception ex){
		
	}
	
	try{
		for(User i : displayUser){
			out.println("User ID: " + i.getUserid() + "<br>");
			out.println("Username: " +i.getUsername() + "<br>");
			out.println("Contact: " +i.getContact() + "<br>");
			out.println("Address: " +i.getAddress() + "<br>");
			if (i.getPurchases() != 0)
				out.println("Purchases: " +i.getPurchases() + "<br>");
		
			out.println("<br>---------------<br>");
		}
	} catch (Exception ex){
		
	}
	
		
	%>

 <form action="/assignment2_jad_client/admin_tours_filter" method="get">
 	<select id="category" name="adminfilter" placeholder="Choose one">
 	<option value="popularity">popularity</option>
 	<option value="zerosales">zerosales</option>
 	<option value="create">create</option>
 	</select>
 	<input type="submit">
 </form>
 <form action="/assignment2_jad_client/admin_tours_filter" method="get">
 	slot: <input type="text" name="adminfilterslot">
 	<input type="submit">
 </form>
 
 <form action="/assignment2_jad_client/admin_user_filter" method="get">
 <select id="category" name="userfilter" placeholder="Choose one">
 	<option value="address">address</option>
 	<option value="inactive">inactive</option>
 	</select>
 	<input type="submit">
 </form>
 <form action="/assignment2_jad_client/admin_user_filter" method="get">
 	name: <input type="text" name="userfiltername">
 	<input type="submit">
 </form>
 <form action="/assignment2_jad_client/admin_user_filter" method="get">
 	number: <input type="text" name="userfilternumber">
 	<input type="submit">
 </form>
 
 <form action="/assignment2_jad_client/admin_sales_filter" method="get">
 	<select id="category" name="salesfilter" placeholder="Choose one">
 	<option value="top">Top 10 users</option>
 	</select>
 	<input type="submit">
 </form>
 
</body>
</html>