package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MovieDaoClass;
import pojo.Review;
import pojo.User;
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Reviews</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>You Have logged-out </h2>");
		out.println(" <a href = 'index.html'>Log-In Agin</a> ");
		out.println("</body>");
		out.println("</html>");
	}
}
