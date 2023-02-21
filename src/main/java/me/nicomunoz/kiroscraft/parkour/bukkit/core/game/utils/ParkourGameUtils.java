package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.utils.ParkourArenaUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;

public class ParkourGameUtils {
	
	public static boolean canPlay(ParkourGame game) {
		return ParkourArenaUtils.isFinished(game.getArena());
	}

}
