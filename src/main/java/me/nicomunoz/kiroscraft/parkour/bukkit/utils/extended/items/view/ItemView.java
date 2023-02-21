package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickActionInterface;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.player.ItemViewPlayerSession;

public class ItemView {
	
	private static HashMap<UUID, ItemViewPlayerSession> players = new HashMap<>();
	
	public static void setView(Player player, ItemViewImpl view) {
		ItemViewPlayerSession session = players.get(player.getUniqueId());
		if(session == null)
		{
			session = new ItemViewPlayerSession();
			session.setContent(player.getInventory().getContents());
		}
		session.setView(view);
		players.put(player.getUniqueId(), session);
		player.getInventory().clear();
		for(ItemViewElement element : view.elements()) {
			player.getInventory().setItem(element.slot, element.item.item());
		}
	}
	
	public static boolean hasView(UUID uuid) {
		return players.get(uuid) != null;
	}
	
	public static Optional<ClickActionInterface> getAction(Player player) {
		ItemViewPlayerSession session = players.get(player.getUniqueId());
		if(session != null) {
			ItemViewImpl view = session.getView();
			for(ItemViewElement element : view.elements()) {
				if(element.slot == player.getInventory().getHeldItemSlot())
					return Optional.of(element.item.action());
			}
		}
		return Optional.empty();
	}
	
	public static void restore(Player player) {
		player.getInventory().clear();
		ItemViewPlayerSession session = players.get(player.getUniqueId());
		if(session != null) {
			player.getInventory().setContents(session.getContent());
			players.remove(player.getUniqueId());
		}
	}

}
