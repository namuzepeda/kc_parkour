package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourGamePlayerDoneEvent extends ParkourGamePlayerEvent {

	public ParkourGamePlayerDoneEvent(Player player) {
		super(player);
	}
	
	public ParkourGame getGame() {
		return super.getParkourPlayer().getGame();
	}

}
