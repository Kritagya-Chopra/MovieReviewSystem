package dao;

import java.util.List;

import pojo.User;

public interface UserDao extends AutoCloseable {
	public int save(User u) throws Exception;
	public int update(User u) throws Exception;
	public int updatePassword(int userId, String newPassword) throws Exception;
	public User findByEmail(String email) throws Exception;
	public List<User> findAll() throws Exception;
}