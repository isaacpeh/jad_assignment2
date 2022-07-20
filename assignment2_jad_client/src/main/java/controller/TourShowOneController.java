/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tour;
import model.TourManager;
/**
 * Servlet implementation class TourShowOneController
 */
@WebServlet("/TourShowOneController")
public class TourShowOneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TourShowOneController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		TourManager tm = new TourManager();
		int tourid = 0;
		try {
			tourid = Integer.parseInt(request.getParameter("tourid"));
			System.out.println(tourid);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Tour ID: " + tourid);
		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		if (tourid == 0) {
			response.sendRedirect("tours_details.jsp?errCode=tourNotFound");
			return;
		}
		
		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		Tour result = tm.showTour(tourid);

		if (result == null) {
			response.sendRedirect("tours_details.jsp?errCode=tourNotFound");
			return;

		} else {
			request.setAttribute("reqTour", result);
			RequestDispatcher dispatcher = request.getRequestDispatcher("tour_details.jsp");
			dispatcher.forward(request, response);
		}
	}
}
