package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.ParkourMakerArena;

public class ParkourMakerPlayer {
	
	public enum SetupMode {
		
		CREATE, EDIT;
		
	}
	
	private UUID playerUUID;
	private ParkourMakerArena makerArena;
	private SetupMode setupMode;
	
	public ParkourMakerPlayer(UUID playerUUID) {
		this.playerUUID = playerUUID;
	}
	
	public UUID getPlayerUUID() {
		return this.playerUUID;
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(this.playerUUID);
	}

	public boolean isEditing() {
		return makerArena != null;
	}

	public void setArena(ParkourMakerArena arena) {
		this.makerArena = arena;
	}
	
	public ParkourMakerArena getArena() {
		return this.makerArena;
	}

	public SetupMode getSetupMode() {
		return setupMode;
	}

	public void setSetupMode(SetupMode setupMode) {
		this.setupMode = setupMode;
	}

}
