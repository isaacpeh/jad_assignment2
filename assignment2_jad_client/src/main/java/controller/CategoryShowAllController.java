/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Category;

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

		Client client = ClientBuilder.newClient();
		String restUrl = "http://localhost:8080/assignment2_jad_server/ToursWS/getCategories";
		WebTarget target = client.target(restUrl);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "generatedapi");
		Response resp = invocationBuilder.get();

		if (resp.getStatus() == Response.Status.OK.getStatusCode()) {
			ArrayList<Category> catArr = resp.readEntity(new GenericType<ArrayList<Category>>() { });

			if (catArr == null || catArr.size() == 0) {
				RequestDispatcher rd = request.getRequestDispatcher(source + "?err=noCategoriesFound");
				rd.forward(request, response);
				return;

			} else {
				request.setAttribute("reqCategories", catArr);
				RequestDispatcher rd = request.getRequestDispatcher(source);
				rd.forward(request, response);
				return;
			}

		} else {
			RequestDispatcher rd = request.getRequestDispatcher(source + "?err=responseError");
			rd.forward(request, response);
			return;
		}

		/*
		 * String source = "index.jsp";
		 * 
		 * if (request.getRequestURI().contains("categories")) { source =
		 * "categories.jsp"; } else if
		 * (request.getRequestURI().contains("admin_category")) { source =
		 * "admin_tours.jsp"; }
		 * 
		 * CategoryManager cm = new CategoryManager(); List<Category> result =
		 * cm.showCategories();
		 * 
		 * request.setAttribute("reqCategories", result); RequestDispatcher dispatcher =
		 * request.getRequestDispatcher(source); dispatcher.forward(request, response);
		 */

	}
}
