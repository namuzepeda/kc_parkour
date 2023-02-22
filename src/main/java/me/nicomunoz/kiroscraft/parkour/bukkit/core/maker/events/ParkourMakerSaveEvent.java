package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events;

import org.bukkit.entity.Player;

public class ParkourMakerSaveEvent extends ParkourMakerDoneEvent {
	
	public ParkourMakerSaveEvent(Player player) {
		super(player); 
	}
	
}
