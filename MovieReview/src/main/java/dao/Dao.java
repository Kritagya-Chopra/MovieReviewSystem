package dao;

import java.sql.Connection;

import Utils.DbUtils;

public class Dao implements AutoCloseable {
	protected Connection con;
	public Dao() {
		try {
			con = DbUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void close() throws Exception {
		try {
		if(con!=null)
			con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
