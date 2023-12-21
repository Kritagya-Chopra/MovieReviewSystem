package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoClass;
import pojo.User;
@WebServlet("/add_user")
public class AddUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String first_name = req.getParameter("first_name");
		String last_name = req.getParameter("last_name");
		String email= req.getParameter("email");
		String mobile= req.getParameter("mobile");
		Date birth = Date.valueOf(req.getParameter("birth"));
		String password = req.getParameter("password");
		User user = new User(1,first_name,last_name,email,mobile,birth,password);
		try(UserDaoClass u = new UserDaoClass())
		{
			int cnt = u.save(user);
			if(cnt == 1)
			{
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Register</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h5>User Registered Succesfully.</h5>");
				out.println("<a href='index.html'>Log-in</a>");
				out.println("</body>");
				out.println("</html>");
			}
			else
			{
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Register</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h5>Some Error Occured.</h5>");
				out.println("<a href='register.html'>try again</a>");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}
}
