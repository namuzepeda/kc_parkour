package me.nicomunoz.kiroscraft.parkour.bukkit.core.economy;

import java.sql.SQLException;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.api.EconomyAPI;
import me.nicomunoz.kiroscraft.parkour.connection.Query;

public class EconomyManager {
	
	private Economy coins;
	
	public EconomyManager() throws SQLException {
		createTablesAndCoins();
		coins = new Economy(EconomyAPI.getId("coins"), "coins");
	}
	
	public Economy getCoins() {
		return this.coins;
	}
	
	private void createTablesAndCoins() throws SQLException {
		Query query = new Query(
				null,
				"CREATE TABLE IF NOT EXISTS `economy` (\n"
				+ "  `id_economy` bigint(20) NOT NULL AUTO_INCREMENT,\n"
				+ "  `name` varchar(255) NOT NULL,\n"
				+ "  PRIMARY KEY (`id_economy`),\n"
				+ "  UNIQUE KEY `UKh9tvawx78lolh34x122glkamp` (`name`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"
		);
		query.executeUpdate();
		query.setQuery("CREATE TABLE IF NOT EXISTS `economy_player` (\n"
				+ "  `balance` bigint(20) NOT NULL,\n"
				+ "  `player` varchar(16) NOT NULL,\n"
				+ "  `economy` bigint(20) NOT NULL,\n"
				+ "  PRIMARY KEY (`economy`,`player`),\n"
				+ "  KEY `FKhsg5uywfpq28apgi6lq4g7f6y` (`player`),\n"
				+ "  CONSTRAINT `FKh8kwthu3w1umiascgq48m2lln` FOREIGN KEY (`economy`) REFERENCES `economy` (`id_economy`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");
		query.executeUpdate();
		query.setQuery("INSERT IGNORE INTO `economy` (`name`) VALUES (?);");
		query.setData("coins");
		query.executeUpdate();
	}

}
