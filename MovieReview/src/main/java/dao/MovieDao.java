package dao;

import java.util.List;

import pojo.Movie;

public interface MovieDao extends AutoCloseable {
	public List<Movie> findAll() throws Exception;
	public Movie findById(int id) throws Exception;
}
