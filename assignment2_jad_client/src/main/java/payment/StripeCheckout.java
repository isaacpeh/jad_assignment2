package payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import StripePayment.StripePayment;
import model.Cart;

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

		List<Cart> sessCart = (ArrayList<Cart>) request.getSession().getAttribute("sessCart");
		double amount = 0;
		try {
			for (Cart i : sessCart) {
				amount += i.getTotalPrice();
			}
		} catch (Exception ex) {

		}
		String tempAmt = Double.toString(amount);
		long amountLong = (long) Double.parseDouble(tempAmt);
		String currency = "sgd";
		Stripe.apiKey = "sk_test_51Kq9MBAJnEcRMEUtMM0mN2GhYLowzYYwq5FIM23xHuGcuNejonbuurPasDfnOfLSfh9SvyeQpOg4TGMGiCuAB1d200BLXLdD4V";

		try {
			PaymentIntent intent = StripePayment.createNewPayment(amountLong, currency);
			RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
			request.setAttribute("client_secret", intent.getClientSecret());
			dispatcher.forward(request, response);
		} catch (StripeException e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp");
			return;
		}
	}

}