package me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories;

import java.util.Iterator;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerJoinEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.ItemBuilder;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickAction;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.holders.ExtendedInventory;
import me.nicomunoz.kiroscraft.parkour.shared.utils.ChatParser;
import me.nicomunoz.kiroscraft.parkour.shared.utils.StringUtils;

public class GamesInventory extends ExtendedInventory {

	public GamesInventory() {
		super(
				ChatParser.toColor(ParkourCore.getInstance().getConfigManager().getInventories().getConfig().getString("games.title")), 
				ParkourCore.getInstance().getConfigManager().getInventories().getConfig().getInt("games.size")
		);
	}
	
	public synchronized void update() {
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getInventories().getConfig();
		ItemBuilder builder = new ItemBuilder(Material.valueOf(config.getString("games.items.arena.type")));
		int i = 0;
		Iterator<ParkourGame> iterator = ParkourCore.getInstance().getGameManager().games().iterator();
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
							ParkourGamePlayerJoinEvent joinEvent = new ParkourGamePlayerJoinEvent(player, game);
							Bukkit.getPluginManager().callEvent(joinEvent);
						}
				
					},
			i++);
		}
	}

}
