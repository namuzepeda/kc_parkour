package me.nicomunoz.kiroscraft.parkour.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MasterConnection {
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection connection;
	
	private static String[] host = new String[3];
	
	public static void setHost(String[] hostt) {
		host[0] = "jdbc:mysql://" + hostt[0] + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true";
		host[1] = hostt[1];
		host[2] = hostt[2];
	}
	
	public static Connection getConnection(boolean nuevo) throws SQLException {
		if(nuevo) return getNewConnection();
		if(connection == null || connection.isClosed()) {
			connection = getNewConnection();
		}
		return connection;
	}
	
	private static Connection getNewConnection() throws SQLException {
		return DriverManager.getConnection(host[0], host[1], host[2]);
	}
	
	public static void removeConnection() throws SQLException {
		if(connection != null)
		connection.close();
		connection = null;
	}

}
