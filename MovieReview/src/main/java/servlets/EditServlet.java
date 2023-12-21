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

import dao.MovieDaoClass;
import dao.ReviewDaoClass;
import pojo.Movie;
import pojo.Review;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =  Integer.parseInt(req.getParameter("id")); 
		int movie_id = Integer.parseInt(req.getParameter("movie_id"));
		String review = req.getParameter("review");
		int rating = Integer.parseInt(req.getParameter("rating"));
		int user_id = Integer.parseInt(req.getParameter("user_id"));
		Review r = new Review(id,movie_id,review,rating,user_id,new Date(1000)); 
		try(ReviewDaoClass rd = new ReviewDaoClass())
		{
			rd.update(r);
			resp.sendRedirect("review");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Review</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method = 'POST' action = 'edit' >");
		
		try(ReviewDaoClass r = new ReviewDaoClass())
		{
			Review review = r.findById(id);
			out.printf("ID : <input type = 'number' name = 'id' readonly value = '%s' >\r\n<br>",review.getId());
			out.printf("Movie_id : <input type = 'number' name = 'movie_id' readonly value = '%s' >\r\n<br>",review.getMovie_id());
			out.printf("Review : <input type = 'text' name = 'review'  value = '%s' >\r\n<br>",review.getReview());
			out.printf("Rating : <input type = 'textarea' name = 'rating'  value = '%s' >\r\n<br>",review.getRating());
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
}
