package me.nicomunoz.kiroscraft.parkour.bukkit.core.arena;

import org.bukkit.configuration.file.FileConfiguration;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.utils.ParkourArenaUtils;

public class ParkourArenaManager {
	
	private ParkourArena arena;
	
	public ParkourArenaManager() {
		rebuild();
	}
	
	public void rebuild() {
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getArena().getConfig();
		this.arena = new ParkourArena(
			config.getLocation("start"),
			config.getLocation("end"),
			config.getLocation("spawn"),
			ParkourArenaUtils.buildCheckpoints()
		);
	}
	
	public void setArena(ParkourArena arena) {
		this.arena = arena;
	}
	
	public ParkourArena getArena() {
		return this.arena;
	}

}
