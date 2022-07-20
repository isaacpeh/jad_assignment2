/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TourManager;

/**
 * Servlet implementation class TourDeleteController
 */
@WebServlet("/TourDeleteController")
public class TourDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TourDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * -------------------------------------------- 
		 * 1. Pull data
		 * --------------------------------------------
		 */
		TourManager tm = new TourManager();
		String tour = request.getParameter("delete_tour");
		int tourid = 0;

		/*
		 * --------------------------------------------
		 * 2. Validate data
		 * --------------------------------------------
		 */
		try {
			tourid = Integer.parseInt(tour);

		} catch (Exception ex) {
			response.sendRedirect("admin_tours?errCode=failedDeleteTour");
			return;
		}

		/*
		 * -------------------------------------------- 
		 * 3. Process request
		 * --------------------------------------------
		 */
		int result = tm.deleteTour(tourid);
		if (result == 1) {
			response.sendRedirect("admin_tours");

		} else {
			response.sendRedirect("admin_tours?errCode=failedDeleteTour");
			return;

		}
	}
}
