package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourGamePlayerJoinEvent extends ParkourGamePlayerEvent {
	
	private ParkourGame game;
	private Location spawn;

	public ParkourGamePlayerJoinEvent(Player player, ParkourGame game, Location spawn) {
		super(player);
		this.game = game;
		this.spawn = spawn;
	}
	
	public ParkourGame getGame() {
		return this.game;
	}
	
	public Location getSpawn() {
		return this.spawn;
	}

}
