package me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.events.RankUpEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.shared.utils.StringUtils;

public class RankListener implements Listener {
	
	@EventHandler
	public void onRankUpEvent(RankUpEvent event) {
		Player player = event.getPlayer();
		Message.key(ParkourProperties.RANK_UP_SUCCESS, player, 
				"%rank", event.getNewRank().getName());
		player.setPlayerListName(StringUtils.replace(Config.asString(ParkourProperties.RANK_FORMAT_TABLISTNAME), 
				"%player", player.getName(),
				"%prefix", event.getNewRank().getPrefix()
		));
		player.setDisplayName(StringUtils.replace(Config.asString(ParkourProperties.RANK_FORMAT_DISPLAYNAME), 
				"%player", player.getName(),
				"%prefix", event.getNewRank().getPrefix()
		));
	}

}
