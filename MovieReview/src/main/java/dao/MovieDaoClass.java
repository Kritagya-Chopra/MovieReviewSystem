package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pojo.Movie;

public class MovieDaoClass extends Dao implements MovieDao{

	@Override
	public List<Movie> findAll() throws Exception {
		List<Movie> list = new ArrayList<Movie>();
		String stmt = "select * from movies";
		try (PreparedStatement st = con.prepareStatement(stmt))
		{
			try(ResultSet rs = st.executeQuery()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String title  = rs.getString("title");
					Date release = rs.getDate("rel_date");
					list.add(new Movie(id,title,release));
				}
			}
		}

		return list;		
	}

	@Override
	public Movie findById(int id) throws Exception {
		Movie m = null;
		String stmt = "select * from movies where id = ?";
		try (PreparedStatement st = con.prepareStatement(stmt))
		{
			st.setInt(1, id);
			try(ResultSet rs = st.executeQuery()){
				while(rs.next()) {
					String title  = rs.getString("title");
					Date release = rs.getDate("rel_date");
					m = new Movie(id,title,release);
				}
			}
		}
		return m;	
	}	
}
