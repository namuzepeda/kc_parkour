package me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;

public class ParkourArenaUtils {
	
	@SuppressWarnings("unchecked")
	public static HashMap<Location, ParkourCheckpoint> buildCheckpoints(String name) {
		HashMap<Location, ParkourCheckpoint> hm = new HashMap<>();
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getArena().getConfig();
		List<Location> checkpoints = (List<Location>) config.getList(name + ".checkpoints", new ArrayList<>());
		for(int i = 0; checkpoints.size() > i; i++) {
			hm.put(checkpoints.get(i), new ParkourCheckpoint(i, checkpoints.get(i)));
		}
		return hm;
	}
	
	public static boolean isFinished(ParkourArena arena) {
		return arena.getStart() != null && arena.getEnd() != null && arena.getSpawn() != null;
	}

}
