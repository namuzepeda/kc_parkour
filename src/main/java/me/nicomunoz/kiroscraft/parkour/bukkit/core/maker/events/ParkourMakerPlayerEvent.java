package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;

public class ParkourMakerPlayerEvent extends ParkourMakerEvent {
	
	private ParkourMakerPlayer makerPlayer;
	
	public ParkourMakerPlayerEvent(Player player) {
		this.makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getForcedPlayer(player);
	}
	
	public Player getPlayer() {
		return this.makerPlayer.getPlayer();
	}
	
	public ParkourMakerPlayer getMakerPlayer() {
		return this.makerPlayer;
	}

}
