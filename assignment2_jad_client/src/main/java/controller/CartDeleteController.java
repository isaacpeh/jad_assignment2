package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;

/**
 * Servlet implementation class CartDeleteController
 */
@WebServlet("/CartDeleteController")
public class CartDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Cart> sessCart =(ArrayList<Cart>)request.getSession().getAttribute("sessCart");
		String[] deleteItems = request.getParameterValues("deleteItems");
		ArrayList<Integer> toBeRemoved= new ArrayList<>();
		session.removeAttribute("sessCart");

		if (deleteItems == null || deleteItems.length == 0) {
			response.sendRedirect("cart.jsp?errCode=noSelectedItems");
			return;
		}
		
		for(int i = sessCart.size()-1; i >= 0; i--) {
			int sessionId = sessCart.get(i).getTourId();
			for(int j = 0; j < deleteItems.length; j++) {
				int deleteItemId = Integer.parseInt(deleteItems[j]);
				if(sessionId == deleteItemId) {
					sessCart.remove(i);
				}
			}
		}

		if(sessCart.isEmpty()) {
			sessCart=null;
		}
		
		session.setAttribute("sessCart", sessCart);
		response.sendRedirect("cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
