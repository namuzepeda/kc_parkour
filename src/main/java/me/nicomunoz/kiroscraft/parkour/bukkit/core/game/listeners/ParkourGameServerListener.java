package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourGameUtils;

public class ParkourGameServerListener implements Listener {
	
	@EventHandler
	public void onPingEvent(ServerListPingEvent event) {
		StringBuilder builder = new StringBuilder();
		for(ParkourGame game : ParkourCore.getInstance().getGameManager().games()) {
			if(ParkourGameUtils.canPlay(game)) {
				if(!builder.toString().isEmpty())
					builder.append(",");
				builder.append(game.getArena().getName());
			}
		}
		event.setMotd(builder.toString());
	}

}
