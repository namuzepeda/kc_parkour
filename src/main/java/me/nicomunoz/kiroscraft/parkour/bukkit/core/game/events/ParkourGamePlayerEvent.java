package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player.ParkourPlayer;

public class ParkourGamePlayerEvent extends ParkourGameEvent {
	
	private ParkourPlayer parkourPlayer;
	
	public ParkourGamePlayerEvent(Player player) {
		this.parkourPlayer = ParkourCore.getInstance().getGameManager().getPlayerManager().getForcedPlayer(player); 
	}
	
	public Player getPlayer() {
		return this.parkourPlayer.getPlayer();
	}
	
	public ParkourPlayer getParkourPlayer() {
		return this.parkourPlayer;
	}

}
