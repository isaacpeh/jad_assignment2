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
import javax.servlet.http.HttpSession;

import model.User;
import model.UserManager;

/**
 * Servlet implementation class VerifyUserController
 */
@WebServlet("/VerifyUserController")
public class VerifyUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyUserController() {
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
		UserManager um = new UserManager();

		String loginid = request.getParameter("loginid");
		String password = request.getParameter("password");

		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		if (loginid == null || loginid.trim().equals("") || password == null || password.trim().equals("")) {
			response.sendRedirect("login.jsp?errCode=failedLogin");
			return;
		}
		
		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		int userid = um.verifyUser(loginid, password);

		if (userid != -1 && userid != 0) {
			um.updateLoggedIn(userid);
			User result = um.showUser(userid);
			if (result.getRole().equals("Admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("sessUserID", userid);
				response.sendRedirect("admin_user");
				return;
			}

			HttpSession session = request.getSession();
			session.setAttribute("sessUserID", userid);
			response.sendRedirect("index.jsp");
		} else {
			// fail
			response.sendRedirect("login.jsp?errCode=failedLogin");
		}
	}
}
