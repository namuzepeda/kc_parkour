package me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous;

import org.bukkit.Bukkit;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.hologram.HologramManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.listeners.MiscelaneousListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.npc.NPCManager;

public class MiscelaneousManager {
	
	private NPCManager npcManager;
	private HologramManager holoManager;
	
	public MiscelaneousManager() {
		this.npcManager = new NPCManager();
		this.holoManager = new HologramManager();
		Bukkit.getPluginManager().registerEvents(new MiscelaneousListener(), ParkourCore.getInstance().getMain());
	}
	
	public NPCManager getNPCManager() {
		return this.npcManager;
	}

	public HologramManager getHologramManager() {
		return this.holoManager;
	}
	
}
