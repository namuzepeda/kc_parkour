package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events;

import org.bukkit.entity.Player;

public class ParkourMakerCancelEvent extends ParkourMakerDoneEvent {
	
	public ParkourMakerCancelEvent(Player player) {
		super(player); 
	}
	
}
