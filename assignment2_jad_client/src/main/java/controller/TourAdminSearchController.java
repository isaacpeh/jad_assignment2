/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tour;
import model.TourManager;

/**
 * Servlet implementation class TourShowBothController
 */
@WebServlet(urlPatterns = { "/admin_tours_filter" })
public class TourAdminSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TourAdminSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TourManager tm = new TourManager();
		String adminFilter = null;
		int adminFilter_slot = -1;
		int adminFilter_priceF = -1;
		int adminFilter_priceT = -1;
		List<Tour> result = new ArrayList<Tour>();
		
		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		
		adminFilter = request.getParameter("adminfilter");

		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		
		try {
			adminFilter_slot = Integer.parseInt(request.getParameter("adminFilterSlot").trim());
		} catch (NumberFormatException ex) {
			System.out.println("slotNumberError");
			response.sendRedirect("test?errCode=slotNumberError");
			return;
		} catch (Exception ex) {

		}

		try {
			adminFilter_priceF = Integer.parseInt(request.getParameter("adminFilterPriceF").trim());
			adminFilter_priceT = Integer.parseInt(request.getParameter("adminFilterPriceT").trim());

		} catch (NumberFormatException ex) {
			System.out.println("priceNumberError");
			response.sendRedirect("test?errCode=priceNumberError");
			return;

		} catch (Exception ex) {

		}

		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */

		if (adminFilter_slot != -1) {
			// Slot
			result = tm.showToursSlot(adminFilter_slot);
		} else if (adminFilter_priceF != -1 && adminFilter_priceT != -1) {
			// Price range
			result = tm.showToursPrice(adminFilter_priceF, adminFilter_priceT);
		
		} else if (adminFilter != null && adminFilter.equalsIgnoreCase("popularity")) {
			// Popularity
			result = tm.showToursPopularity();

		} else if (adminFilter != null && adminFilter.equalsIgnoreCase("zerosales")) {
			// Zero Sales
			result = tm.showToursNoSales();

		} else if (adminFilter != null && adminFilter.equalsIgnoreCase("create")) {
			// Creation date
			result = tm.showToursNew();

		} else {
			response.sendRedirect("test?errCode=noFilter");
			return;
		}

		request.setAttribute("mgmtTours", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TourManager tm = new TourManager();
		int tourid_active = -1;
		int tourid_option = -1;
		
		try {
			tourid_active = Integer.parseInt(request.getParameter("tourid").trim());
			tourid_option = Integer.parseInt(request.getParameter("tour_isActive").trim());
		} catch (NumberFormatException ex) {
			System.out.println("tourActiveError");
			response.sendRedirect("test?errCode=tourActiveError");
			return;
		} catch (Exception ex) {

		}
		
		if (tourid_active != -1) {
			// Update active
			int affectedRows = tm.updateActive(tourid_active, tourid_option);

			if (affectedRows == 1) {
				response.sendRedirect("test?message=successfullyUpdated");
				return;
			} else {
				response.sendRedirect("test?errCode=unsuccessfulUpdate");
				return;
			}
		}
		
	}

}
