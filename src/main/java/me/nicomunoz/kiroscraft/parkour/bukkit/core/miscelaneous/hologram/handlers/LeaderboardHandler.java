package me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.hologram.handlers;

import java.sql.SQLException;

import org.bukkit.Location;

import com.github.unldenis.hologram.Hologram;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourGameUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;
import me.nicomunoz.kiroscraft.parkour.shared.utils.StringUtils;

public class LeaderboardHandler {
	
	public static String[] defaultValues;
	
	private Location location;
	private Hologram hologram;
	private String arena;
	
	public LeaderboardHandler(String arena, Location location) throws SQLException {
		if(defaultValues == null) {
			defaultValues = new String[] { 
					Config.asString(ParkourProperties.HOLO_LEADERBOARD_DEFAULT_PLAYER), 
					Config.asString(ParkourProperties.HOLO_LEADERBOARD_DEFAULT_TIME)
			};
		}
		this.arena = arena;
		this.location = location;
		if(this.location != null) {
			spawn();
		}
	}
	
	public synchronized void update() throws SQLException {
		destroy();
		spawn();
	}
	
	public void spawn() throws SQLException {
		if(this.location == null)
			return ;
		Hologram.Builder builder = Hologram.builder();
		builder.location(location);
		builder.addLine(Config.asString(ParkourProperties.HOLO_LEADERBOARD_TOP));
		for(String[] mark : ParkourGameUtils.getTopRunners(this.arena, 10, defaultValues)) {
			builder.
			addLine(
					StringUtils.replace(Config.asString(ParkourProperties.HOLO_LEADERBOARD_FORMAT), 
							"%player", mark[0],
							"%time", mark[1]
					)
			);
		}
		builder.addLine(Config.asString(ParkourProperties.HOLO_LEADERBOARD_BOTTOM));
		this.hologram = builder.build(ParkourCore.getInstance().getMiscelaneousManager().getHologramManager().getPool());
	}
	
	public void destroy() {
		if(this.hologram != null)
			ParkourCore.getInstance().getMiscelaneousManager().getHologramManager().getPool().remove(hologram);
	}

	public Location getLocation() {
		return this.location;
	}

}
