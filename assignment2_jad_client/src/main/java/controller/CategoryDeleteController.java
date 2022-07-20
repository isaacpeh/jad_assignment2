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

import model.CategoryManager;

/**
 * Servlet implementation class CategoryDeleteController
 */
@WebServlet("/CategoryDeleteController")
public class CategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryDeleteController() {
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
		CategoryManager cm = new CategoryManager();
		String cat = request.getParameter("delete_category");
		int catid = 0;

		/*
		 * --------------------------------------------
		 * 2. Validate data
		 * --------------------------------------------
		 */
		try {
			catid = Integer.parseInt(cat);

		} catch (Exception ex) {
			response.sendRedirect("admin_tours?errCode=failedDeleteCategory");
			return;
		}

		/*
		 * -------------------------------------------- 
		 * 3. Process request
		 * --------------------------------------------
		 */
		int result = cm.deleteCategory(catid);
		if (result == 1) {
			response.sendRedirect("admin_tours");

		} else {
			response.sendRedirect("admin_tours?errCode=failedDeleteCategory");
			return;

		}
	}
}
