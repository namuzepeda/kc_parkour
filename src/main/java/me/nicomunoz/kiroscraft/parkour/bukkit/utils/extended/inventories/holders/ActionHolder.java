package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.holders;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.event.inventory.InventoryClickEvent;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickActionInterface;

public class ActionHolder {
	
	private final Map<Integer, ClickActionInterface> actions = new  HashMap<>();
	
	public void addAction(int slot, ClickActionInterface action) {
		this.actions.put(slot, action);
	}
	
	public void addAction(ClickActionInterface action, int... slots) {
		for(int slot : slots) {
			actions.put(slot, action);
		}
	}
	
	public Optional<ClickActionInterface> getAction(InventoryClickEvent event) {
		return actions.entrySet().stream().filter(obj -> obj.getKey().intValue() == event.getRawSlot()).map(obj -> obj.getValue()).findFirst();
	}
	
    public void clear() {
    	this.actions.clear();
    }

}
