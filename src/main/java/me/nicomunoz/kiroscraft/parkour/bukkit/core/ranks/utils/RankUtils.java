package me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.utils;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.player.PlayerData;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.Rank;

public class RankUtils {
	
	public static boolean ownRank(PlayerData playerData, Rank rank) {
		return playerData.getRank() != null && playerData.getRank().getLevel() >= rank.getLevel();
	}
	
	public static boolean enoughMoneyToBuy(PlayerData playerData, Rank rank) {
		return playerData.getCoins() >= rank.getPrice();
	}

}
