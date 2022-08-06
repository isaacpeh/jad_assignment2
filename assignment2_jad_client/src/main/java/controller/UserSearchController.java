/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserManager;

/**
 * Servlet implementation class UserShowAllController
 */
@WebServlet("/admin_user_filter")
public class UserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserManager um = new UserManager();
		String userFilter = null;
		String userFilter_number = null;
		String userFilter_name = null;
		List<User> result = null;
		
		try {
			userFilter = request.getParameter("userfilter").trim();
		} catch (Exception ex) {
			// input error here
		}
		
		try {
			userFilter_number = request.getParameter("userFilterNumber").trim();
		} catch (Exception ex) {
			// input error here
		}
		
		try {
			userFilter_name = request.getParameter("userFilterName").trim();
		} catch (Exception ex) {
			// input error here
		}
		

		if (userFilter != null && userFilter.equalsIgnoreCase("address")) {
			result = um.showAllUsersAddr();

		} else if (userFilter != null && userFilter.equalsIgnoreCase("inactive")) {
			LocalDate dateNow = LocalDate.now();
			LocalDate dateMinus = dateNow.minusDays(90);
			System.out.println("Today's Date: " + dateNow);
			System.out.println("-90 Days: " + dateMinus);
			result = um.showAllUsersInactive(dateMinus.toString());
			
		} else if (userFilter_number != null) {
			result = um.showAllUsersNumber(userFilter_number);
			
		} else if (userFilter_name != null) {
			result = um.showAllUsersName(userFilter_name);
			
		} else { // maybe some error instead
			result = um.showAllUsers();
		}
		
		request.setAttribute("mgmtUsers", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_user.jsp");
		dispatcher.forward(request, response);

	}
}
