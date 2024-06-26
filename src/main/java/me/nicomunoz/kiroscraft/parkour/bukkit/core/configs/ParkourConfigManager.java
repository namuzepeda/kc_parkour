package me.nicomunoz.kiroscraft.parkour.bukkit.core.configs;

import java.io.IOException;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.YamlConfig;

public class ParkourConfigManager {
	
	private YamlConfig itemsConfig;
	private YamlConfig arenaConfig;
	private YamlConfig inventoriesConfig;
	private YamlConfig miscelaneousConfig;
	
	public ParkourConfigManager() throws IOException {
		this.itemsConfig = new YamlConfig(ParkourCore.getInstance().getMain(), "items.yml");
		this.itemsConfig.getConfig().options().copyDefaults(true);
		this.arenaConfig = new YamlConfig(ParkourCore.getInstance().getMain(), "arenas.yml");
		this.arenaConfig.getConfig().options().copyDefaults(true);
		this.inventoriesConfig = new YamlConfig(ParkourCore.getInstance().getMain(), "inventories.yml");
		this.inventoriesConfig.getConfig().options().copyDefaults(true);
		this.miscelaneousConfig = new YamlConfig(ParkourCore.getInstance().getMain(), "miscelaneous.yml");
		this.miscelaneousConfig.getConfig().options().copyDefaults(true);
	}
	
	public YamlConfig getItems() {
		return this.itemsConfig;
	}
	
	public YamlConfig getArena() {
		return this.arenaConfig;
	}
	
	public YamlConfig getInventories() {
		return this.inventoriesConfig;
	}
	
	public YamlConfig getMiscelanousConfig() {
		return this.miscelaneousConfig;
	}

}
