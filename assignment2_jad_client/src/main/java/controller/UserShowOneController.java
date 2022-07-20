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

import model.User;
import model.UserManager;

/**
 * Servlet implementation class UserShowOneController
 */
@WebServlet(urlPatterns = { "/profile" })
public class UserShowOneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserShowOneController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		UserManager um = new UserManager();
		int loggedInUser = 0;
		try {
			loggedInUser = (int) request.getSession().getAttribute("sessUserID");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Logged in user: " + loggedInUser);
		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		if (loggedInUser == 0) {
			response.sendRedirect("login.jsp?errCode=userNotFound");
			return;
		}
		
		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		User result = um.showUser(loggedInUser);

		if (result == null) {
			response.sendRedirect("login.jsp?errCode=userNotFound");
			return;

		} else {
			request.setAttribute("reqProfile", result);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
			dispatcher.forward(request, response);
		}
	}
}
