package me.nicomunoz.kiroscraft.parkour.bukkit.core.arena;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.utils.ParkourArenaUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourGameUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.hologram.handlers.LeaderboardHandler;
import me.nicomunoz.kiroscraft.parkour.connection.Query;

public class ParkourArenaManager {
	
	private HashMap<String, ParkourArena> arenas;
	
	public ParkourArenaManager() throws SQLException {
		this.arenas = new HashMap<>();
		createMapPlayerTable();
		rebuild();
	}
	
	public Collection<ParkourArena> arenas() {
		return this.arenas.values();
	}
	
	public void rebuild() throws SQLException {
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getArena().getConfig();
		for(String name : config.getConfigurationSection("").getKeys(false)) {
			create(name);
		}
	}
	
	public boolean exists(String name) {
		return arenas.containsKey(name.toLowerCase());
	}
	
	public void remove(String name) {
		arenas.remove(name);
	}
	
	public ParkourArena create(String name) throws SQLException {
		ParkourArena arena = buildArena(name);
		arenas.put(name.toLowerCase(), arena);
		return arena;
	}
	
	public ParkourArena get(String name) {
		return arenas.get(name.toLowerCase());
	}
	
	private ParkourArena buildArena(String name) throws SQLException {
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getArena().getConfig();
		return new ParkourArena(
				name,
				config.getLocation(name + ".start"),
				config.getLocation(name + ".end"),
				config.getLocation(name + ".spawn"),
				ParkourArenaUtils.buildCheckpoints(name),
				ParkourMode.valueOf(config.getString(name + ".mode")),
				new LeaderboardHandler(name, config.getLocation(name + ".leaderboard"))
		);
	}
	
	private void createMapPlayerTable() throws SQLException {
		new Query(
				null,
				"CREATE TABLE IF NOT EXISTS `arena_player` (\n"
				+ "  `arena` varchar(255) NOT NULL,\n"
				+ "  `player` varchar(16) NOT NULL,\n"
				+ "  `time` bigint NULL,\n"
				+ "  `location` text NULL,\n"
				+ "  PRIMARY KEY (`arena`,`player`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"
		).executeUpdate();
	}

}
