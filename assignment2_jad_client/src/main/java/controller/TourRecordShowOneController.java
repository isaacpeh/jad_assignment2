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

/**
 * Servlet implementation class TourRecordShowOneController
 */
@WebServlet("/TourRecordShowOneController")
public class TourRecordShowOneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TourRecordShowOneController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		TourRecordManager trm = new TourRecordManager();
		int loggedInUser = 0;
		try {
			loggedInUser = (int) request.getSession().getAttribute("sessUserID");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

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
		List<TourRecord> result = trm.getRecordByUser(loggedInUser);

		if (result == null) {
			response.sendRedirect("profile.jsp?errCode=NoRecords");
			return;

		} else {
			request.setAttribute("reqProfileRecords", result);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
			dispatcher.forward(request, response);
		}
	}
}
