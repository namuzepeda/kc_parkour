package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract interface ClickActionInterface {

	 void execute(InventoryClickEvent event);
	 void execute(PlayerInteractAtEntityEvent event);
	 void execute(PlayerInteractEvent event);
	 void execute(Player player);

}