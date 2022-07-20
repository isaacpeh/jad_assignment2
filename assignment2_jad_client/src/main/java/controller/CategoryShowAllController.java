/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.CategoryManager;

/**
 * Servlet implementation class CategoryShowAllController
 */

@WebServlet(urlPatterns = { "/index", "/categories", "/admin_category" })
public class CategoryShowAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryShowAllController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String source = "index.jsp";

		if (request.getRequestURI().contains("categories")) {
			source = "categories.jsp";
		} else if (request.getRequestURI().contains("admin_category")) {
			source = "admin_tours.jsp";
		}

		CategoryManager cm = new CategoryManager();
		List<Category> result = cm.showCategories();

		request.setAttribute("reqCategories", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher(source);
		dispatcher.forward(request, response);

	}
}
