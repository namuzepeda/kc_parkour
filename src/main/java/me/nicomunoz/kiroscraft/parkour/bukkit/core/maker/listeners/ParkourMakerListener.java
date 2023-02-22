package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.listeners;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.items.ParkourView;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.ParkourMakerArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerCancelEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerCheckpointAddEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerCheckpointRemoveEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerEndEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerDoneEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSaveEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSetupCreateEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSetupEditEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSetupEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSpawnEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerStartEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemView;

public class ParkourMakerListener implements Listener {
	
	@EventHandler
	public void onMakerSetupEvent(ParkourMakerSetupEvent event) {
		Player player = event.getPlayer();
		player.closeInventory();
		ItemView.setView(event.getPlayer(), ParkourView.MAKER);
		event.getMakerArena().setMode(event.getMode());
	}
	
	@EventHandler
	public void onMakerCreateEvent(ParkourMakerSetupCreateEvent event) {
		Message.key(ParkourProperties.MAKER_EVENT_CREATE, event.getPlayer(), 
				"%arena", event.getMakerArena().getName(),
				"%mode", event.getMode().name()
		);
	}
	
	@EventHandler
	public void onMakerEditEvent(ParkourMakerSetupEditEvent event) {
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_START, event.getPlayer(),
				"%arena", event.getMakerArena().getName(),
				"%mode", event.getMode().name()
		);
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
		String name = arena.getName().toLowerCase();
		config.set(name +  ".start", arena.getStart());
		config.set(name + ".end", arena.getEnd());
		config.set(name + ".spawn", arena.getSpawn());
		config.set(name + ".mode", arena.getMode().name());
		for(int i = 0; arena.getCheckpoints().size() > i; i++) {
			config.set(name + ".checkpoints", arena.getCheckpoints());
		}
		ParkourCore.getInstance().getConfigManager().getArena().saveConfig();
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_DONE, event.getPlayer());
	}
	
	@EventHandler
	public void onMakerCancelEvent(ParkourMakerCancelEvent event) {
		ParkourMakerPlayer makerPlayer = event.getMakerPlayer();
		makerPlayer.setArena(null);
		makerPlayer.setSetupMode(null);
		Message.key(ParkourProperties.MAKER_EVENT_EDIT_CANCEL, event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onMakerFinishEditingEvent(ParkourMakerDoneEvent event) {
		ParkourCore.getInstance().getMakerManager().getPlayerManager().deleteInstance(event.getPlayer());
		ItemView.restore(event.getPlayer());
	}

}