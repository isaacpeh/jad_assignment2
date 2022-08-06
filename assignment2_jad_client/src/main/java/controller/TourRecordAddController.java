package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.TourRecord;
import model.TourRecordManager;

/**
 * Servlet implementation class TourRecordAddController
 */
@WebServlet(urlPatterns = { "/TourRecordAddController", "/admin_book" })
public class TourRecordAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TourRecordAddController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */

		List<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("sessCart");
		List<TourRecord> list = new ArrayList<TourRecord>();
		TourRecordManager trm = new TourRecordManager();

		int tourID = 0;
		int userID = 0;
		int qty = 0;
		
		String source = "checkout.jsp";

		if (request.getRequestURI().contains("admin_book")) {
			source = "admin_user.jsp";
			int userid = -1;
			int tourid = -1;
			int quantity = -1;
			try {
				userid = Integer.parseInt(request.getParameter("adminbook_userid"));
				tourid = Integer.parseInt(request.getParameter("adminbook_tourid"));
				quantity = Integer.parseInt(request.getParameter("adminbook_quantity"));
				
				if (quantity <= 0) {
					response.sendRedirect(source + "?errCode=quantityError");
					return;
				}

			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				response.sendRedirect(source + "?errCode=invalidNumberFormat");
				return;
			} catch (Exception ex) {

			}

			TourRecord newTour = new TourRecord(userid, tourid, quantity);
			list.add(newTour);
			int[] result = trm.addRecord(list);

			if (result[0] != -1) {
				response.sendRedirect(source + "?message=succesfullyBooked");
				return;

			} else {
				response.sendRedirect(source + "?errCode=failedBooking");
				return;
			}
		}

		if (cart == null || cart.size() == 0) {
			response.sendRedirect(source + "?errCode=cartEmpty");
			return;
		}

		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		for (int i = 0; i < cart.size(); i++) {
			try {
				tourID = cart.get(i).getTourId();
				qty = cart.get(i).getQuantity();
				userID = (int) request.getSession().getAttribute("sessUserID");

				TourRecord newRecord = new TourRecord(userID, tourID, qty);
				list.add(newRecord);

			} catch (NumberFormatException ex) {
				response.sendRedirect(source + "?errCode=invalidParameters");
				return;

			} catch (Exception ex) {
				response.sendRedirect(source + "?errCode=unknownError");
				return;
			}
		}

		if (list == null || list.size() == 0) {
			response.sendRedirect(source + "?errCode=unfilledParameters");
			return;
		}

		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		int[] result = trm.addRecord(list);
		
		if (result[0] != -1) {
			response.sendRedirect("customer_history.jsp");
			return;

		} else {
			response.sendRedirect(source + "?errCode=failedTransaction");
			return;
		}
	}

}
