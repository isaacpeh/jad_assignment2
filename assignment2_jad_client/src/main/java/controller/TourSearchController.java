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
@WebServlet(urlPatterns = { "/tours", "/admin_tours" })
public class TourSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TourSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String source = "tours.jsp";
		if (request.getRequestURI().contains("admin_tours")) {
			source = "admin_category";
		}

		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		TourManager tm = new TourManager();
		int catid = 0;
		String filter = null;
		List<Tour> result;

		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */


		try {
			result = new ArrayList<>();
			filter = request.getParameter("key").trim();
			catid = Integer.parseInt(request.getParameter("catid"));

		} catch (Exception ex) {
			// input error here
		}

		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */

		if (catid != 0 && (filter != null && filter != "")) {
			// Category and Filter
			result = tm.showToursBoth(catid, filter);

		} else if (catid == 0 && (filter != null && filter != "")) {
			// Filter only
			result = tm.showToursFilter(filter);

		} else if (catid != 0 && (filter == null || filter == "")) {
			// Category only
			result = tm.showToursCategory(catid);

		} else {
			// Show all
			result = tm.showTours();
		}

		request.setAttribute("reqTours", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher(source);
		dispatcher.forward(request, response);
	}
}
