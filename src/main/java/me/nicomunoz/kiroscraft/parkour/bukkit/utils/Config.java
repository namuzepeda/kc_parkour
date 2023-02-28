package me.nicomunoz.kiroscraft.parkour.bukkit.utils;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.shared.utils.StringUtils;

public class Config {
	
	public static long asLong(String key) {
		return Long.parseLong(value(key));
	}
	
	public static short asShort(String key) {
		return Short.parseShort(value(key));
	}
	
	public static String asString(String key, String... replaces) {
		return StringUtils.replace(value(key), replaces);
	}
	
	public static String asString(String key) {
		return value(key);
	}
	
	public static int asInt(String key) {
		return Integer.parseInt(value(key));
	}
		
	public static boolean asBoolean(String key) {
		String value = value(key);
		return Boolean.valueOf(value);
	}
	
	private static String value(String key) {
		return ParkourCore.getInstance().getProperties().getValue(key);
	}

}
