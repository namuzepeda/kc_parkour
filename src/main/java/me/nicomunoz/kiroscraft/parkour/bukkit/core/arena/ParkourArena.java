package me.nicomunoz.kiroscraft.parkour.bukkit.core.arena;

import java.util.HashMap;
import org.bukkit.Location;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;

public class ParkourArena {
	
	private String name;
	private Location start;
	private Location end;
	private Location spawn;
	private HashMap<Location, ParkourCheckpoint> checkpoints;
	private ParkourMode mode;
	
	public ParkourArena(String name, Location start, Location end, Location spawn, HashMap<Location, ParkourCheckpoint> checkpoints, ParkourMode mode) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.spawn = spawn;
		this.checkpoints = checkpoints;
		this.mode = mode;
	}
	
	public String getName() {
		return this.name;
	}

	public Location getStart() {
		return start;
	}

	public Location getEnd() {
		return end;
	}

	public Location getSpawn() {
		return this.spawn;
	}
	
	public HashMap<Location, ParkourCheckpoint> getCheckpoints() {
		return checkpoints;
	}
	
	public ParkourMode getMode() {
		return this.mode;
	}

}
