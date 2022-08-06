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
 * Servlet implementation class CartQuantityController
 */
@WebServlet("/CartQuantityController")
public class CartQuantityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartQuantityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String btnType = request.getParameter("btn");
		int tourid = Integer.parseInt(request.getParameter("tourid"));
		List<Cart> sessCart =(ArrayList<Cart>)request.getSession().getAttribute("sessCart");
		if(sessCart == null || sessCart.isEmpty()) {
			response.sendRedirect("cart.jsp?errCode=CartEmpty");
		}
		
		System.out.println(btnType);
		
			for(int i=0; i<sessCart.size();i++) {
				int sessCartId = sessCart.get(i).getTourId();
				if(sessCartId == tourid) {
					if(btnType.equals("minus")) {
						if(sessCart.get(i).getQuantity() <= 1) {
							response.sendRedirect("cart.jsp");
							return;
						}
						sessCart.get(i).minusQuantity();
						System.out.println(sessCart.get(i).getQuantity());
						System.out.println("Entered minus");
						session.removeAttribute("sessCart");
						session.setAttribute("sessCart", sessCart);
						response.sendRedirect("cart.jsp");
					}
					else {
						if(sessCart.get(i).getQuantity() >= 99) {
							response.sendRedirect("cart.jsp");
							return;
						}
						sessCart.get(i).addQuantity();
						System.out.println(sessCart.get(i).getQuantity());
						System.out.println("Entered plus");
						session.removeAttribute("sessCart");
						session.setAttribute("sessCart", sessCart);
						response.sendRedirect("cart.jsp");
					}
					
				}
			}
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
