package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourGamePlayerRollbackEvent extends ParkourGamePlayerEvent {
	
	public enum RollbackMode {
		RESTART, CHECKPOINT;
	}
	
	private ParkourGame game;
	private RollbackMode mode;

	public ParkourGamePlayerRollbackEvent(Player player, ParkourGame game, RollbackMode mode) {
		super(player);
		this.game = game;
		this.mode = mode;
	}
	
	public ParkourGame getGame() {
		return this.game;
	}
	
	public RollbackMode getMode() {
		return this.mode;
	}

}
