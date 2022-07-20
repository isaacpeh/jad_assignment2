/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.User;
import model.UserManager;

/**
 * Servlet implementation class UserUpdateController
 */

@MultipartConfig
@WebServlet("/UserUpdateController")
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* --------------------------------------------
		 * 1. Pull data
		 * -------------------------------------------- */
		UserManager um = new UserManager();

		String loginid = request.getParameter("update_loginid");
		String password = request.getParameter("update_password");
		String email = request.getParameter("update_email");
		String contact = request.getParameter("update_contact");
		String pic = request.getParameter("current_pic");
		String role = " ";

		int loggedInUser = 0;
		
		System.out.println("Default Pic: " + pic);
		
		try {
			int contactNo = Integer.parseInt(contact);
		} catch (Exception ex) {
			response.sendRedirect("profile?errCode=incorrectType");
			return;
		}
		
		try {
			loggedInUser = (int) request.getSession().getAttribute("sessUserID");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/* --------------------------------------------
		 * 2. Validate data
		 * -------------------------------------------- */
		if (loginid == null || loginid.trim().equals("") || password == null || password.trim().equals("")
				|| email == null || email.trim().equals("") || contact == null || contact.trim().equals("")) {
			response.sendRedirect("profile?errCode=failedUpdate");
			return;
		}

		if (loggedInUser == 0) {
			response.sendRedirect("login.jsp?errCode=userNotFound");
			return;
		}
		
		Part file = request.getPart("update_pic");
		
		String imageFileName = file.getSubmittedFileName().toString();
		System.out.println("imageFileName: " + imageFileName);
		int index = imageFileName.lastIndexOf('.');
		String extension = imageFileName.substring(index + 1);
		System.out.println(extension);

		if (imageFileName.equals("")) {
			imageFileName = pic;
		} else {
			imageFileName = loginid + "." + extension;

			System.out.println("final -" + imageFileName);
			try {
				String uploadPath = request.getSession().getServletContext().getRealPath("/")
						+ "images\\user_profile_pics\\" + imageFileName;
				
				FileOutputStream fos = new FileOutputStream(uploadPath);
				InputStream is = file.getInputStream();
				
				byte[] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.print("Picture" + pic);
		
		System.out.println("Selected image for file name: " + imageFileName);
		
		String test1 = request.getSession().getServletContext().getRealPath("/")
				+ "images\\user_profile_pics\\" + imageFileName;
		System.out.println(test1 + " - AOK");

		/* --------------------------------------------
		 * 3. Process request
		 * -------------------------------------------- */
		User update_user = new User(loginid, email, contact, password, role);
		update_user.setPicUrl(imageFileName);
		int result = um.updateUser(loggedInUser, update_user);

		if (result == 1) {
			// Pass
			response.sendRedirect("profile?succCode=successfulUpdate");

		} else if (result == -1) {
			// Duplicate Entry
			response.sendRedirect("profile?errCode=duplicateUpdate");
		} else {
			// Other Error
			System.out.println(result);
			response.sendRedirect("profile?errCode=failedUpdate");
		}
	}

}
