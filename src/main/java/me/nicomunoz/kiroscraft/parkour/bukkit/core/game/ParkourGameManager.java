package me.nicomunoz.kiroscraft.parkour.bukkit.core.game;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.utils.ParkourArenaUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories.GameModeSelectorInventory;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories.modes.GamesModeCompetitiveInventory;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories.modes.GamesModeDropperInventory;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories.modes.GamesModeFreeInventory;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners.ParkourGameListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners.ParkourGamePlayerListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.listeners.ParkourGameServerListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player.ParkourPlayerManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.timer.ParkourTimer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourGameUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourState;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;

public class ParkourGameManager {
	
	private Listener[] listeners = new Listener[] {
																					new ParkourGameListener(), 
																					new ParkourGamePlayerListener(), 
																					new ParkourGameServerListener() 
																				};
	
	private ParkourPlayerManager playerManager;
	private HashMap<String, ParkourGame> games;
	private ParkourTimer competitiveTimer;
	private GamesModeFreeInventory[] gamesModeInventory;
	private GameModeSelectorInventory modeSelectorInventory;
	private Location leaveLocation;
	
	public ParkourGameManager() {
		this.playerManager = new ParkourPlayerManager();
		this.games = new HashMap<>();
		loadGames();
		
		for(Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, ParkourCore.getInstance().getMain());
		}
		
		this.modeSelectorInventory = new GameModeSelectorInventory();
		
		this.gamesModeInventory = new GamesModeFreeInventory[] {
			new GamesModeFreeInventory(),
			new GamesModeCompetitiveInventory(),
			new GamesModeDropperInventory()
		};
		this.leaveLocation = ParkourCore.getInstance().getConfigManager().getArena().getConfig().getLocation("leave");
	}
	
	public Location getLeaveLocation() {
		return this.leaveLocation;
	}
	
	private void loadGames() {
		for(ParkourArena arena : ParkourCore.getInstance().getArenaManager().arenas()) {
			if(ParkourArenaUtils.isFinished(arena)) {
				ParkourGame game = new ParkourGame(arena);
				if(ParkourGameUtils.canPlay(game))
					game.setState(ParkourState.PLAYING);
				this.games.put(arena.getName().toLowerCase(), game);
				if(game.getArena().getMode() == ParkourMode.COMPETITIVE) {
					if(competitiveTimer == null) {
						competitiveTimer = new ParkourTimer();
						competitiveTimer.runTaskTimerAsynchronously(ParkourCore.getInstance().getMain(), 0, Config.asLong(ParkourProperties.ACTIONBAR_TIMER_UPDATE));
					}
				}
			}
		}
	}
	
	public ParkourPlayerManager getPlayerManager() {
		return this.playerManager;
	}
	
	public ParkourGame getGame(String arenaName) {
		return this.games.get(arenaName.toLowerCase());
	}
	
	public boolean exists(String arenaName) {
		return this.games.containsKey(arenaName.toLowerCase());
	}
	
	public Collection<ParkourGame> games() {
		return this.games.values();
	}
	
	public GameModeSelectorInventory getModeSelectorInventory() {
		return this.modeSelectorInventory;
	}
	
	public GamesModeFreeInventory getModeInventory(ParkourMode mode) {
		return this.gamesModeInventory[mode.ordinal()];
	}

}
