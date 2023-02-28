package me.nicomunoz.kiroscraft.parkour.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;

public class BukkitQuery extends Query implements AsyncQuery {

	public BukkitQuery(Connection connection, String query, Object... data) {
		super(connection, query, data);
	}

	@Override
	public void executeAsync(boolean prepared, boolean select, QueryResult result) {
		Bukkit.getScheduler().runTaskAsynchronously(ParkourCore.getInstance().getMain(), () -> {
			try {
				if(select) {
					ResultSet rs = getResultSet(prepared);
					result.execute(true, rs, null);
				} else {
					result.execute(executeUpdate(), null, null);
				}
			} catch (SQLException e) {
				result.execute(false, null, e);
			}
		});
	}
	
	

}
