package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.listeners;

import java.sql.SQLException;
import java.util.Optional;

import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickActionInterface;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemView;

public class ExtendedItemListener implements Listener {
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) throws SQLException {
		if(event.useItemInHand() == Result.DENY) return;
		if(event.hasItem()) {
			Optional<ClickActionInterface> action = ItemView.getAction(event.getPlayer());
			if(action.isPresent()) {
				action.get().execute(event);
			}
		}
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if(event.getClickedInventory() != null && ItemView.hasView(event.getWhoClicked().getUniqueId())) {
			event.setCancelled(event.getClickedInventory() instanceof PlayerInventory);
		}
	}

}
