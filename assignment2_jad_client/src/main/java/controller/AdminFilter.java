/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserManager;

/**
 * Servlet Filter implementation class AdminAuthentication
 */
@WebFilter(urlPatterns = {
		// "/admin_tours_filter",
		"/admin_user", 
		"/admin_user.jsp",
		"/admin_tours", 
		"/admin_tours.jsp", 
		"/admin_category",
		"/UserDeleteController",
		"/TourAddController",
		"/TourDeleteController",
		"/TourUpdateController",
		"/CategoryDeleteController",
		"/CategoryAddController" })

public class AdminFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AdminFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("----------------- Filter -----------------");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		UserManager um = new UserManager();
		int loggedInUser = 0;
		try {
			loggedInUser = (int) httpServletRequest.getSession(false).getAttribute("sessUserID");

		} catch (Exception ex) {

		}

		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		if (loggedInUser == 0) {
			System.out.println("No user id: " + loggedInUser);
			httpServletResponse.sendRedirect("index.jsp");
			System.out.println("----------------- Filter End -----------------");
			return;
		}
		
		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		User result = um.showUser(loggedInUser);

		if (result == null || !result.getRole().equals("Admin")) {
			System.out.println("Unauthorized user id: " + loggedInUser);
			System.out.println("----------------- Filter End -----------------");
			httpServletResponse.sendRedirect("index.jsp");
			return;

		} else {
			// pass the request along the filter chain
			System.out.println("Authorized admin id: " + loggedInUser);
			System.out.println("----------------- Filter End -----------------");
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
