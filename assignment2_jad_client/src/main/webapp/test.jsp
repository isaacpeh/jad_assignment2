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
		// tours management results
		List<Tour> displayTours = (ArrayList<Tour>) request.getAttribute("mgmtTours");
		
		// user management results
		List<User> displayUsers = (ArrayList<User>) request.getAttribute("mgmtUsers");
		
		// sales management results
		List<TourRecord> displaySalesTour = (ArrayList<TourRecord>) request.getAttribute("mgmtSalesTours");
		List<TourRecord> displaySalesUser = (ArrayList<TourRecord>) request.getAttribute("mgmtSalesUsers");
		
		
	try{
		for(Tour i : displayTours){
			out.println("Tour ID: " + i.getTourid() + "<br>");
			out.println("Tour Name: " + i.getTourName() + "<br>");
			out.println("Slots: " + i.getSlotsAvailable() + "<br>");
			
			if(i.getTotalSales() != 0){
				out.println("Sales: " + i.getTotalSales() + "<br>");
			}
			
			if(i.getCreated_at() != null){
				out.println("Created at: " + i.getCreated_at() + "<br>");
			}
			
			out.println("<br>---------------<br>");
		}
	} catch (Exception ex){
		
	}
	
	try{
		for(User i : displayUsers){
			out.println("User ID: " + i.getUserid() + "<br>");
			out.println("Username: " +i.getUsername() + "<br>");
			out.println("Contact: " +i.getContact() + "<br>");
			out.println("Address: " +i.getAddress() + "<br>");
			
			if (i.getLast_logged_in() != null)
				out.println("Last Login: " +i.getLast_logged_in() + "<br>");
		
			out.println("<br>---------------<br>");
		}
	} catch (Exception ex){
		
	}
	
	try{
		for(TourRecord i : displaySalesTour){
			out.println("Tour Name: " + i.getTourname() + "<br>");
			out.println("User: " + i.getUsername() + "<br>");
			out.println("Qty: " + i.getQuantity() + "<br>");
			out.println("Date of Purchase: " + i.getPurchased_at() + "<br>");
			out.println("<br>---------------<br>");
		}
	} catch (Exception ex){
		
	}
	
	try{
		for(TourRecord i : displaySalesUser){
			out.println("User ID: " + i.getUserid() + "<br>");
			out.println("Username: " +i.getUsername() + "<br>");
			out.println("Tour ID: " + i.getTourid() + "<br>");
			out.println("Tourname: " + i.getTourname() + "<br>");
			out.println("Quantity: " + i.getQuantity() + "<br>");
			out.println("Purcased at: " + i.getPurchased_at() + "<br>");
			out.println("<br>---------------<br>");
		}
	} catch (Exception ex){
		
	}
		
	%>

<h2>Tour</h2>
<h5>Tour Filters</h5>
 <form action="/assignment2_jad_client/admin_tours_filter" method="get">
 	<select id="category" name="adminfilter" placeholder="Choose one">
 	<option value="popularity">popularity</option>
 	<option value="zerosales">zerosales</option>
 	<option value="create">create</option>
 	</select>
 	<input type="submit">
 </form>
 <h5>Tour Filter by slot count</h5>
 <form action="/assignment2_jad_client/admin_tours_filter" method="get">
 	slot: <input type="text" name="adminFilterSlot">
 	<input type="submit">
 </form>
 
<h2>User</h2>
<h5>User Filters</h5>
 <form action="/assignment2_jad_client/admin_user_filter" method="get">
 <select id="category" name="userfilter" placeholder="Choose one">
 	<option value="address">address</option>
 	<option value="inactive">inactive</option>
 	</select>
 	<input type="submit">
 </form>
 <h5>User Filters by name</h5>
 <form action="/assignment2_jad_client/admin_user_filter" method="get">
 	name: <input type="text" name="userFilterName">
 	<input type="submit">
 </form>
 <h5>User Filters by number</h5>
 <form action="/assignment2_jad_client/admin_user_filter" method="get">
 	number: <input type="text" name="userFilterNumber">
 	<input type="submit">
 </form>
 
 <h2>Sales</h2>
 <h5>Sales Filters </h5>
 <form action="/assignment2_jad_client/admin_sales_filter" method="get">
 	<select id="category" name="salesfilter" placeholder="Choose one">
 	<option value="top">Top 10 users</option>
 	</select><br>
 	<input type="submit">
 </form>
 <h5>Sales Filters by date</h5>
<form action="/assignment2_jad_client/admin_sales_filter" method="get">
 	<label>Date From:</label>
	  <input type="date" name="dateFrom"> <br>
	  <label>Date To:</label>
	  <input type="date" name="dateTo">
	  <input type="submit">
 </form>
<h5>Sales Filters by users who booked certain tour</h5>
<form action="/assignment2_jad_client/admin_sales_filter" method="get">
	  TourID: <input type="text" name="salesTourid">
	  <input type="submit">
 </form>
 
</body>
</html>