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
 * Servlet implementation class UserAddController
 */
@WebServlet("/UserAddController")
public class UserAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAddController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		UserManager um = new UserManager();
		
		String loginid = request.getParameter("new_loginid");
		String password = request.getParameter("new_password");
		String rePassword = request.getParameter("retyped_password");
		String email = request.getParameter("new_email");
		String contact = request.getParameter("new_contact");

		try {
			int contactInt = Integer.parseInt(contact);
		} catch (Exception ex) {
			response.sendRedirect("signup.jsp?errCode=invalidContactType");
		}
		String role = "Customer";

		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		if (request.getParameter("new_role") != null) {
			role = "Admin";
		}

		if (loginid == null || loginid.trim().equals("") || password == null || password.trim().equals("")
				|| email == null || email.trim().equals("") || contact == null || contact.trim().equals("")) {
			response.sendRedirect("signup.jsp?errCode=failedRegistration");
			return;
		}

		if (!password.equals(rePassword)) {
			response.sendRedirect("signup.jsp?errCode=passwordError");
			return;
		}

		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		User new_user = new User(loginid, email, contact, password, role);
		int result = um.addUser(new_user);

		if (result == 1) {
			// Pass
			int userid = um.verifyUser(loginid, password);

			if (userid != -1 && userid != 0) {
				// Login
				HttpSession session = request.getSession();
				session.setAttribute("sessUserID", userid);
				response.sendRedirect("index.jsp");

			} else {
				response.sendRedirect("signup.jsp?errCode=failedRegistration");
				return;
			}

		} else if (result == -1) {
			// Duplicate Entry
			response.sendRedirect("signup.jsp?errCode=duplicateRegistration");
			return;

		} else {
			// Other Error
			response.sendRedirect("signup.jsp?errCode=failedRegistration");
			return;
		}
	}
}
