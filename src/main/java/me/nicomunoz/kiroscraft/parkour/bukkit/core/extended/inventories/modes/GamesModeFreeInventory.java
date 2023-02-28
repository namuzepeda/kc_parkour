package me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories.modes;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.google.gson.JsonSyntaxException;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerJoinEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourGameUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.ItemBuilder;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickAction;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.holders.ExtendedInventory;
import me.nicomunoz.kiroscraft.parkour.shared.utils.ChatParser;
import me.nicomunoz.kiroscraft.parkour.shared.utils.StringUtils;

public class GamesModeFreeInventory extends ExtendedInventory implements GamesModeInventory {
	
	@Override
	public ParkourMode getMode() {
		return ParkourMode.FREE;
	}

	public GamesModeFreeInventory() {
		super(
				ChatParser.toColor(ParkourCore.getInstance().getConfigManager().getInventories().getConfig().getString("games.title")), 
				ParkourCore.getInstance().getConfigManager().getInventories().getConfig().getInt("games.size")
		);
	}
	
	public synchronized void update() {
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getInventories().getConfig();
		ItemBuilder builder = new ItemBuilder(Material.valueOf(config.getString("games.items.arena.type")));
		int i = 0;
		Iterator<ParkourGame> iterator = ParkourCore.getInstance().getGameManager().games().stream().filter(obj -> obj.getArena().getMode() == getMode()).collect(Collectors.toList()).iterator();
		while(iterator.hasNext()) {
			ParkourGame game = iterator.next();
			
			String[] replaces = new String[] {
					"%arena", game.getArena().getName(),
					"%players", game.getPlayers().size() + ""
				};
			
			builder
				.name(StringUtils.replace(config.getString("games.items.arena.name"), replaces))
				.lores(config.getStringList("games.items.arena.lore")
						.stream().map(obj -> StringUtils.replace(obj, replaces)).collect(Collectors.toList()));
			
			addItem(builder.make(), 
					new ClickAction() {
				
						@Override
						public void execute(Player player) {
							player.closeInventory();
							if(game.getArena().getMode() == ParkourMode.FREE) {
								Bukkit.getScheduler().runTaskAsynchronously(ParkourCore.getInstance().getMain(), () -> {
									try {
										Location location = ParkourGameUtils.getSpawnLocation(game.getArena(), player.getName());
										Bukkit.getScheduler().runTask(ParkourCore.getInstance().getMain(), () -> {
											ParkourGamePlayerJoinEvent joinEvent = new ParkourGamePlayerJoinEvent(player, game, location);
											Bukkit.getPluginManager().callEvent(joinEvent);
										});
									} catch (JsonSyntaxException | SQLException e) {
										e.printStackTrace();
									}
								});
							} else {
								ParkourGamePlayerJoinEvent joinEvent = new ParkourGamePlayerJoinEvent(player, game, game.getArena().getSpawn());
								Bukkit.getPluginManager().callEvent(joinEvent);
							}
						}
				
					},
			i++);
		}
	}

}
