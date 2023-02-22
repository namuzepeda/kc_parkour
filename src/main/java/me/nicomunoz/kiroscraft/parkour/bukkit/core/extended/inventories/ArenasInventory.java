package me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.ParkourMakerArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.ItemBuilder;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickAction;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.holders.ExtendedInventory;
import me.nicomunoz.kiroscraft.parkour.shared.utils.StringUtils;

public class ArenasInventory extends ExtendedInventory {

	public ArenasInventory() {
		super("§0Editor de arenas", 27);
		
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getItems().getConfig();
		ItemBuilder builder = new ItemBuilder(Material.valueOf(config.getString("inventory.arenas.type")));
		int i = 0;
		for(ParkourArena arena : ParkourCore.getInstance().getArenaManager().arenas()) {
			String[] replaces = new String[] {
				"%arena", arena.getName()
			};
			
			builder
				.name(StringUtils.replace(config.getString("inventory.arenas.name"), replaces))
				.lores(config.getStringList("inventory.arenas.lore")
						.stream().map(obj -> StringUtils.replace(obj, replaces)).collect(Collectors.toList()));
			
			addItem(
					builder.make(),
					new ClickAction() {
						
						@SuppressWarnings("unchecked")
						@Override
						public void execute(Player player) {
							player.closeInventory();
							ParkourMakerArena makerArena = new ParkourMakerArena(arena.getName());
							makerArena.setSpawn(arena.getSpawn());
							makerArena.setStart(makerArena.getStart());
							makerArena.setEnd(arena.getEnd());
							List<Location> checkpoints = (List<Location>) ParkourCore.getInstance().getConfigManager().getArena().getConfig().getList(arena.getName().toLowerCase() + ".checkpoints", new ArrayList<>());
							makerArena.getCheckpoints().addAll(checkpoints);
							ParkourCore.getInstance().getMakerManager().getPlayerManager().getForcedPlayer(player).setArena(makerArena);
							player.openInventory(inventory);
						}
						
					},
					i++
			);
		}
	}

}
