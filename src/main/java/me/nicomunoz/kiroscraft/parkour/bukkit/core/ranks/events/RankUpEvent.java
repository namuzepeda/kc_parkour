package me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.events;

import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.events.ParkourEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.Rank;

public class RankUpEvent extends ParkourEvent {
	
	private Rank oldRank;
	private Rank newRank;
	private Player player;
	
	public RankUpEvent(Rank oldRank, Rank newRank, Player player) {
		this.oldRank = oldRank;
		this.newRank = newRank;
		this.player = player;
	}

	public Rank getOldRank() {
		return oldRank;
	}

	public Rank getNewRank() {
		return newRank;
	}

	public Player getPlayer() {
		return player;
	}

}
