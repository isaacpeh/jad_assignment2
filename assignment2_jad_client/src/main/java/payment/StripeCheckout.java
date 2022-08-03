package payment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import stripepayment.StripePayment;

/**
 * Servlet implementation class StripeCheckout
 */
@WebServlet("/StripeCheckout")
public class StripeCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StripeCheckout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		long amount = (long) request.getAttribute("");
		String currency = (String) request.getAttribute("");
		amount = 12345;
		currency = "sgd";
		Stripe.apiKey = "sk_test_51Kq9MBAJnEcRMEUtMM0mN2GhYLowzYYwq5FIM23xHuGcuNejonbuurPasDfnOfLSfh9SvyeQpOg4TGMGiCuAB1d200BLXLdD4V";
		
		try {
			PaymentIntent intent = StripePayment.createNewPayment(amount, currency);
			RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
			request.setAttribute("client_secret", intent.getClientSecret());
			dispatcher.forward(request, response);
		} catch (StripeException e) {
			response.sendRedirect("login.jsp");
			return;
		}
	}

}
