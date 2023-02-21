package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.player;

import org.bukkit.inventory.ItemStack;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemViewImpl;

public class ItemViewPlayerSession {
	
	private ItemViewImpl view;
	private ItemStack[] content;

	public ItemViewImpl getView() {
		return view;
	}
	
	public void setView(ItemViewImpl view) {
		this.view = view;
	}
	
	public ItemStack[] getContent() {
		return content;
	}
	
	public void setContent(ItemStack[] content) {
		this.content = content;
	}
	
}
