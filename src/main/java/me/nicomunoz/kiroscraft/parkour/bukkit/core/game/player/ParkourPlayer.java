package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player;

import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourPlayer {
	
	private UUID playerUUID;
	private Date start;
	private ParkourGame game;
	private ParkourCheckpoint checkpoint;
	
	public ParkourPlayer(UUID playerUUID) {
		super();
		this.playerUUID = playerUUID;
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(this.playerUUID);
	}
	
	public void setStart(Date date) {
		this.start = date;
	}
	
	public Date getStart() {
		return this.start;
	}
	
	public ParkourGame getGame() {
		return this.game;
	}
	
	public void setGame(ParkourGame game) {
		this.game = game;
	}
	
	public void setCheckpoint(ParkourCheckpoint checkpoint) {
		this.checkpoint = checkpoint;
	}
	
	public ParkourCheckpoint getCheckpoint() {
		return this.checkpoint;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj != null 
				&& obj instanceof ParkourPlayer 
				&& ((ParkourPlayer) obj).playerUUID == this.playerUUID);
	}
	
}
