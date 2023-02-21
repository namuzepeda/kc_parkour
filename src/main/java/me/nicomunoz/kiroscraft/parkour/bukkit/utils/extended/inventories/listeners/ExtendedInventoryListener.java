package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.listeners;

import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickActionInterface;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.holders.ExtendedInventory;

public class ExtendedInventoryListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getView().getTopInventory().getHolder() instanceof ExtendedInventory) {
			event.setCancelled(true);
			if (event.getWhoClicked() instanceof Player) {
				ItemStack itemStack = event.getCurrentItem();
				if (itemStack == null || itemStack.getType() == Material.AIR) return;
				ExtendedInventory customHolder = (ExtendedInventory) event.getView().getTopInventory().getHolder();
				Optional<ClickActionInterface> action = customHolder.getAction(event);
				if(action.isPresent()) {
					action.get().execute(event);
				}
			}
		}
	}

}
