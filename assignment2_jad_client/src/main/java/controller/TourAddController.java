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
 * Servlet implementation class TourAddController
 */
@WebServlet("/TourAddController")
public class TourAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TourAddController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO: Pull and add image into the other database

		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		TourManager tm = new TourManager();

		String tourName = request.getParameter("new_tourName");
		String bDescription = request.getParameter("new_tourBDescription");
		String dDescription = request.getParameter("new_tourDDescription");
		String stourPrice = request.getParameter("new_tourPrice");
		String stourSlots = request.getParameter("new_tourSlots");
		String[] imgs = request.getParameterValues("new_tourImg");
		String[] category = request.getParameterValues("new_tourCategory");

		int[] intCategory;
		double tourPrice;
		int tourSlots;

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
		if ( category == null || category.length == 0 || tourName == null || tourName.trim().equals("") 
				|| bDescription == null || bDescription.trim().equals("")
				|| dDescription == null || dDescription.trim().equals("") || stourPrice == null
				|| stourPrice.trim().equals("") || stourSlots == null || stourSlots.trim().equals("") 
				|| error) {
			response.sendRedirect("admin_tours?errCode=failedNewTour");
			return;
		}

		try {
			// String category array to int category array
			intCategory = new int[category.length];
			for (int i = 0; i < category.length; i++) {
				intCategory[i] = Integer.parseInt(category[i]);
			}

			// Check for valid inputs
			tourSlots = Integer.parseInt(stourSlots);
			tourPrice = Double.parseDouble(stourPrice);

		} catch (Exception ex) {
			response.sendRedirect("admin_tours?errCode=failedNewTour");
			return;
		}

		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		Tour new_tour = new Tour(tourName, bDescription, dDescription, tourPrice, tourSlots);
		int result = tm.addTour(new_tour);
		boolean dupError = false;
		boolean otherError = false;

		if (result != 0 && result != -1) {
			// Passed
			int result2[] = tm.addTourImg(result, imgs);

			if (result2[0] == -1) {
				dupError = true;
			} else if (result2[0] == -2) {
				otherError = true;
			} else {
				// Add image passed
				int result3[] = tm.addTourCat(result, intCategory);

				if (result3[0] == -1) {
					dupError = true;
				} else if (result3[0] == -2) {
					otherError = true;
				} else {
					// Add category passed
					response.sendRedirect("admin_tours");
				}
			}

		} else if (result == -1 || dupError) {
			// Duplicate Entry
			response.sendRedirect("admin_tours?errCode=duplicateTour");
		} else if (result == 0 || otherError) {
			// Other Error
			response.sendRedirect("admin_tours?errCode=failedNewTour");
		}
		
	}

}
