package me.nicomunoz.kiroscraft.parkour.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.nicomunoz.kiroscraft.parkour.shared.utils.StringUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class Message {
	
	public static void send(String msg, CommandSender sender, String... replaces) {
		checkNotNull(msg, "Message cannot be null");
		if(replaces != null)
			msg = StringUtils.replace(msg, replaces);
		sender.sendMessage(msg);
	}
	
	public static void key(String key, CommandSender sender,  String... replaces) {
		send(Config.asString(key), sender, replaces);
	}
	
	public static void broadcast(String key, String permission, String... replaces) {
		String msg = Config.asString(key);
		
		checkNotNull(msg, "Message cannot be null");
		if(replaces != null)
			msg = StringUtils.replace(msg, replaces);
		if(permission != null)
			Bukkit.broadcast(msg, permission);
		else
			Bukkit.broadcastMessage(msg);
	}

}
