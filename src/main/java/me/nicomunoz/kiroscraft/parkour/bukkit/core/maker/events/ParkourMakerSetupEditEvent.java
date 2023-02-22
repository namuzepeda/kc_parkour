package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.ParkourMakerArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;

public class ParkourMakerSetupEditEvent extends ParkourMakerSetupEvent {
	
	public ParkourMakerSetupEditEvent(Player player, ParkourMode mode) {
		super(player, mode);
	}
	
}
