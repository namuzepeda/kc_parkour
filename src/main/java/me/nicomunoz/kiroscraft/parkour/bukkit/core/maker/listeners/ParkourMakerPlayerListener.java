package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourState;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourPermission;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Permission;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemView;

public class ParkourMakerPlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerLoginEvent event) {
		ParkourGame game = ParkourCore.getInstance().getGameManager().getGame();
		if(game == null || game.getState() == ParkourState.BUILDING) {
			if(!Permission.has(ParkourPermission.MAKER_JOIN, event.getPlayer(), false)) {
				if(Config.asBoolean(ParkourProperties.KICK_IF_BUILDING))
					event.disallow(Result.KICK_OTHER, Config.asString(ParkourProperties.KICK_BUILD_MSG));
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		if(ParkourCore.getInstance().getMakerManager().getPlayerManager().exists(event.getPlayer())) {
			ItemView.restore(event.getPlayer());
			ParkourCore.getInstance().getMakerManager().getPlayerManager().deleteInstance(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(event.getPlayer());
		if(makerPlayer != null) {
			event.setCancelled(makerPlayer.isEditing());
		}
	}
	
	@EventHandler
	public void onPlayerPickupItemEvent(EntityPickupItemEvent event) {
		if(event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(player);
			if(makerPlayer != null) {
				event.setCancelled(makerPlayer.isEditing());
			}
		}
	}

}
