package me.nicomunoz.kiroscraft.parkour.connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryResult {
	
	public void execute(boolean ok, ResultSet rs, SQLException error);

}
