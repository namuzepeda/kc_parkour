package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class ClickAction implements ClickActionInterface{

	@Override
	public void execute(InventoryClickEvent event) {
		execute((Player) event.getWhoClicked());
	}

	@Override
	public void execute(Player player) {
		
	}
	
	@Override
	public void execute(PlayerInteractEvent event) {
		execute(event.getPlayer());
	}
	
	@Override
	public void execute(PlayerInteractAtEntityEvent event) {
		execute(event.getPlayer());
	}
	
}
