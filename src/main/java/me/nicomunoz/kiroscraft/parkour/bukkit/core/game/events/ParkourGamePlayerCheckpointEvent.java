package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourGamePlayerCheckpointEvent extends ParkourGamePlayerEvent {
	
	private ParkourGame game;
	private ParkourCheckpoint checkpoint;
	private boolean firstCheckpoint;

	public ParkourGamePlayerCheckpointEvent(Player player, ParkourGame game, ParkourCheckpoint checkpoint) {
		super(player);
		this.game = game;
		this.checkpoint = checkpoint;
		this.firstCheckpoint = getParkourPlayer().getCheckpoint() == null;
	}
	
	public ParkourGame getGame() {
		return this.game;
	}
	
	public ParkourCheckpoint getCheckpoint() {
		return this.checkpoint;
	}
	
	public boolean isFirstCheckpoint() {
		return this.firstCheckpoint;
	}

}
