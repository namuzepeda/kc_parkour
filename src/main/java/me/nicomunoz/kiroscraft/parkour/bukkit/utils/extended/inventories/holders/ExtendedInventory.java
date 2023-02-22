package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.holders;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickActionInterface;

public class ExtendedInventory extends ActionHolder implements InventoryHolder {
	
	protected Inventory inventory;
	
	public ExtendedInventory(String title, int size) {
		inventory = Bukkit.createInventory(this, size, title);
	}
	
	public void addItem(ItemStack item, ClickActionInterface action, int... slot) {
		if(slot != null) {
			for(Integer i : slot) {
				addAction(i, action);
				inventory.setItem(i, item);
			}
		}
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

}
