package me.nicomunoz.kiroscraft.parkour.bukkit.core.game;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners.ParkourGameListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners.ParkourGamePlayerListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners.ParkourGameServerListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player.ParkourPlayerManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourGameUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourState;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;

public class ParkourGameManager {
	
	private ParkourPlayerManager playerManager;
	private ParkourGame game;
	
	private Listener[] listeners = new Listener[] { new ParkourGameListener(), 
																					new ParkourGamePlayerListener(), 
																					new ParkourGameServerListener() };
	
	public ParkourGameManager() {
		this.playerManager = new ParkourPlayerManager();
		loadArena();
		
		for(Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, ParkourCore.getInstance().getMain());
		}
	}
	
	public void loadArena() {
		this.game = new ParkourGame(
				ParkourCore.getInstance().getArenaManager().getArena(), 
				ParkourMode.valueOf(Config.asString(ParkourProperties.PARKOUR_MODE))
		);
		game.setState(ParkourGameUtils.canPlay(game) ? ParkourState.PLAYING : ParkourState.BUILDING);
	}
	
	public ParkourPlayerManager getPlayerManager() {
		return this.playerManager;
	}
	
	public ParkourGame getGame() {
		return this.game;
	}

}
