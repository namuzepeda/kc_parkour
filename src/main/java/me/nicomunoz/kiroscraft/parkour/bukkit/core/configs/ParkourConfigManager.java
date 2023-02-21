package me.nicomunoz.kiroscraft.parkour.bukkit.core.configs;

import java.io.IOException;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.YamlConfig;

public class ParkourConfigManager {
	
	private YamlConfig itemsConfig;
	private YamlConfig arenaConfig;
	
	public ParkourConfigManager() throws IOException {
		this.itemsConfig = new YamlConfig(ParkourCore.getInstance().getMain(), "items.yml");
		this.itemsConfig.getConfig().options().copyDefaults(true);
		this.arenaConfig = new YamlConfig(ParkourCore.getInstance().getMain(), "arena.yml");
		this.arenaConfig.getConfig().options().copyDefaults(true);
	}
	
	public YamlConfig getItems() {
		return this.itemsConfig;
	}
	
	public YamlConfig getArena() {
		return this.arenaConfig;
	}

}
