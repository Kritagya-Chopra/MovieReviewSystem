package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pojo.Review;

public class ReviewDaoClass extends Dao implements ReviewDao {

	
	@Override
	public int save(Review r) throws Exception {
		int cnt = 0;
		String sql = "insert into reviews values (default,?,?,?,?,now() )";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, r.getMovie_id());
			stmt.setString(2, r.getReview());
			stmt.setInt(3, r.getRating());
			stmt.setInt(4, r.getUser_id());
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}

	@Override
	public int update(Review r) throws Exception {
		int cnt = 0;
		String sql = "update reviews set movie_id = ? , review = ? ,rating = ? , user_id = ? , modified = now() where id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(5, r.getId());
			stmt.setInt(1, r.getMovie_id());
			stmt.setString(2, r.getReview());
			stmt.setInt(3, r.getRating());
			stmt.setInt(4, r.getUser_id());
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}

	@Override
	public List<Review> findAll() throws Exception {
		List<Review> ls = new ArrayList<Review>();
		String sql = "select * from reviews ORDER BY modified DESC";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			try(ResultSet rs = stmt.executeQuery())
			{
				while(rs.next())
				{
					int id = rs.getInt("id") ;
					int movie_id = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int user_id = rs.getInt("user_id");
					Date modified = rs.getDate("modified");
					Review r = new Review(id,movie_id,review,rating,user_id,modified);
					ls.add(r);
				}
			}
		}
		 
		return ls;
	}

	@Override
	public List<Review> findByUserId(int userId) throws Exception {
		List<Review> ls = new ArrayList<Review>();
		String sql = "select * from reviews where user_id = ? ";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, userId);
			try(ResultSet rs = stmt.executeQuery())
			{
				while(rs.next())
				{
					int id = rs.getInt("id") ;
					int movie_id = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int user_id = rs.getInt("user_id");
					Date modified = rs.getDate("modified");
					Review r = new Review(id,movie_id,review,rating,user_id,modified);
					ls.add(r);
				}
			}
		}
		 
		return ls;
	}

	@Override
	public List<Review> getSharedWithUser(int userId) throws Exception {
		List<Review> ls = new ArrayList<Review>();
		String sql = "select * from reviews a , shares b where a.user_id = b.user_id and a.user_id  = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, userId);
			try(ResultSet rs = stmt.executeQuery())
			{
				while(rs.next())
				{
					int id = rs.getInt("id") ;
					int movie_id = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int user_id = rs.getInt("user_id");
					Date modified = rs.getDate("modified");
					Review r = new Review(id,movie_id,review,rating,user_id,modified);
					ls.add(r);
				}
			}
		}
		return ls;
	}

	@Override
	public Review findById(int id) throws Exception {
		Review review = null;
		String sql = "select * from reviews where id = ? " ;
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery())
			{
				while(rs.next())
				{
					int movie_id = rs.getInt("movie_id");
					String revie = rs.getString("review");
					int rating = rs.getInt("rating");
					int user_id = rs.getInt("user_id");
					Date modified = rs.getDate("modified");
					review = new Review(id,movie_id,revie,rating,user_id,modified);
					
				}
			}
		}
		return review;
	}

	@Override
	public int deleteById(int reviewId) throws Exception {
		int cnt = 0;
		String sql = "delete from reviews where id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, reviewId);
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}

	@Override
	public int shareReview(int reviewId, int userId) throws Exception {
		int cnt = 0;
		String sql = "insert into shares values (? , ? )";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, reviewId);
			stmt.setInt(2, userId);
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}

}
