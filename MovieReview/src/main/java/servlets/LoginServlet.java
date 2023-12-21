package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoClass;
import pojo.User;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(UserDaoClass u = new UserDaoClass())
		{
			User user = u.findByEmail(req.getParameter("email"));
			if(user!=null && user.getPassword().equals(req.getParameter("password"))) {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				resp.sendRedirect("review");
			}
			else
			{
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<html>");
				out.println("<title>wrong cred</title>");
				out.println("</head>");
				out.println("<body>");
				out.printf("<h1> Hi %s </h1>\r\n" , req.getParameter("email") );
				out.println("<h2> Wrong ID or Password </h2>");
				out.println("<a href ='index.html' > Login </a> ");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
