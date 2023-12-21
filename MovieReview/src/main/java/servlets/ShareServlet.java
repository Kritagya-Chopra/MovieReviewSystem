package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDaoClass;
import dao.ReviewDaoClass;
import dao.UserDaoClass;
import pojo.Movie;
import pojo.Review;
import pojo.User;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Review</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method = 'POST' action = 'share' >");
		
		try(ReviewDaoClass r = new ReviewDaoClass())
		{
			Review review = r.findById(Integer.parseInt(req.getParameter("id")));
			out.printf("ID : <input type = 'number' name = 'id' readonly value = '%s' >\r\n<br>",review.getId());
			try(UserDaoClass ud = new UserDaoClass())
			{
				out.println("USER : <select name='user' >\r\n");
				List<User> ls = ud.findAll();
				for(User u : ls) {
					out.printf("<option value=%d>%s</option>\r\n", u.getId(),u.getFirst_name());
				}
				out.println("</select> <br>");
			}
			out.printf("User_id : <input type = 'number' name = 'user_id' readonly value = '%s' >\r\n<br>",review.getUser_id());
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
				
		
		out.println("<input type = 'submit' value = 'Save' >");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int review_id = Integer.parseInt(req.getParameter("id"));
		int user_id = Integer.parseInt(req.getParameter("user_id"));
		try(ReviewDaoClass r = new ReviewDaoClass())
		{
			r.shareReview(review_id, user_id);
			resp.sendRedirect("review");
		} catch (Exception e) {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Reviews</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h2>this is already shared</h2>");
			out.println(" <a href = 'review'>Bach to Dashboard</a> ");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
