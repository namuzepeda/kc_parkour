package me.nicomunoz.kiroscraft.parkour.bukkit.core.arena;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.utils.ParkourArenaUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;

public class ParkourArenaManager {
	
	private HashMap<String, ParkourArena> arenas;
	
	public ParkourArenaManager() {
		this.arenas = new HashMap<>();
		rebuild();
	}
	
	public Collection<ParkourArena> arenas() {
		return this.arenas.values();
	}
	
	public void rebuild() {
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getArena().getConfig();
		for(String name : config.getConfigurationSection("").getKeys(false)) {
			create(name);
		}
	}
	
	public boolean exists(String name) {
		return arenas.containsKey(name.toLowerCase());
	}
	
	public ParkourArena create(String name) {
		ParkourArena arena = buildArena(name);
		arenas.put(name.toLowerCase(), arena);
		return arena;
	}
	
	private ParkourArena buildArena(String name) {
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getArena().getConfig();
		return new ParkourArena(
				name,
				config.getLocation(name + ".start"),
				config.getLocation(name + ".end"),
				config.getLocation(name + ".spawn"),
				ParkourArenaUtils.buildCheckpoints(name),
				ParkourMode.valueOf(config.getString(name + ".mode"))
		);
	}

}
