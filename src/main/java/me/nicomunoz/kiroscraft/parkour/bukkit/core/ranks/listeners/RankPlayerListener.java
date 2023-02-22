package me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.listeners;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.player.PlayerData;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.Rank;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;
import me.nicomunoz.kiroscraft.parkour.shared.utils.StringUtils;

public class RankPlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		try {
			Player player = event.getPlayer();
			PlayerData playerData = PlayerData.get(player.getName());
			Rank rank = playerData.getRank();
			if(rank != null) {
				player.setPlayerListName(StringUtils.replace(Config.asString(ParkourProperties.RANK_FORMAT_TABLISTNAME), 
						"%player", player.getName(),
						"%prefix", rank.getPrefix()
				));
				player.setDisplayName(StringUtils.replace(Config.asString(ParkourProperties.RANK_FORMAT_DISPLAYNAME), 
						"%player", player.getName(),
						"%prefix", rank.getPrefix()
				));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
		if(Config.asBoolean(ParkourProperties.RANK_CHAT_ENABLED)) {
			Player player = event.getPlayer();
			String format = Config.asString(ParkourProperties.RANK_CHAT_FORMAT);
			event.setFormat(StringUtils.replace(format, 
					"%name", player.getName(),
					"%displayname", player.getDisplayName(),
					"%msg", event.getMessage()
			));
		}
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		PlayerData.delete(event.getPlayer().getName());
	}

}
