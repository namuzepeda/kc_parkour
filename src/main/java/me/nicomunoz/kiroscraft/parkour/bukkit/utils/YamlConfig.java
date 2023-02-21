package me.nicomunoz.kiroscraft.parkour.bukkit.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.Files;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;

public class YamlConfig{
	
	private File configFile;
	private FileConfiguration config;
	
	public YamlConfig(JavaPlugin plugin, String fileName) throws IOException{
		this.configFile = new File(plugin.getDataFolder(), fileName);
		if(!plugin.getDataFolder().exists())
			plugin.getDataFolder().mkdir();
		if(!configFile.exists()) {
			configFile.createNewFile();
			InputStream initialStream = plugin.getResource(fileName);
			if(initialStream != null) {
				byte[] buffer = new byte[initialStream.available()];
			    initialStream.read(buffer);
			    Files.write(buffer, configFile);
			}
		}
		this.config = YamlConfiguration.loadConfiguration(configFile);
	}
	 
	public void reloadConfig() {
		this.config = YamlConfiguration.loadConfiguration(configFile);
	}
	 
	public FileConfiguration getConfig() {
		return config;
	}
	 
	public void saveConfig() {
		if (config == null || configFile == null) {
		return;
	}
		try {
			getConfig().save(configFile);
		} catch (IOException ex) {
			ParkourCore.getInstance().getMain().getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
		}
	}
 
}
