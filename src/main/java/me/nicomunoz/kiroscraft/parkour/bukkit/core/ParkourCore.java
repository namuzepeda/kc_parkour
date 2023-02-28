package me.nicomunoz.kiroscraft.parkour.bukkit.core;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.sql.SQLException;

import me.nicomunoz.kiroscraft.parkour.bukkit.Main;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArenaManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.configs.ParkourConfigManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.EconomyManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGameManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.ParkourMakerManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.commands.ParkourMakerCommand;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.MiscelaneousManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.RankManager;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.ExtendedCore;
import me.nicomunoz.kiroscraft.parkour.connection.MasterConnection;

public class ParkourCore {
	
	private static ParkourCore core;
	
	public static ParkourCore newInstance(Main main) throws IOException, SQLException {
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
	private RankManager rankManager;
	private EconomyManager economyManager;
	private ParkourGameManager gameManager;
	private ParkourMakerManager makerManager;
	private ParkourArenaManager arenaManager;
	private ParkourConfigManager configManager;
	private MiscelaneousManager miscelaneousManager;
	
	private ParkourCore(Main main) {
		this.main = main;
	}
	
	private void init() throws IOException, SQLException {
		this.properties = new ParkourProperties();
		initDB();
		this.configManager = new ParkourConfigManager();
		this.economyManager = new EconomyManager();
		this.rankManager = new RankManager();
		this.miscelaneousManager = new MiscelaneousManager();
		this.arenaManager = new ParkourArenaManager();
		this.gameManager = new ParkourGameManager();
		this.makerManager = new ParkourMakerManager();
		
		ExtendedCore.newInstance().enable(main);
		initializeCommands();
		
		for(ParkourMode mode : ParkourMode.values()) {
			this.gameManager.getModeInventory(mode).update();
		}
	}
	
	private synchronized void initDB() {
		String host =  properties.getValue("db.host") + ":" + properties.getValue("db.port") + "/" + properties.getValue("db.database");
		MasterConnection.setHost(new String[] {host, properties.getValue("db.user"), properties.getValue("db.password")});
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
	
	public EconomyManager getEconomyManager() {
		return this.economyManager;
	}
	
	public RankManager getRankManager() {
		return this.rankManager;
	}
	
	public MiscelaneousManager getMiscelaneousManager() {
		return this.miscelaneousManager;
	}

}