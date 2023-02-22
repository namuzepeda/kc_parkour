package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourGamePlayerLeaveEvent extends ParkourGamePlayerDoneEvent {

	public ParkourGamePlayerLeaveEvent(Player player) {
		super(player);
	}

}
