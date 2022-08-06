package StripePayment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentCreateParams;

public class StripePayment {
	public static PaymentIntent createNewPayment(Long amount, String currency) throws StripeException {
		PaymentIntentCreateParams params = PaymentIntentCreateParams.builder().setAmount(amount).setCurrency(currency)
				.setAutomaticPaymentMethods(
						PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build())
				.build();

		PaymentIntent paymentIntent = PaymentIntent.create(params);

		return paymentIntent;
	}

	public static PaymentIntent retrievePayment(String payment_intent_client_secret) throws StripeException {
		Stripe.apiKey = "sk_test_51Kq9MBAJnEcRMEUtMM0mN2GhYLowzYYwq5FIM23xHuGcuNejonbuurPasDfnOfLSfh9SvyeQpOg4TGMGiCuAB1d200BLXLdD4V";
		PaymentIntent intent = PaymentIntent.retrieve(payment_intent_client_secret);
		return intent;
	}

	public static PaymentMethod retrievePaymentMethod(String payment_method) throws StripeException {
		Stripe.apiKey = "sk_test_51Kq9MBAJnEcRMEUtMM0mN2GhYLowzYYwq5FIM23xHuGcuNejonbuurPasDfnOfLSfh9SvyeQpOg4TGMGiCuAB1d200BLXLdD4V";
		PaymentMethod pm = PaymentMethod.retrieve(payment_method);
		return pm;
	}
}