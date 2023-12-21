package pojo;

import java.sql.Date;

public class Movie {
	int id;
	String title ;
	Date release;
	public Movie(int id, String title, Date release) {
		this.id = id;
		this.title = title;
		this.release = release;
	}
	public Movie() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRelease() {
		return release;
	}
	public void setRelease(Date release) {
		this.release = release;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", release=" + release + "]";
	}
}
