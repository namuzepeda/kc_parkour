package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;

public class ParkourMakerSetupCreateEvent extends ParkourMakerSetupEvent {
	
	public ParkourMakerSetupCreateEvent(Player player, ParkourMode mode) {
		super(player, mode);
	}
	
}
