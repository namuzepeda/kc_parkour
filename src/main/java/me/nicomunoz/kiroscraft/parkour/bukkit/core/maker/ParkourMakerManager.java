package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories.EditorModeSelectorInventory;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.listeners.ParkourMakerListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.listeners.ParkourMakerPlayerListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayerManager;

public class ParkourMakerManager {
	
	private ParkourMakerPlayerManager playerManager;
	private EditorModeSelectorInventory modeSelectorInventory;
	
	private Listener[] listeners = { new ParkourMakerListener(), new ParkourMakerPlayerListener() };
	
	public ParkourMakerManager() {
		this.playerManager = new ParkourMakerPlayerManager();
		this.modeSelectorInventory = new EditorModeSelectorInventory();
		
		for(Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, ParkourCore.getInstance().getMain());
		}
	}
	
	public ParkourMakerPlayerManager getPlayerManager() {
		return this.playerManager;
	}
	
	public EditorModeSelectorInventory getModeSelectorInventory() {
		return this.modeSelectorInventory;
	}

}
