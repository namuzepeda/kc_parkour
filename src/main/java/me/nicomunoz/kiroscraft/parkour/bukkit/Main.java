package me.nicomunoz.kiroscraft.parkour.bukkit;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		try {
			ParkourCore.newInstance(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {
		ParkourCore.deleteInstance();
	}

}
