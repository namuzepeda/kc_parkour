package me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.hologram;

import com.github.unldenis.hologram.HologramPool;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.hologram.handlers.LeaderboardHandler;

public class HologramManager {
	
	private HologramPool pool;
	
	
	public HologramManager() {
		this.pool = new HologramPool(ParkourCore.getInstance().getMain(), 70, 0.5f, 5f);
		
	}
	
	public HologramPool getPool() {
		return this.pool;
	}

}
