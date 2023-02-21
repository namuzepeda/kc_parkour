package me.nicomunoz.kiroscraft.parkour.bukkit.utils;

import org.bukkit.command.CommandSender;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;

public class Permission {
	
	public static boolean has(String permission, CommandSender sender, boolean sendError) {
		if(sender.hasPermission(permission)) return true;
		if(sendError) sendError(sender);
		return false;
	}
	
	public static void sendError(CommandSender sender) {
		Message.key(ParkourProperties.NOT_PERMISSION, sender);
	}

}
