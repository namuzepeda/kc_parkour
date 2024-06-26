package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourGamePlayerStartEvent extends ParkourGamePlayerEvent {
	
	private ParkourGame game;

	public ParkourGamePlayerStartEvent(Player player, ParkourGame game) {
		super(player);
		this.game = game;
	}
	
	public ParkourGame getGame() {
		return this.game;
	}

}
