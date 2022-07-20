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

import model.Tour;
import model.TourManager;

/**
 * Servlet implementation class TourUpdateController
 */
@WebServlet("/TourUpdateController")
public class TourUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TourUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		TourManager tm = new TourManager();
		
		String stourId = request.getParameter("update_tourId");
		String tourName = request.getParameter("update_tourName");
		String bDescription = request.getParameter("update_tourBDescription");
		String dDescription = request.getParameter("update_tourDDescription");
		String stourPrice = request.getParameter("update_tourPrice");
		String stourSlots = request.getParameter("update_tourSlots");
		String[] imgs = request.getParameterValues("update_tourImg");
		String[] category = request.getParameterValues("update_tourCategory");
		int[] intCategory;
		double tourPrice;
		int tourSlots, tourId;

		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		boolean error = false;

		// Check if image array contains empty string
		for (String i : imgs) {
			if (i.trim().length() == 0 || i == null) {
				error = true;
			}
		}

		// Check if image array contains duplicates
		for (int j = 0; j < imgs.length; j++) {
			for (int k = j + 1; k < imgs.length; k++) {
				if (k != j && imgs[k].equalsIgnoreCase(imgs[j])) {
					error = true;
				}
			}
		}

		// Check for empty fields


		try {
			// String category array to int category array
			intCategory = new int[category.length];
			for (int i = 0; i < category.length; i++) {
				intCategory[i] = Integer.parseInt(category[i]);
			}

			// Check for valid inputs
			tourId = Integer.parseInt(stourId);
			tourSlots = Integer.parseInt(stourSlots);
			tourPrice = Double.parseDouble(stourPrice);

		} catch (Exception ex) {
			System.out.println("fail2");
			System.out.println(ex.getMessage());
			response.sendRedirect("admin_tours?errCode=failedUpdateTour");
			return;
		}
		
		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		Tour update_tour = new Tour(tourName, bDescription, dDescription, tourPrice, tourSlots);
		int updated = tm.updateTour(tourId, update_tour); // update tour

		if (updated == 1) {
			tm.deleteTourImg(tourId); // remove tour images
			tm.deleteTourCat(tourId); // remove tour categories
			tm.addTourImg(tourId, imgs); // add updated tour images
			tm.addTourCat(tourId, intCategory); // add updated tour categories
			response.sendRedirect("admin_tours");
			return;
		} else if (updated == -1) {
			response.sendRedirect("admin_tours?errCode=duplicateUpdateTour");
			return;
		} else {
			System.out.println("fail2");
			System.out.println(updated);
			response.sendRedirect("admin_tours?errCode=failedUpdateTour");
			return;
		}
	}
}
