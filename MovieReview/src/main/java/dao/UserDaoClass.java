package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pojo.User;

public class UserDaoClass extends Dao implements UserDao {

	@Override
	public int save(User u) throws Exception {
		int cnt;
		String sql = "insert into users values(Default,?,?,?,?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getFirst_name());
			stmt.setString(2, u.getLast_name());
			stmt.setString(3, u.getEmail());
			stmt.setString(6, u.getPassword());
			stmt.setString(4, u.getMobile());
			stmt.setDate(5, u.getBirth());
			cnt = stmt.executeUpdate();
		}
		return cnt;

	}

	@Override
	public int update(User u) throws Exception {
		int cnt;
		String sql = "update users set first_name = ? , last_name = ? , email = ?, password = ? , mobile = ?, birth = ? where id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(7, u.getId());
			stmt.setString(1, u.getFirst_name());
			stmt.setString(2, u.getLast_name());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getMobile());
			stmt.setDate(6, u.getBirth());
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}

	@Override
	public int updatePassword(int userId, String newPassword) throws Exception {
		int cnt;
		String sql = "update users set password = ? where id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(2, userId);
			stmt.setString(1, newPassword);
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}

	@Override
	public User findByEmail(String email) throws Exception {
		User u = null;
		String sql = "select * from users where email = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					String first_name = rs.getString("first_name");
					String last_name = rs.getString("last_name");
					email = rs.getString("email");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					Date birth = rs.getDate("birth");
					u = new User(id, first_name, last_name, email, mobile, birth, password);
				}
			}
		}
		return u;

	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> list = new ArrayList<User>();
		String sql = "select * from users";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String first_name = rs.getString("first_name");
					String last_name = rs.getString("last_name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					Date birth = rs.getDate("birth");
					list.add(new User(id, first_name, last_name, email, mobile, birth, password));
				}
			}
		}
		return list;
	}

}
