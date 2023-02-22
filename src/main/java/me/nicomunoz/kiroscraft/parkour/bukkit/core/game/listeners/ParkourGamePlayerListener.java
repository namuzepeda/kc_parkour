package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.items.ParkourView;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerCheckpointEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerFinishEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerJoinEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerStartEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player.ParkourPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourState;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemView;

public class ParkourGamePlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		ParkourPlayer parkourPlayer = ParkourCore.getInstance().getGameManager().getPlayerManager().getPlayer(event.getPlayer());
		if(parkourPlayer != null) {
			if(parkourPlayer.getGame() != null)
				parkourPlayer.getGame().getPlayers().remove(parkourPlayer);
			ParkourCore.getInstance().getGameManager().getPlayerManager().deleteInstance(event.getPlayer());	
		}
	}
	
	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		ParkourPlayer parkourPlayer = ParkourCore.getInstance().getGameManager().getPlayerManager().getPlayer(event.getPlayer());
		event.setCancelled(parkourPlayer.getGame() != null);
	}
	
	@EventHandler
	public void onPlayerPickupItemEvent(EntityPickupItemEvent event) {
		if(event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			ParkourPlayer parkourPlayer = ParkourCore.getInstance().getGameManager().getPlayerManager().getPlayer(player);
			event.setCancelled(parkourPlayer.getGame() != null);
		}
	}
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		ParkourPlayer parkourPlayer = ParkourCore.getInstance().getGameManager().getPlayerManager().getPlayer(event.getPlayer());
		event.setCancelled(parkourPlayer.getGame() != null);
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		ParkourPlayer parkourPlayer = ParkourCore.getInstance().getGameManager().getPlayerManager().getPlayer(event.getPlayer());
		event.setCancelled(parkourPlayer != null && parkourPlayer.getGame() != null);
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		ItemView.setView(event.getPlayer(), ParkourView.JOIN);
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
				if(parkourPlayer.getStart() == null || (new Date().getTime() - parkourPlayer.getStart().getTime()) / 1000 > 5) {
					ParkourGamePlayerStartEvent startEvent = new ParkourGamePlayerStartEvent(event.getPlayer(), game);
					Bukkit.getPluginManager().callEvent(startEvent);
				}
				return ;
			}
			
			//End
			if(game.getArena().getEnd().equals(location)) {
				ParkourGamePlayerFinishEvent startEvent = new ParkourGamePlayerFinishEvent(event.getPlayer());
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
