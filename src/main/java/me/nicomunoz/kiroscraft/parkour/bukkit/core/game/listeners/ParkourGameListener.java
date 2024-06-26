package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners;

import java.sql.SQLException;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.items.ParkourView;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerCheckpointEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerDoneEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerFinishEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerJoinEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerRollbackEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerRollbackEvent.RollbackMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerStartEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player.ParkourPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourGameUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemView;
import me.nicomunoz.kiroscraft.parkour.connection.Query;

public class ParkourGameListener implements Listener {
	
	@EventHandler
	public void onPlayerJoinEvent(ParkourGamePlayerJoinEvent event) {
		ParkourGame game = event.getGame();
		
		game.getPlayers().add(event.getParkourPlayer());
		event.getParkourPlayer().setGame(game);
		event.getPlayer().teleport(event.getSpawn());
		if(Config.asBoolean(ParkourProperties.BROADCAST_ON_JOIN)) {
			Message.broadcast(ParkourProperties.BROADCAST_MESSAGE, null, 
					"%player", event.getPlayer().getDisplayName()
			);
		}
		if(Config.asBoolean(ParkourProperties.MESSAGE_ON_JOIN)) {
			Message.key(ParkourProperties.MESSAGE_ON_JOIN_MSG, event.getPlayer(), 
					"%player", event.getPlayer().getDisplayName(),
					"%arena", game.getArena().getName()
			);
		}
		ItemView.setView(event.getPlayer(), 
				game.getArena().getMode() == ParkourMode.FREE ? ParkourView.MODE_FREE : ParkourView.MODE_COMPETITIVE);
		ParkourCore.getInstance().getGameManager().getModeInventory(game.getArena().getMode()).update();
	}
	
	@EventHandler
	public void onPlayerStartEvent(ParkourGamePlayerStartEvent event) {
		ParkourGame game = event.getGame();
		event.getParkourPlayer().setStart(new Date());
		Message.key(ParkourProperties.EVENT_START_MSG, event.getPlayer());
		ItemView.setView(event.getPlayer(),
				game.getArena().getMode() == ParkourMode.FREE ? ParkourView.MODE_FREE : ParkourView.MODE_COMPETITIVE_RESTART);
	}
	
	@EventHandler
	public void onPlayerRollbackEvent(ParkourGamePlayerRollbackEvent event) {
		ParkourGame game = event.getGame();
		if(event.getMode() == RollbackMode.CHECKPOINT) {
			ParkourCheckpoint checkpoint = event.getParkourPlayer().getCheckpoint();
			if(checkpoint != null) {
				Message.key(ParkourProperties.EVENT_CHECKPOINT_RESTART_MSG, event.getPlayer(),
						"%checkpoint", checkpoint.getId() + "");
				event.getPlayer().teleport(checkpoint.getLocation());
				return ;
			}
		}
		event.getParkourPlayer().setCheckpoint(null);
		event.getParkourPlayer().setStart(null);
		event.getPlayer().teleport(game.getArena().getSpawn());
		ItemView.setView(event.getPlayer(), 
				game.getArena().getMode() == ParkourMode.FREE ? ParkourView.MODE_FREE : ParkourView.MODE_COMPETITIVE_RESTART);
	}
	
	@EventHandler
	public void onPlayerCheckpointEvent(ParkourGamePlayerCheckpointEvent event) {
		ParkourGame game = event.getGame();
		event.getParkourPlayer().setCheckpoint(event.getCheckpoint());
		Message.key(ParkourProperties.EVENT_CHECKPOINT_MSG, event.getPlayer(),
				"%checkpoint", event.getCheckpoint().getId() + "");
		if(event.isFirstCheckpoint()) {
			ItemView.setView(event.getPlayer(), 
					game.getArena().getMode() == ParkourMode.FREE ? 
							ParkourView.MODE_FREE_CHECKPOINT : ParkourView.MODE_COMPETITIVE_CHECKPOINT);
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onParkourGameDoneEvet(ParkourGamePlayerDoneEvent event) {
		ParkourGame game = event.getGame();
		Player player = event.getPlayer();
		ParkourPlayer parkourPlayer = event.getParkourPlayer();
		event.getPlayer().teleport(event.getGame().getArena().getSpawn());
		game.getPlayers().remove(parkourPlayer);
		parkourPlayer.setCheckpoint(null);
		parkourPlayer.setStart(null);
		parkourPlayer.setGame(null);
		ItemView.setView(player, ParkourView.JOIN);
		player.teleport(game.getArena().getLeave() != null ? game.getArena().getLeave() : game.getArena().getSpawn());
		ParkourCore.getInstance().getGameManager().getModeInventory(game.getArena().getMode()).update();
	}
	
	@EventHandler
	public void onPlayerFinishEvent(ParkourGamePlayerFinishEvent event) {
		Date finish = new Date();
		ParkourGame game = event.getGame();
		long time = finish.getTime() - event.getParkourPlayer().getStart().getTime();
		String stringTime = ParkourGameUtils.getTime(finish.getTime() - event.getParkourPlayer().getStart().getTime());
		Message.key(ParkourProperties.EVENT_FINISH_MSG, event.getPlayer(), 
				"%time", stringTime);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				try {
					if(game.getArena().getMode() == ParkourMode.COMPETITIVE) {
						new Query(null, "INSERT INTO map_player (map, player, time) VALUES (?, ?, ?) "
								+ "ON DUPLICATE KEY UPDATE time = "
								+ "LEAST(COALESCE(time, 999999999999), ?)", game.getArena().getName(),
								event.getPlayer().getName(), time, time).executeUpdate();
						game.getArena().getLeaderboard().update();
					} else if(game.getArena().getMode() == ParkourMode.FREE) {
						ParkourGameUtils.insertOrUpdateLocation(null, game.getArena(), event.getPlayer().getName());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}.runTaskAsynchronously(ParkourCore.getInstance().getMain());
	}

}
