package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourGamePlayerFinishEvent extends ParkourGamePlayerDoneEvent {

	public ParkourGamePlayerFinishEvent(Player player) {
		super(player);
	}

}
