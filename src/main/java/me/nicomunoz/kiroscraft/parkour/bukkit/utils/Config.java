package me.nicomunoz.kiroscraft.parkour.bukkit.utils;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;

public class Config {
	
	public static long asLong(String key) {
		return Long.parseLong(value(key));
	}
	
	public static short asShort(String key) {
		return Short.parseShort(value(key));
	}
	
	public static String asString(String key) {
		return value(key);
	}
	
	public static int asInt(String key) {
		return Integer.parseInt(value(key));
	}
		
	public static boolean asBoolean(String key) {
		return Boolean.getBoolean(key);
	}
	
	private static String value(String key) {
		return ParkourCore.getInstance().getProperties().getValue(key);
	}

}
