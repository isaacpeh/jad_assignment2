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

		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		TourManager tm = new TourManager();
		String adminFilter = null;
		int adminFilter_slot = -1;
		List<Tour> result = new ArrayList<Tour>();
		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */


		try {
			adminFilter = request.getParameter("adminfilter");
		} catch (Exception ex) {
			// input error here
		}

		try {
			adminFilter_slot = Integer.parseInt(request.getParameter("adminfilterslot").trim());
		} catch (Exception ex) {
			// input error here
		}

		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */

		// && source.equalsIgnoreCase("admin_category")
		if (adminFilter_slot != -1) {
			// Slot
			result = tm.showToursSlot(adminFilter_slot);
			
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
			// Show all
			result = tm.showTours();
		}

		request.setAttribute("reqTours", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
		dispatcher.forward(request, response);
	}
}
