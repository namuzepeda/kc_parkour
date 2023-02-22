package me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.session.EconomyTransaction;
import me.nicomunoz.kiroscraft.parkour.connection.Query;

public class EconomyAPI {
	
	public static long getId(String name) throws SQLException {
		ResultSet rs  = new Query(null, "SELECT id_economy FROM economy WHERE name = ?", name).getResultSet(true);
		if(rs != null) {
			return rs.getLong("id_economy");
		}
		return 0;
	}
	
	public static boolean withdraw(EconomyTransaction transaction, Connection... connection) throws SQLException {
		return withdraw(transaction.getEconomy().getId(), transaction.getPlayer(), transaction.getAmount(), connection);
	}
	
	public static boolean withdraw(long economy, String player, int lose, Connection... connection) throws SQLException {
		Connection conn = connection != null ? connection[0] : null;
		return new Query(conn, "UPDATE economy_player SET balance = (balance - ?) WHERE player = ? AND economy = ?", lose, player, economy).executeUpdate();
	}
	
	public static boolean deposit(EconomyTransaction transaction, Connection... connection) throws SQLException {
		return deposit(transaction.getEconomy().getId(), transaction.getPlayer(), transaction.getAmount(), connection);
	}
	
	public static boolean deposit(long economy, String player, int gain, Connection... connection) throws SQLException {
		Connection conn = connection != null ? connection[0] : null;
		Query query = new Query(conn, "INSERT INTO economy_player (balance, player, economy) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE balance = (balance + ?)", gain, player, economy, gain);
		return (query.executeUpdate());
	}
	
	public static int getBalance(long economy, String player) throws SQLException {
		Query query = new Query(null, "(SELECT balance FROM economy_player WHERE economy = ? AND player = ?) UNION (SELECT 0)", economy, player);
		ResultSet rs = query.getResultSet(true);
		if(rs != null) return rs.getInt("balance");
		return 0;
	}

}
