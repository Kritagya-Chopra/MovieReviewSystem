package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDaoClass;
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		try(ReviewDaoClass r = new ReviewDaoClass()){
			r.deleteById(id);
			resp.sendRedirect("review");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
