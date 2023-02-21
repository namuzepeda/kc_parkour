package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended;

import static com.google.common.base.Preconditions.checkArgument;

import org.bukkit.plugin.java.JavaPlugin;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.listeners.ExtendedInventoryListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.listeners.ExtendedItemListener;

public class ExtendedCore {
	
private static ExtendedCore core;
	
	public static ExtendedCore newInstance() {
		checkArgument(core == null, "Cannot instance ExtendedCore twice");
		
		core = new ExtendedCore();
		return (core);
	}
	
	public static ExtendedCore getInstance() {
		return core;
	}
	
	public static void deleteInstance() {
		core = null;
	}
	
	
	private ExtendedItemListener itemListener;
	private ExtendedInventoryListener inventoryListener;
	
	public ExtendedCore() {
		this.itemListener = new ExtendedItemListener();
		this.inventoryListener = new ExtendedInventoryListener();
	}
	
	public void enable(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this.itemListener, plugin);
		plugin.getServer().getPluginManager().registerEvents(this.inventoryListener, plugin);
	}

}
