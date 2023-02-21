package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items;

import org.bukkit.inventory.ItemStack;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickActionInterface;

public interface ItemImpl {
	
	public ItemStack item();
	public ClickActionInterface action();

}
