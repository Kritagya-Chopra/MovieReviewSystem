package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MovieDaoClass;
import dao.ReviewDaoClass;
import pojo.Movie;
import pojo.Review;
import pojo.User;
@WebServlet("/AddReview")
public class AddReviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Add Review</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method = 'POST' action = 'AddReview' >");
		out.println("<select name='movie' >\r\n");
		try(MovieDaoClass m = new MovieDaoClass())
		{
			List<Movie> ls = m.findAll();
			for(Movie mv :ls) {
				out.printf("<option value=%d>%s</option>\r\n", mv.getId(),mv.getTitle());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
				
		out.println("</select> <br>");
		out.println("Rating : <input type = 'number' name = 'rating' > <br>");
		out.println("Review : <input type = 'textarea' name = 'review' > <br>");
		out.println("<input type = 'submit' value = 'Save' >");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = 1;
		int movie_id = Integer.parseInt(req.getParameter("movie"));
		String review = req.getParameter("review");
		int rating = Integer.parseInt(req.getParameter("rating"));
		HttpSession s = req.getSession();
		User u = (User) s.getAttribute("user");
		int user_id = u.getId();
		
		Review r = new Review(id,movie_id,review,rating,user_id,new Date(1000));
		try(ReviewDaoClass r1 = new ReviewDaoClass())
		{
			r1.save(r);
			resp.sendRedirect("review");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
