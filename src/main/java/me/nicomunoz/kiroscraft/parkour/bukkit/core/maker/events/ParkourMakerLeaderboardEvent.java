package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ParkourMakerLeaderboardEvent extends ParkourMakerPlayerEvent {
	
	private Location location;
	
	public ParkourMakerLeaderboardEvent(Player player, Location location) {
		super(player);
		this.location = location;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
}
