package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TourRecord;
import model.TourRecordManager;
import model.User;
import model.UserManager;

/**
 * Servlet implementation class SalesSearchController
 */
@WebServlet("/admin_sales_filter")
public class SalesSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		UserManager um = new UserManager();
		TourRecordManager trm = new TourRecordManager();
		String salesFilter = null;
		String dateFrom = null;
		String dateTo = null;
		int salesTourid = -1;
		List<User> userResult = null;
		List<TourRecord> salesResult = null;
		
		// ---------------------------------------------
		// (1) Pulling Data
		// ---------------------------------------------
		try {
			salesFilter = request.getParameter("salesfilter").trim();
		} catch (Exception ex) {
			// input error here
		}
		
		try {
			dateFrom = request.getParameter("dateFrom").trim() + " 00:00:00";
			dateTo = request.getParameter("dateTo").trim() + " 23:59:59";
		} catch (Exception ex) {
			// input error here
		}
		
		try {
			salesTourid = Integer.parseInt(request.getParameter("salesTourid").trim());
		} catch (Exception ex) {
			// input error here
		}
		
		// ---------------------------------------------
		// (2) Processing data
		// ---------------------------------------------
		if (salesFilter != null && salesFilter.equalsIgnoreCase("topOrders")) {
			// TOP 10 USERS BY ORDERS
			userResult = trm.showTopUsersOrder();
			request.setAttribute("mgmtSalesUsers", userResult);
			
		} else if (salesFilter != null && salesFilter.equalsIgnoreCase("topValue")) { 
			// TOP 10 USERS BY VALUE
			userResult = trm.showTopUsersValue();
			request.setAttribute("mgmtSalesUsers", userResult);
			
		} else if (dateFrom != null && dateTo != null) {
			// BOOK BETWEEN DATE RANGE
			salesResult = trm.getRecordByDate(dateFrom, dateTo);
			request.setAttribute("mgmtSalesTours", salesResult);
			
		} else if (salesTourid != -1) {
			// USERS WHO BOOK CERTAIN TOUR
			salesResult = trm.getUserByRecord(salesTourid);
			request.setAttribute("mgmtSalesUsers", salesResult);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("test");
		dispatcher.forward(request, response);
	}

}
