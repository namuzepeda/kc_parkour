package me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.juliarn.npc.NPC;
import com.github.juliarn.npc.event.PlayerNPCInteractEvent;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;

public class MiscelaneousListener implements Listener {
	
	@EventHandler
	public void onPlayerInteractNPCEvent(PlayerNPCInteractEvent event) {
		NPC npc = event.getNPC();
		if(npc.getLocation().equals(ParkourCore.getInstance().getMiscelaneousManager().getNPCManager().getSelector().getLocation())) {
			event.getPlayer().openInventory(ParkourCore.getInstance().getGameManager().getModeSelectorInventory().getInventory());
		}
	}

}
