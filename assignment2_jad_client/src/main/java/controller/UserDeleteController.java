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

import model.UserManager;

/**
 * Servlet implementation class UserDeleteController
 */
@WebServlet("/UserDeleteController")
public class UserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteController() {
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
		UserManager um = new UserManager();
		String user = request.getParameter("delete_user");
		int userid = 0;

		/*
		 * --------------------------------------------
		 * 2. Validate data
		 * --------------------------------------------
		 */
		try {
			userid = Integer.parseInt(user);

		} catch (Exception ex) {
			response.sendRedirect("admin_user?errCode=deleteFailed");
			return;
		}

		/*
		 * -------------------------------------------- 
		 * 3. Process request
		 * --------------------------------------------
		 */
		int result = um.deleteUser(userid);
		if (result == 1) {
			response.sendRedirect("admin_user");

		} else {
			response.sendRedirect("admin_user?errCode=deleteFailed");
			return;

		}
	}

}
