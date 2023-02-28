package me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSetupCreateEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSetupEditEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSetupEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer.SetupMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.ItemBuilder;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickAction;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.holders.ExtendedInventory;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.utils.ExtendedItemBuilder;

public class GameModeSelectorInventory extends ExtendedInventory {

	public GameModeSelectorInventory() {
		super(
				ParkourCore.getInstance().getConfigManager().getInventories().getConfig().getString("modes.title"),
				ParkourCore.getInstance().getConfigManager().getInventories().getConfig().getInt("modes.size")
		);
		
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getInventories().getConfig();
		JavaPlugin plugin = ParkourCore.getInstance().getMain();
		addItem(
				ExtendedItemBuilder.build("modes.free_mode.item", config),
				new ClickAction() {
					
					@Override
					public void execute(Player player) {
						Bukkit.getScheduler().runTaskLater(plugin, () -> {
							player.openInventory(ParkourCore.getInstance().getGameManager().getModeInventory(ParkourMode.FREE).getInventory());
						}, 1L);
					}
					
				},
				config.getInt("modes.free_mode.slot")
		);
		
		addItem(
				ExtendedItemBuilder.build("modes.competitive_mode.item", config),
				new ClickAction() {
					
					@Override
					public void execute(Player player) {
						Bukkit.getScheduler().runTaskLater(plugin, () -> {
							player.openInventory(ParkourCore.getInstance().getGameManager().getModeInventory(ParkourMode.COMPETITIVE).getInventory());
						}, 1L);
					}
					
				},
				config.getInt("modes.competitive_mode.slot")
		);
		
		addItem(
				ExtendedItemBuilder.build("modes.dropper_mode.item", config),
				new ClickAction() {
					
					@Override
					public void execute(Player player) {
						Bukkit.getScheduler().runTaskLater(plugin, () -> {
							player.openInventory(ParkourCore.getInstance().getGameManager().getModeInventory(ParkourMode.DROPPER).getInventory());
						}, 1L);
					}
					
				},
				config.getInt("modes.dropper_mode.slot")
		);
	}

}
