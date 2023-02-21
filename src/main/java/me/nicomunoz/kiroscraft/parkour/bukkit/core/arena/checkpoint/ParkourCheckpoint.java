package me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint;

import org.bukkit.Location;

public class ParkourCheckpoint {
	
	private int id;
	private Location location;
	
	public ParkourCheckpoint(int id, Location location) {
		this.id = id;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public Location getLocation() {
		return this.location;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj != null 
				&& obj instanceof ParkourCheckpoint 
				&& ((ParkourCheckpoint) obj).location.equals(this.location);
	}

}
