<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/assignment2_jad_client/StripeCheckout" method="POST">
	<input type="text" name="amount">
	<input type="text" name="currency">
	<input type="submit">
</form>
</body>
</html>