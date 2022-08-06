<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<link href="https://unpkg.com/cirrus-ui" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous" />
<link rel="stylesheet" href="./css/cart.css" />
</head>
<body class='p-2'>
	<%@ page import="model.Tour, java.util.*, model.TourManager,model.Cart"%>
	<%@ include file="header.jsp"%>
	<%
	List<Cart> sessCart =(ArrayList<Cart>)request.getSession().getAttribute("sessCart");
	System.out.print(sessCart);
	
	//session is not null(show items)----------------------------------------------------------------------------------------
	if(sessCart != null){
		%>
		
		<div class="space space--xl ..."></div>
			<h1 style='text-align: center;'></h1>
			<div class='shoppingCart card row p-2 h-90p'>
				<div class='cartItems col-9 u-relative'>
				
				<%--delete form --%>
				<form action="CartDeleteController">
				<div style="justify-content: space-between;width:100%;display:flex">
					<h2>Shopping Cart</h2>
					<%--Delete Button --%>
					<div>
						<a><button class="btn-danger">Delete</button></a>
					</div>
				</div>	
			<div class='u-overflow-y-scroll' style="height:500px">
			
				<%
				double subtotal=0, gst=0, checkoutPrice =0;
				
				for(int i=0;i<sessCart.size();i++){
					String tourname = "", tourPic = "", tourPrice = "",tourQuantity = "",tourId="",totalPrice="";
					try{
						tourname = sessCart.get(i).getTourName();
						tourPic = sessCart.get(i).getPicUrl();
						tourPrice = Double.toString(sessCart.get(i).getPrice());
						tourQuantity = Integer.toString(sessCart.get(i).getQuantity());
						tourId = Integer.toString(sessCart.get(i).getTourId());
						totalPrice = Double.toString(sessCart.get(i).getTotalPrice());
						subtotal += Double.parseDouble(totalPrice);
						gst += (subtotal*0.07);
						checkoutPrice = subtotal+gst;
					}
					catch(Exception e){
						response.sendRedirect("tours.jsp?errCode=cartErr");
					}
					%>
				
				<div class='cartItem py-2 pr-4'>
					<input type="checkbox" id="delete-<%=tourId%>" name="deleteItems" value="<%=tourId%>" style="width:25px;height:25px"> 
					
					<div class='cartImage'>
						<img src='https://www.marinabaysands.com/content/dam/singapore/marinabaysands/master/main/home/sg-visitors-guide/southern-islands/Sentosa%201000x557px.jpg' />
					</div>
					<div class='ml-2' style="width:100%">
						<div class='namePrice'>
							<h5><%=tourname %></h5>
							<h3 style="right:0px" id="tourPrice-<%=tourId %>">$<%=tourPrice %></h3>
						</div>
						<div class="inputContainer"style="width:100%;display:flex;justify-content:space-between;border-collapse: collapse;">
							<%--Plus-Minus Cart Button --%>
								<div class="form-group number" style="width:120px;margin-top:45px">
								<a href="CartQuantityController?btn=minus&tourid=<%=tourId%>"><button class="form-group-btn btn-success btn--xs minus" id="minusBtn-<%=tourId %>" type="button" style="height:55px;font-size:20px" onclick="totalValue('minus',<%=tourId%>,<%=tourPrice %>)">-</button></a>
								<input class="form-group-input input--xs" id="quantityInput-<%=tourId %>" value="<%=tourQuantity %>" disabled style="text-align: center;border-radius:10px" />
								<a href="CartQuantityController?btn=plus&tourid=<%=tourId%>"><button class="form-group-btn btn-success btn--xs plus" id="plusBtn" type="button" style="height:55px;font-size:20px" onclick="totalValue('plus',<%=tourId%>,<%=tourPrice %>)">+</button></a>
							</div>
						</div>
						
					</div>
				</div>
				<%} %>		
						
						
				
			</div>
			</form>
		</div>
		<form class='paymentDetails col-3' submit='#'>
			<div class='cartTotal p-2 card bg-teal-100'>
				<div class='subtotal'>
					<h6>Subtotal</h6>
					<h6 id="subtotal">S$<%=subtotal %></h6>
				</div>
				<hr>
				<div class='subtotal mt-2'>
					<h6>GST</h6>
					<h6 id="gst">S$<%=String.format("%.1f", gst)%></h6>
				</div>
				<hr>
				<div class='subtotal mt-2'>
					<h4>
						TOTAL
						</h6>
						<h4 id="checkoutPrice">S$<%=checkoutPrice %></h4>
				</div>
			</div>
			<div class='acceptedMethods card bg-teal-100 p-2'>
				<h6>Accepted payment methods:</h6>
				<div class='creditCards'>
					<i class="fab fa-cc-stripe fa-3x"></i> <i
						class="fab fa-cc-visa fa-3x"></i> <i
						class="fab fa-cc-mastercard fa-3x"></i> <i
						class="fab fa-cc-paypal fa-3x"></i>
				</div>
			</div>
			<div class='card bg-teal-100 p-2'>
				<button class='w-100p my-1 bg-yellow-300'>PROCEED</button>
			</div>
		</form>
	</div>
	
	
		<% 
		//session is null----------------------------------------------------------------------------------------------------
	}else{
		%>
		<div class="space space--xl ..."></div>
	<h1 style='text-align: center;'></h1>
	<div class='shoppingCart card row p-2 h-90p'>
		<div class='cartItems col-9 u-relative'>
		
		<%--delete form --%>
		<form action="/deleteCart">
		<div style="justify-content: space-between;width:100%;display:flex">
			
		</div>	
			<div class='u-overflow-y-scroll u-center' style="height:500px">
				
				<div class='cartItem py-2 pr-4'>
					<h3>Your cart is empty</h3>
				</div>
				
			</div>
			</form>
		</div>
		<form class='paymentDetails col-3' submit='#'>
			<div class='cartTotal p-2 card bg-teal-100'>
				<div class='subtotal'>
					<h6>Subtotal</h6>
					<h6 id="subtotal">S$0</h6>
				</div>
				<hr>
				<div class='subtotal mt-2'>
					<h6>GST</h6>
					<h6 id="gst">S$0</h6>
				</div>
				<hr>
				<div class='subtotal mt-2'>
					<h4>
						TOTAL
						</h6>
						<h4 id="checkoutPrice">S$0</h4>
				</div>
			</div>
			<div class='acceptedMethods card bg-teal-100 p-2'>
				<h6>Accepted payment methods:</h6>
				<div class='creditCards'>
					<i class="fab fa-cc-stripe fa-3x"></i> <i
						class="fab fa-cc-visa fa-3x"></i> <i
						class="fab fa-cc-mastercard fa-3x"></i> <i
						class="fab fa-cc-paypal fa-3x"></i>
				</div>
			</div>
			<div class='card bg-teal-100 p-2'>
				<button class='w-100p my-1 bg-yellow-300'>PROCEED</button>
			</div>
		</form>
	</div>
	
	<%} %>
	
	<%--script for tour quantity --%>
	<script type="text/javascript">
							var subtotal = parseFloat(document.getElementById("subtotal").innerHTML.replace("S$",""));
							var gst = parseFloat(document.getElementById("gst").innerHTML.replace("S$",""));
							var checkoutPrice = parseFloat(document.getElementById("checkoutPrice").innerHTML.replace("S$",""));
							
							
							function incrementValue(tourid)
							{	
								const minusBtn = document.getElementById("minusBtn-"+tourid);
								minusBtn.disabled = false;
							    var value = parseInt(document.getElementById('quantityInput-'+tourid).value, 10);
							    value = isNaN(value) ? 0 : value; //not a number check for value
							    if(value<99){
							        value++;
							        document.getElementById('quantityInput-'+tourid).value = value;
							    }
							   
							}
							function decrementValue(tourid)
							{
								const minusBtn = document.getElementById("minusBtn-"+tourid);
							    var value = parseInt(document.getElementById('quantityInput-'+tourid).value, 10);
							    value = isNaN(value) ? 0 : value;
							    if(value>1){
							        value--;
							        document.getElementById('quantityInput-'+tourid).value = value;
							        if(value<=1){
							        	minusBtn.disabled=true;
							        }
							    }
							    

							}
							function totalValue(btnInput,tourid,tourPrice){
								if(btnInput == "plus"){
									incrementValue(tourid);
									subtotal += parseFloat(tourPrice);
									gst = subtotal*0.07;
									checkoutPrice = subtotal + gst;
									
									document.getElementById("subtotal").innerHTML = "S$"+subtotal.toFixed(1);
									document.getElementById("gst").innerHTML = "S$"+gst.toFixed(1);
									document.getElementById("checkoutPrice").innerHTML = "S$"+checkoutPrice;
									
								}
								else{
									decrementValue(tourid);
									subtotal -=parseFloat(tourPrice);
									gst = subtotal*0.07;
									checkoutPrice = subtotal + gst;
									
									document.getElementById("subtotal").innerHTML = "S$"+subtotal.toFixed(1);
									document.getElementById("gst").innerHTML ="S$"+gst.toFixed(1);
									document.getElementById("checkoutPrice").innerHTML = "S$"+checkoutPrice;
								}
							}
							
							function validateCheckedItem(){
								
							}
							</script>
</body>
</html>






