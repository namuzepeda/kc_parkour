package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.listeners;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.items.ParkourView;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.ParkourMakerArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerCancelEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerCheckpointAddEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerCheckpointRemoveEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerEditEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerEndEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerFinishEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSaveEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSpawnEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerStartEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemView;

public class ParkourMakerListener implements Listener {
	
	@EventHandler
	public void onMakerEditEvent(ParkourMakerEditEvent event) {
		ParkourMakerPlayer makerPlayer = event.getMakerPlayer();
		event.getPlayer().closeInventory();
		makerPlayer.setArena(new ParkourMakerArena(event.getMode()));
		ItemView.setView(event.getPlayer(), ParkourView.MAKER);
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_START, event.getPlayer());
	}
	
	@EventHandler
	public void onMakerStartEvent(ParkourMakerStartEvent event) {
		ParkourMakerArena arena = event.getMakerPlayer().getArena();
		if(arena.getCheckpoints().contains(event.getBlock().getLocation())) {
			Message.key(ParkourProperties.MAKER_EVENT_EDIT_CANNOT_IF_CHECKPOINT, event.getPlayer());
			return ;
		}
		arena.setStart(event.getBlock().getLocation());
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_SET_START, event.getPlayer());
	}
	
	@EventHandler
	public void onMakerSpawnSetEvent(ParkourMakerSpawnEvent event) {
		ParkourMakerArena arena = event.getMakerPlayer().getArena();
		arena.setSpawn(event.getLocation());
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_SET_SPAWN, event.getPlayer());
	}
	
	@EventHandler
	public void onMakerStartEvent(ParkourMakerEndEvent event) {
		ParkourMakerArena arena = event.getMakerPlayer().getArena();
		if(arena.getCheckpoints().contains(event.getBlock().getLocation())) {
			Message.key(ParkourProperties.MAKER_EVENT_EDIT_CANNOT_IF_CHECKPOINT, event.getPlayer());
			return ;
		}
		arena.setEnd(event.getBlock().getLocation());
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_SET_END, event.getPlayer());
	}
	
	@EventHandler
	public void onMakerStartEvent(ParkourMakerCheckpointAddEvent event) {
		ParkourMakerArena arena = event.getMakerPlayer().getArena();
		if( (arena.getStart() != null && arena.getStart().equals(event.getBlock().getLocation()) ) 
				|| (arena.getEnd() != null && arena.getEnd().equals(event.getBlock().getLocation()) )) {
			Message.key(ParkourProperties.MAKER_EVENT_EDIT_CANNOT_IF_START_END, event.getPlayer());
			return ;
		}
		if(arena.getCheckpoints().contains(event.getBlock().getLocation())) {
			Message.key(ParkourProperties.MAKER_EVENT_EDIT_CONTAINS_CHECKPOINT, event.getPlayer());
			return ;
		}
		arena.getCheckpoints().add(event.getBlock().getLocation());
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_ADD_CHECKPOINT, event.getPlayer());
	}
	
	@EventHandler
	public void onMakerStartEvent(ParkourMakerCheckpointRemoveEvent event) {
		ParkourMakerArena arena = event.getMakerPlayer().getArena();
		if(!arena.getCheckpoints().contains(event.getBlock().getLocation())) {
			Message.key(ParkourProperties.MAKER_EVENT_EDIT_NOT_CONTAINS_CHECKPOINT, event.getPlayer());
			return ;
		}
		arena.getCheckpoints().remove(event.getBlock().getLocation());
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_REMOVE_CHECKPOINT, event.getPlayer());
	}
	
	@EventHandler
	public void onMakerSaveEvent(ParkourMakerSaveEvent event) {
		ParkourMakerArena arena = event.getMakerPlayer().getArena();
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getArena().getConfig();
		config.set("start", arena.getStart());
		config.set("end", arena.getEnd());
		config.set("spawn", arena.getSpawn());
		for(int i = 0; arena.getCheckpoints().size() > i; i++) {
			config.set("checkpoints." + i, arena.getCheckpoints().get(i));
		}
		ParkourCore.getInstance().getConfigManager().getArena().saveConfig();
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_SAVE, event.getPlayer());
		ParkourCore.getInstance().getProperties().setValue(ParkourProperties.PARKOUR_MODE, event.getMakerPlayer().getArena().getMode().name());
		try {
			ParkourCore.getInstance().getProperties().save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onMakerCancelEvent(ParkourMakerCancelEvent event) {
		ItemView.restore(event.getPlayer());
		ParkourCore.getInstance().getMakerManager().getPlayerManager().deleteInstance(event.getPlayer());
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_CANCEL, event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onMakerFinishEditingEvent(ParkourMakerFinishEvent event) {
		ParkourCore.getInstance().getMakerManager().getPlayerManager().deleteInstance(event.getPlayer());
		ItemView.restore(event.getPlayer());
	}

}
