package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MovieDaoClass;
import dao.ReviewDaoClass;
import pojo.Review;
import pojo.User;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (ReviewDaoClass rv = new ReviewDaoClass()) {

			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Reviews</title>");
			out.println("</head>");
			out.println("<body>");
			HttpSession session = req.getSession();
			User u = (User) session.getAttribute("user");
			String type = req.getParameter("type");
			List<Review> list = new ArrayList<Review>();
			out.printf("<h5>Hello, %s</h5>\r\n <br> <br>", u.getFirst_name());
			out.println("<a href = 'review?type=all'>All Reviews     </a>   <a href = 'review?type=my'>    My Reveiws    </a>     <a href = 'review?type=shared'>   Shared Reviews    </a>  <br> <br>");
			String s = "";
			
			if (type == null || type.equals("all")) {
				list = rv.findAll();
				s="All";
			} else if (type.equals("my")) {
				list = rv.findByUserId(u.getId());
				s="My";
			} else {
				list = rv.getSharedWithUser(u.getId());
				s="Shared";
			}
			out.printf("<h1>%s Reviews</h1>\r\n",s);
			
			out.println("<table border='1'>");
			out.println("<thead>");
			out.println("<th>Id</th>");
			out.println("<th>Movie</th>");
			out.println("<th>Rating</th>");
			out.println("<th>Review</th>");
			out.println("<th>Actions</th>");
			out.println("</thead>");
			out.println("<tbody>");
			
			for (Review r : list) {
				out.println("<tr>");
				out.printf("<td>%s</td>\r\n", r.getId());
				try (MovieDaoClass mv = new MovieDaoClass()) {
					out.printf("<td>%s</td>\r\n", mv.findById(r.getMovie_id()).getTitle());
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				out.printf("<td>%s</td>\r\n <br>", r.getRating());
				out.printf("<td>%s</td>\r\n", r.getReview());
				if (r.getUser_id() == u.getId()) {
					out.printf(
							"<td><a href='edit?id=%s'><img src='edit.png' alt='EDIT' height='24'  width = '24' /></a>  <a href = 'delete?id=%s' ><img src = 'delete.png' height = '24' width = '24' alt = 'Delete' /></a> <a href = 'share?id=%s' ><img src = 'share.png' height = '24' width = '24' alt='Update' /></a></td>\r\n",
							r.getId(), r.getId(),r.getId());
				} else {
					out.printf(
							"<td><img src = 'cantEdit.png' alt='EDIT' height='24'  width = '24' >  <img src = 'cantDel.png'  height = '24' width = '24' alt = 'Delete' > <img src = 'cantShare.png'  height = '24' width = '24' alt = 'Update'></td>\r\n");
				}
				out.println("</tr>");
			}

			out.println("</tbody>");
			out.println("</table> <br>");
			out.println("<a href = 'AddReview'>Add Review</a>    <a href = 'logout'>Sign-Out</a> ");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}
}
