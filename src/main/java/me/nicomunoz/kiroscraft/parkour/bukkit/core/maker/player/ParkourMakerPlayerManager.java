package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class ParkourMakerPlayerManager {
	
	private HashMap<UUID, ParkourMakerPlayer> players;
	
	public ParkourMakerPlayerManager() {
		this.players = new HashMap<>();
	}
	
	public boolean exists(UUID uuid) {
		return (players.containsKey(uuid));
	}
	
	public boolean exists(Player player) {
		return (players.containsKey(player.getUniqueId()));
	}
	
	public ParkourMakerPlayer newInstance(UUID uuid) {
		checkArgument(!exists(uuid), "Error creating ParkourMaker, instance already exists.");
		
		ParkourMakerPlayer parkourPlayer = new ParkourMakerPlayer(uuid);
		players.put(uuid, parkourPlayer);
		return (parkourPlayer);
	}
	
	public ParkourMakerPlayer newInstance(Player player) {
		return (newInstance(player.getUniqueId()));
	}
	
	public ParkourMakerPlayer getPlayer(UUID uuid) {
		return (players.get(uuid));
	}
	
	public ParkourMakerPlayer getPlayer(Player player) {
		return (getPlayer(player.getUniqueId()));
	}
	
	public ParkourMakerPlayer getForcedPlayer(UUID uuid) {
		if(exists(uuid)) 
			return getPlayer(uuid);
		return (newInstance(uuid));
	}
	
	public ParkourMakerPlayer getForcedPlayer(Player player) {
		return getForcedPlayer(player.getUniqueId());
	}
	
	public void deleteInstance(UUID uuid) {
		if(exists(uuid))
			players.remove(uuid);
	}
	
	public void deleteInstance(Player player) {
		deleteInstance(player.getUniqueId());
	}

}