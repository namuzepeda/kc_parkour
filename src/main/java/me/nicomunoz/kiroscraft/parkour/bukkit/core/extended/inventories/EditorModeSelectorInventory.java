package me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

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

public class EditorModeSelectorInventory extends ExtendedInventory {

	public EditorModeSelectorInventory() {
		super("§0Opciones de arena", 27);
		
		addItem(
				new ItemBuilder(Material.SLIME_BLOCK).name("§bModo libre").make(),
				new ClickAction() {
					
					@Override
					public void execute(Player player) {
						ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getForcedPlayer(player);
						ParkourMakerSetupEvent setupEvent = null;
						if(makerPlayer.getSetupMode() == SetupMode.CREATE)
							setupEvent = new ParkourMakerSetupCreateEvent(player, ParkourMode.FREE);
						else
							setupEvent = new ParkourMakerSetupEditEvent(player, ParkourMode.FREE);
						Bukkit.getPluginManager().callEvent(setupEvent);
					}
					
				},
				11
		);
		
		addItem(
				new ItemBuilder(Material.HONEY_BLOCK).name("§6Modo competitivo").make(),
				new ClickAction() {
					
					@Override
					public void execute(Player player) {
						ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getForcedPlayer(player);
						ParkourMakerSetupEvent setupEvent = null;
						if(makerPlayer.getSetupMode() == SetupMode.CREATE)
							setupEvent = new ParkourMakerSetupCreateEvent(player, ParkourMode.COMPETITIVE);
						else
							setupEvent = new ParkourMakerSetupEditEvent(player, ParkourMode.COMPETITIVE);
						Bukkit.getPluginManager().callEvent(setupEvent);
					}
					
				},
				13
		);
		addItem(
				new ItemBuilder(Material.HONEYCOMB_BLOCK).name("§3Modo Dropper").make(),
				new ClickAction() {
					
					@Override
					public void execute(Player player) {
						ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getForcedPlayer(player);
						ParkourMakerSetupEvent setupEvent = null;
						if(makerPlayer.getSetupMode() == SetupMode.CREATE)
							setupEvent = new ParkourMakerSetupCreateEvent(player, ParkourMode.DROPPER);
						else
							setupEvent = new ParkourMakerSetupEditEvent(player, ParkourMode.DROPPER);
						Bukkit.getPluginManager().callEvent(setupEvent);
					}
					
				},
				15
		);
	}

}
