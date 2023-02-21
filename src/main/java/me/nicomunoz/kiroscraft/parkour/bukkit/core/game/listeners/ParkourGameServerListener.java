package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourGameServerListener implements Listener {
	
	@EventHandler
	public void onPingEvent(ServerListPingEvent event) {
		ParkourGame game = ParkourCore.getInstance().getGameManager().getGame();
		if(game != null) {
			event.setMotd(game.getMode().name() + " " + game.getState().name());
		}
	}

}
