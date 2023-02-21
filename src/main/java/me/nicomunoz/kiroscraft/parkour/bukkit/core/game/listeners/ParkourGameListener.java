package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners;

import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.checkpoint.ParkourCheckpoint;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.items.ParkourView;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerCheckpointEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerFinishEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerJoinEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerRollbackEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerStartEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerRollbackEvent.RollbackMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemView;

public class ParkourGameListener implements Listener {
	
	@EventHandler
	public void onPlayerJoinEvent(ParkourGamePlayerJoinEvent event) {
		event.getGame().getPlayers().add(event.getParkourPlayer());
		event.getParkourPlayer().setGame(event.getGame());
		event.getPlayer().teleport(event.getGame().getArena().getSpawn());
		if(Config.asBoolean(ParkourProperties.BROADCAST_ON_JOIN)) {
			Message.broadcast(ParkourProperties.BROADCAST_MESSAGE, null, 
					"%player", event.getPlayer().getDisplayName());
		}
		if(Config.asBoolean(ParkourProperties.MESSAGE_ON_JOIN)) {
			Message.key(ParkourProperties.MESSAGE_ON_JOIN_MSG, event.getPlayer(), 
					"%player", event.getPlayer().getDisplayName());
		}
		ItemView.setView(event.getPlayer(), 
				event.getGame().getMode() == ParkourMode.FREE ? ParkourView.MODE_FREE : ParkourView.MODE_COMPETITIVE);
	}
	
	@EventHandler
	public void onPlayerStartEvent(ParkourGamePlayerStartEvent event) {
		event.getParkourPlayer().setStart(new Date());
		Message.key(ParkourProperties.EVENT_START_MSG, event.getPlayer());
		ItemView.setView(event.getPlayer(), 
				event.getGame().getMode() == ParkourMode.FREE ? ParkourView.MODE_FREE : ParkourView.MODE_COMPETITIVE_RESTART);
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
		event.getParkourPlayer().setStart(new Date());
		event.getPlayer().teleport(game.getArena().getSpawn());
		ItemView.setView(event.getPlayer(), 
				event.getGame().getMode() == ParkourMode.FREE ? ParkourView.MODE_FREE : ParkourView.MODE_COMPETITIVE_RESTART);
	}
	
	@EventHandler
	public void onPlayerCheckpointEvent(ParkourGamePlayerCheckpointEvent event) {
		event.getParkourPlayer().setCheckpoint(event.getCheckpoint());
		Message.key(ParkourProperties.EVENT_CHECKPOINT_MSG, event.getPlayer(),
				"%checkpoint", event.getCheckpoint().getId() + "");
		if(event.isFirstCheckpoint()) {
			ItemView.setView(event.getPlayer(), 
					event.getGame().getMode() == ParkourMode.FREE ? 
							ParkourView.MODE_FREE_CHECKPOINT : ParkourView.MODE_COMPETITIVE_CHECKPOINT);
		}
	}
	
	@EventHandler
	public void onPlayerFinishEvent(ParkourGamePlayerFinishEvent event) {
		Date finish = new Date();
		Message.key(ParkourProperties.EVENT_FINISH_MSG, event.getPlayer(), 
				"%time", "PRONTO");
		event.getPlayer().teleport(event.getGame().getArena().getSpawn());
		event.getParkourPlayer().setCheckpoint(null);
		event.getParkourPlayer().setStart(null);
		ItemView.restore(event.getPlayer());
	}

}
