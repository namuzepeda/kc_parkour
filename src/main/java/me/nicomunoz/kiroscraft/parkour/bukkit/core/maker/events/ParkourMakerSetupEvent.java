package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.ParkourMakerArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;

public class ParkourMakerSetupEvent extends ParkourMakerPlayerEvent {
	
	private ParkourMode mode;
	
	public ParkourMakerSetupEvent(Player player, ParkourMode mode) {
		super(player);
		this.mode = mode;
	}
	
	public ParkourMakerArena getMakerArena() {
		return super.getMakerPlayer().getArena();
	}
	
	public ParkourMode getMode() {
		return this.mode;
	}
	
}
