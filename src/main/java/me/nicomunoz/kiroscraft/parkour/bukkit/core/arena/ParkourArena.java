package me.nicomunoz.kiroscraft.parkour.bukkit.core.arena;

import java.util.HashMap;
import org.bukkit.Location;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;

public class ParkourArena {
	
	private Location start;
	private Location end;
	private Location spawn;
	private HashMap<Location, ParkourCheckpoint> checkpoints;
	
	public ParkourArena(Location start, Location end, Location spawn, HashMap<Location, ParkourCheckpoint> checkpoints) {
		this.start = start;
		this.end = end;
		this.spawn = spawn;
		this.checkpoints = checkpoints;
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

}
