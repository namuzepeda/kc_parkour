package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import static com.google.common.base.Preconditions.checkArgument;;

public class ParkourPlayerManager {
	
	private HashMap<UUID, ParkourPlayer> players;
	
	public ParkourPlayerManager() {
		this.players = new HashMap<>();
	}
	
	public boolean exists(UUID uuid) {
		return (players.containsKey(uuid));
	}
	
	public boolean exists(Player player) {
		return (players.containsKey(player.getUniqueId()));
	}
	
	public ParkourPlayer newInstance(UUID uuid) {
		checkArgument(!exists(uuid), "Error creating ParkourPlayer, instance already exists.");
		
		ParkourPlayer parkourPlayer = new ParkourPlayer(uuid);
		players.put(uuid, parkourPlayer);
		return (parkourPlayer);
	}
	
	public ParkourPlayer newInstance(Player player) {
		return (newInstance(player.getUniqueId()));
	}
	
	public ParkourPlayer getPlayer(UUID uuid) {
		return (players.get(uuid));
	}
	
	public ParkourPlayer getPlayer(Player player) {
		return (getPlayer(player.getUniqueId()));
	}
	
	public ParkourPlayer getForcedPlayer(UUID uuid) {
		if(exists(uuid)) 
			return getPlayer(uuid);
		return (newInstance(uuid));
	}
	
	public ParkourPlayer getForcedPlayer(Player player) {
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
