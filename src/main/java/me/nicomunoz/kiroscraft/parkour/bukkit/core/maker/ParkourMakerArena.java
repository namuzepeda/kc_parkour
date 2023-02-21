package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;

public class ParkourMakerArena {
	
	private Location start;
	private Location end;
	private Location spawn;
	private List<Location> checkpoints;
	private ParkourMode mode;
	
	public ParkourMakerArena(ParkourMode mode) {
		this.checkpoints = new ArrayList<>();
		this.mode = mode;
	}
	
	public ParkourMode getMode() {
		return this.mode;
	}
	
	public Location getSpawn() {
		return this.spawn;
	}
	
	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	public Location getStart() {
		return start;
	}

	public void setStart(Location start) {
		this.start = start;
	}

	public Location getEnd() {
		return end;
	}

	public void setEnd(Location end) {
		this.end = end;
	}

	public List<Location> getCheckpoints() {
		return checkpoints;
	}

}