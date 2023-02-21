package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.ItemImpl;

public class ItemViewElement {
	
	public int slot;
	public ItemImpl item;
	
	public ItemViewElement(int slot, ItemImpl item) {
		this.slot = slot;
		this.item = item;
	}

}
