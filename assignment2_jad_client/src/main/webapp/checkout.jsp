<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<script src="https://js.stripe.com/v3/"></script>
</head>
<body>
	<%
	String client_secret = (String) request.getAttribute("client_secret");
	%>
    <form id="payment-form">
      <div id="payment-element">
        <!--Stripe.js injects the Payment Element-->
      </div>
      <button id="submit">
        <div class="spinner hidden" id="spinner"></div>
        <span id="button-text">Pay now</span>
      </button>
      <div id="payment-message" class="hidden"></div>
    </form>
	<script>
	const getUrl = window.location;
	const redirectUrl = getUrl .protocol + "//" + getUrl.host + '${pageContext.request.contextPath}/pay'
	const stripe = Stripe('pk_test_51Kq9MBAJnEcRMEUtef9FXsOU425WAMCfQlMYTM61PBRLOyXTpVyQelHN00lyScDJk1xzxtlDqckU1Jy3CNMKiNEN00GUR5RX2x');
	
	const options = {
			  clientSecret: '<%=client_secret%>',
			  // Fully customizable with appearance API.
			  appearance: {/*...*/},
			};

			// Set up Stripe.js and Elements to use in checkout form, passing the client secret obtained in step 2
			const elements = stripe.elements(options);

			// Create and mount the Payment Element
			const paymentElement = elements.create('payment');
			paymentElement.mount('#payment-element');
			
			const form = document.getElementById('payment-form');

			form.addEventListener('submit', async (event) => {
			  event.preventDefault();

			  const {error} = await stripe.confirmPayment({
			    //`Elements` instance that was used to create the Payment Element
			    elements,
			    confirmParams: {
			      return_url: redirectUrl,
			    },
			  });

			  if (error) {
			    // This point will only be reached if there is an immediate error when
			    // confirming the payment. Show error to your customer (for example, payment
			    // details incomplete)
			    const messageContainer = document.querySelector('#error-message');
			    messageContainer.textContent = error.message + "return url: redirectUrl";
			  } else {
			    // Your customer will be redirected to your `return_url`. For some payment
			    // methods like iDEAL, your customer will be redirected to an intermediate
			    // site first to authorize the payment, then redirected to the `return_url`.
			  }
			});
	</script>
</body>
</html>