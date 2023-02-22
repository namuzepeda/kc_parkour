package me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.Rank;
import me.nicomunoz.kiroscraft.parkour.connection.MasterConnection;
import me.nicomunoz.kiroscraft.parkour.connection.Query;

public class RankAPI {
	
	public static Rank getRank(String player) throws SQLException {
		ResultSet rs = new Query(null, "SELECT rank FROM rank_player WHERE player = ?", player).getResultSet(true);
		if(rs != null) {
			return ParkourCore.getInstance().getRankManager().getRank(rs.getLong("rank"));
		}
		return null;
	}
	
	public static boolean setRank(String player, long rank, Connection... connection) throws SQLException {
		Connection conn = connection != null ? connection[0] : null;
		Query query = new Query(conn, "INSERT INTO rank_player (rank, player) VALUES (?, ?) ON DUPLICATE KEY UPDATE rank = ?", rank, player, rank);
		return query.executeUpdate();
	}

}
