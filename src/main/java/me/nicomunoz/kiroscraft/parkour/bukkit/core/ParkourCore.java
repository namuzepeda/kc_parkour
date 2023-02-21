package me.nicomunoz.kiroscraft.parkour.bukkit.core;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;

import me.nicomunoz.kiroscraft.parkour.bukkit.Main;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArenaManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.configs.ParkourConfigManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGameManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.ParkourMakerManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.commands.ParkourMakerCommand;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.ExtendedCore;

public class ParkourCore {
	
	private static ParkourCore core;
	
	public static ParkourCore newInstance(Main main) throws IOException {
		checkArgument(core == null, "Cannot initialize ParkourCore twice");
		
		core = new ParkourCore(main);
		core.init();
		return core;
	}
	
	public static ParkourCore getInstance() {
		return core;
	}
	
	public static void deleteInstance() {
		core = null;
	}
	
	
	private Main main;
	private ParkourProperties properties;
	private ParkourGameManager gameManager;
	private ParkourMakerManager makerManager;
	private ParkourArenaManager arenaManager;
	private ParkourConfigManager configManager;
	
	private ParkourCore(Main main) {
		this.main = main;
	}
	
	private void init() throws IOException {
		this.configManager = new ParkourConfigManager();
		this.properties = new ParkourProperties();
		this.arenaManager = new ParkourArenaManager();
		this.gameManager = new ParkourGameManager();
		this.makerManager = new ParkourMakerManager();
		
		ExtendedCore.newInstance().enable(main);
		initializeCommands();
	}
	
	private void initializeCommands() {
		this.main.getCommand("pmaker").setExecutor(new ParkourMakerCommand());
	}
	
	public Main getMain() {
		return this.main;
	}
	
	public ParkourProperties getProperties() {
		return this.properties;
	}
	
	public ParkourGameManager getGameManager() {
		return this.gameManager;
	}
	
	public ParkourMakerManager getMakerManager() {
		return this.makerManager;
	}
	
	public ParkourArenaManager getArenaManager() {
		return this.arenaManager;
	}
	
	public ParkourConfigManager getConfigManager() {
		return this.configManager;
	}

}