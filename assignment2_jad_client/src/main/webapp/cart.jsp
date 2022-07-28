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
	<%%>
	<div class="space space--xl ..."></div>
	<h1 style='text-align: center;'></h1>
	<div class='shoppingCart card row p-2 h-90p'>
		<div class='cartItems col-9'>
			<h2>Shopping Cart</h2>
			<div class='u-overflow-y-scroll'>
				<%
				List<Cart> sessCart = new ArrayList<>();
				Cart cartItem1 = new Cart();
				cartItem1.setTourName("Singapore River Cruise");
				cartItem1.setPrice(22.5);
				cartItem1.setPicUrl("https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_863/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/vj3hgjkxyxcaegbbs86b/RiverCruisebyWaterB.jpg");
				cartItem1.setQuantity(4);
				sessCart.add(cartItem1);
				
				Cart cartItem2 = new Cart();
				cartItem2.setTourName("Singapore Southern Islands Hopping (3 Or 4 Islands");
				cartItem2.setPrice(70.0);
				cartItem2.setPicUrl("https://www.marinabaysands.com/content/dam/singapore/marinabaysands/master/main/home/sg-visitors-guide/southern-islands/Sentosa%201000x557px.jpg");
				cartItem2.setQuantity(2);
				sessCart.add(cartItem2);
				
				
				for(int i=0;i<sessCart.size();i++){
					String tourname = "", tourPic = "", tourPrice = "",tourQuantity = "";
					try{
						tourname = sessCart.get(i).getTourName();
						tourPic = sessCart.get(i).getPicUrl();
						tourPrice = Double.toString(sessCart.get(i).getPrice());
						tourQuantity = Integer.toString(sessCart.get(i).getQuantity());
					}
					catch(Exception e){
						response.sendRedirect("tours.jsp?errCode=cartErr");
					}
					%>
				
				<div class='cartItem py-2 pr-4' >
					<div class='cartImage'>
						<img src='<%=tourPic %>' />
					</div>
					<div class='ml-2' style="width:100%">
						<div class='namePrice'>
							<h5><%=tourname %></h5>
							<h3 style="right:0px">$<%=tourPrice %></h3>
						</div>
						<div class="inputContainer"style="width:100%;display:flex;justify-content:flex-end;">
							<div class="form-group number mt-4" style="width:100px;">
							<button class="form-group-btn btn-success btn--xs minus" style="height:55px;font-size:20px">-</button>
							<input class="form-group-input input--xs" id="quantityInput" type="text" value="<%=tourQuantity %>" disabled style="text-align: center"/>
							<button class="form-group-btn btn-success btn--xs plus" style="height:55px;font-size:20px">+</button>
							<script>
							</script>
							</div>
						</div>
					</div>
				</div>
						
						
						
				<%} %>
			</div>
		</div>
		<form class='paymentDetails col-3' submit='#'>
			<div class='cartTotal p-2 card bg-teal-100'>
				<div class='subtotal'>
					<h6>Subtotal</h6>
					<h6>S$62.95</h6>
				</div>
				<hr>
				<div class='subtotal mt-2'>
					<h6>GST</h6>
					<h6>S$9.90</h6>
				</div>
				<hr>
				<div class='subtotal mt-2'>
					<h4>
						TOTAL
						</h6>
						<h4>S$62.95</h4>
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
</body>
</html>






