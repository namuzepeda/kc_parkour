package me.nicomunoz.kiroscraft.parkour.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import me.nicomunoz.kiroscraft.parkour.shared.utils.ArrayUtils;

public class Query {
	
	private String query;
	private Object[] data = null;
	private Connection connection;
	
	public Query setData(Object... data) {
		this.data = data;
		return this;
	}
	
	public String getQuery() {
		return this.query;
	}
	
	public Query appendData(Object... data)
	{
		if(this.data != null && this.data.length != 0)
			if(this.data.length > 1)
				this.data = ArrayUtils.append(this.data, data);
			else
				this.data = ArrayUtils.append(this.data, data[0]);
		return this;
	}
	
	public Query setQuery(String query) {
		this.query = query;
		return this;
	}
	
	public void appendQuery(String query) {
		if(this.query.contains(";")) this.query = this.query.substring(0, this.query.indexOf(";"));
		this.query += query;
	}
	
	public void addData(Object... data) {
		ArrayList<Object> old;
		if(this.data != null) old = new ArrayList<>(Arrays.asList(this.data));
		else old = new ArrayList<>();
		old.addAll(Arrays.asList(data));
		this.data = old.toArray(new Object[old.size()]);
	}
	
	public Query(Connection connection, String query, Object... data) {
		re(connection, query, data);
	}
	
	public void re(Connection connection, String query, Object... data) {
		this.query = query;
		if(query != null && !this.query.endsWith(";")) this.query +=";";
		if(data != null) this.data = data;
		if(connection == null)
			try {
				connection = MasterConnection.getConnection(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		this.connection = connection;
	}
	
	public boolean executeUpdate(PreparedStatement ps) throws SQLException {
		return ps.executeUpdate() > 0;
	}
	
	public PreparedStatement getPreparedStatement(boolean keys) throws SQLException {
		PreparedStatement ps;
		if(!keys) ps = connection.prepareStatement(query);
		else ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			if(data != null)
			for(int ii = 0; data.length > ii; ii++) {
				ps.setObject(ii + 1, data[ii]);
			}
		return ps;
	}
	
	public boolean executeUpdate() throws SQLException {
		PreparedStatement ps = getPreparedStatement(false);
		return ps.executeUpdate() > 0;
	}
	
	public Statement getStatement() {
		try {
			return connection.createStatement();
		} catch(SQLException e ) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getResultSet(boolean prepared) throws SQLException {
		ResultSet rs;
		if(prepared) rs = getPreparedStatement(false).executeQuery();
		else rs = getStatement().executeQuery(query);
		
		if(!rs.next()) {
			return null;
		}
		return rs;
	}
}
