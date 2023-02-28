package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.utils.ParkourArenaUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.connection.Query;

public class ParkourGameUtils {
	
	public static boolean canPlay(ParkourGame game) {
		return ParkourArenaUtils.isFinished(game.getArena());
	}
	
	public static String[][] getTopRunners(ParkourArena arena, int limit, String[] def) throws SQLException {
		return getTopRunners(arena.getName(), limit, def);
	}
	
	public static String[][] getTopRunners(String arena, int limit, String[] def) throws SQLException {
		String[][] data = new String[limit][];
		ResultSet rs  = new Query(null, "SELECT player, time "
				+ "FROM map_player WHERE map = ? ORDER BY time DESC", arena).getResultSet(true);
		for(int i = 0; limit > i; i++) {
			if(rs == null || rs.isAfterLast()) {
				data[i] = def;
			} else {
				data[i] = new String[] { rs.getString("player"), getTime(rs.getLong("time")) };
				rs.next();
			}
		}
		return data ;
	}
	
	public static Location getSpawnLocation(ParkourArena arena, String player) throws JsonSyntaxException, SQLException {
		Query query = new Query(null, "SELECT location FROM map_player WHERE map = ? AND player = ?", arena.getName(), player);
		ResultSet rs = query.getResultSet(true);
		if(rs != null && rs.getString("location") != null) {
			JsonObject object = JsonParser.parseString(rs.getString("location")).getAsJsonObject();
			Location location = new Location(
					Bukkit.getWorld(object.get("world").getAsString()),
					object.get("x").getAsDouble(),
					object.get("y").getAsDouble() + 1,
					object.get("z").getAsDouble(),
					object.get("yaw").getAsFloat(),
					object.get("pitch").getAsFloat()
			);
			return location;
		}
		return arena.getSpawn();
	}
	
	public static boolean insertOrUpdateLocation(Location location, ParkourArena arena, String player) throws SQLException {
		JsonObject object = null;
		if(location != null) {
			object = new JsonObject();
			object.addProperty("world", location.getWorld().getName());
			object.addProperty("x", location.getX());
			object.addProperty("y", location.getY());
			object.addProperty("z", location.getZ());
			object.addProperty("yaw", location.getYaw());
			object.addProperty("pitch", location.getPitch());
		}
		Query query = new Query(null, "INSERT INTO map_player (map, player, location) VALUES (?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE location = ?", arena.getName(), player, object != null ? object.toString() : null, object != null ? object.toString() : null);
		return query.executeUpdate();
	}
	
	public static String getTime(long total) {
		long hours = total / 1000 / 3600;
		long minutes = (total / 1000 % 3600) / 60;
		long seconds = total / 1000 % 60;

		String second = String.valueOf(total * 0.001);
		second = second.substring(second.indexOf("."));
		return String.format("%02d:%02d:%02d", hours, minutes, seconds).concat(second);
	}

}
