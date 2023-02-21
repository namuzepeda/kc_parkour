package me.nicomunoz.kiroscraft.parkour.bukkit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.Files;

public class PropertiesHandler {
	
	private Properties data;
	private File file;
	
	public PropertiesHandler(JavaPlugin plugin, String name) throws IOException {
		this.data = new Properties();
		file = new File(plugin.getDataFolder(), name + ".properties");
		if(!plugin.getDataFolder().exists())
			plugin.getDataFolder().mkdir();
		if(!file.exists()) {
			file.createNewFile();
			InputStream initialStream = plugin.getResource(name + ".properties");
		    byte[] buffer = new byte[initialStream.available()];
		    initialStream.read(buffer);

		    Files.write(buffer, file);
		    initialStream.close();
		}
		FileInputStream inputStream = new FileInputStream(file);
		data.load(inputStream);
		inputStream.close();
	}
	
	public String getValue(String key) {
		return data.getProperty(key);
	}
	
	public void setValue(String key, String value) {
		data.setProperty(key, value);
	}
	
	public void save() throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		data.store(out, null);
		out.close();
	}

}
