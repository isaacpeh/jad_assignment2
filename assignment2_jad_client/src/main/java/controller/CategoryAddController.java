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

import model.Category;
import model.CategoryManager;

/**
 * Servlet implementation class CategoryAddController
 */
@WebServlet("/CategoryAddController")
public class CategoryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryAddController() {
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
		CategoryManager cm = new CategoryManager();

		String catName = request.getParameter("new_category");
		String catDescription = request.getParameter("new_description");

		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		if (catName == null || catName.trim().equals("") || catDescription == null || catDescription.trim().equals("")) {
			response.sendRedirect("admin_tours?errCode=failedNewCategory");
			return;
		}

		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		Category new_cat = new Category(catName, catDescription);
		int result = cm.addCategory(new_cat);
		if (result == 1) {
			// Pass
			response.sendRedirect("admin_tours");
			return;
		} else if (result == -1) {
			// Duplicate Entry
			response.sendRedirect("admin_tours?errCode=duplicateCategory");
			return;
		} else {
			// Other Error
			response.sendRedirect("admin_tours?errCode=failedNewCategory");
			return;
		}
	}

}
