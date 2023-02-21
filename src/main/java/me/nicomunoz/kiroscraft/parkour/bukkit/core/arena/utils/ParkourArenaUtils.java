package me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.utils;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;

public class ParkourArenaUtils {
	
	public static HashMap<Location, ParkourCheckpoint> buildCheckpoints() {
		HashMap<Location, ParkourCheckpoint> hm = new HashMap<>();
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getArena().getConfig();
		if(config.contains("checkpoints") && config.isConfigurationSection("checkpoints")) {
			for(String stringId : config.getConfigurationSection("checkpoints").getKeys(false)) {
				int id = Integer.parseInt(stringId);
				Location location = config.getLocation("checkpoints." + id);
				hm.put(location, new ParkourCheckpoint(id, location));
			}
		}
		return hm;
	}
	
	public static boolean isFinished(ParkourArena arena) {
		return arena.getStart() != null && arena.getEnd() != null && arena.getSpawn() != null;
	}

}
