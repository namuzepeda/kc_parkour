package me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.items.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerEditEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.ItemBuilder;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickAction;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.holders.ExtendedInventory;

public class EditInventory extends ExtendedInventory {

	public EditInventory() {
		super("§0Opciones de arena", 27);
		
		addItem(
				new ItemBuilder(Material.SLIME_BLOCK).name("§bModo libre").make(),
				new ClickAction() {
					
					@Override
					public void execute(Player player) {
						ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getForcedPlayer(player);
						if(makerPlayer.isEditing()) {
							Message.key(ParkourProperties.MAKER_EVENT_EDIT_ALREADY_EDITING, player);
							return ;
						}
						ParkourMakerEditEvent editEvent = new ParkourMakerEditEvent(player, ParkourMode.FREE);
						Bukkit.getPluginManager().callEvent(editEvent);
					}
					
				},
				12
		);
		
		addItem(
				new ItemBuilder(Material.HONEY_BLOCK).name("§6Modo competitivo").make(),
				new ClickAction() {
					
					@Override
					public void execute(Player player) {
						ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getForcedPlayer(player);
						if(makerPlayer.isEditing()) {
							Message.key(ParkourProperties.MAKER_EVENT_EDIT_ALREADY_EDITING, player);
							return ;
						}
						ParkourMakerEditEvent editEvent = new ParkourMakerEditEvent(player, ParkourMode.COMPETITIVE);
						Bukkit.getPluginManager().callEvent(editEvent);
					}
					
				},
				14
		);
	}

}
