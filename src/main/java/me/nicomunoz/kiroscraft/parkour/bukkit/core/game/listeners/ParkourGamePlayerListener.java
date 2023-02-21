package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerCheckpointEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerFinishEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerJoinEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerStartEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player.ParkourPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourState;

public class ParkourGamePlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		ParkourGame game = ParkourCore.getInstance().getGameManager().getGame();
		if(game != null && game.getState() == ParkourState.PLAYING) {
			ParkourGamePlayerJoinEvent joinEvent = new ParkourGamePlayerJoinEvent(event.getPlayer(), game);
			Bukkit.getPluginManager().callEvent(joinEvent);
		}
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if(event.getAction() == Action.PHYSICAL) {
			ParkourPlayer parkourPlayer = ParkourCore.getInstance().getGameManager().getPlayerManager().getPlayer(event.getPlayer());
			if(parkourPlayer == null)
				return ;
			ParkourGame game = parkourPlayer.getGame();
			if(game == null)
				return ;
			if(game.getState() != ParkourState.PLAYING)
				return ;
			Location location = event.getClickedBlock().getLocation();
			
			//Start
			if(game.getArena().getStart().equals(location)) {
				ParkourGamePlayerStartEvent startEvent = new ParkourGamePlayerStartEvent(event.getPlayer(), game);
				Bukkit.getPluginManager().callEvent(startEvent);
				return ;
			}
			
			//End
			if(game.getArena().getEnd().equals(location)) {
				ParkourGamePlayerFinishEvent startEvent = new ParkourGamePlayerFinishEvent(event.getPlayer(), game);
				Bukkit.getPluginManager().callEvent(startEvent);
				return ;
			}
			
			ParkourCheckpoint checkPoint = game.getArena().getCheckpoints().get(location);
			if(checkPoint != null) {
				if(parkourPlayer.getCheckpoint() == null || parkourPlayer.getCheckpoint().getId() < checkPoint.getId()) {
					ParkourGamePlayerCheckpointEvent checkpointEvent = new ParkourGamePlayerCheckpointEvent(event.getPlayer(), game, checkPoint);
					Bukkit.getPluginManager().callEvent(checkpointEvent);
				}
			}
		}
	}
}
