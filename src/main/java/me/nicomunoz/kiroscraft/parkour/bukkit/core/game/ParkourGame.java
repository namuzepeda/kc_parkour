package me.nicomunoz.kiroscraft.parkour.bukkit.core.game;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player.ParkourPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.timer.ParkourTimer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourState;

public class ParkourGame {
	
	private Set<ParkourPlayer> players;
	private Date start;
	private Date finish;
	private ParkourState state;
	private ParkourArena arena;
	
	public ParkourGame(ParkourArena arena) {
		this.arena = arena;
		this.players = new HashSet<>();
	}
	
	public Set<ParkourPlayer> getPlayers() {
		return this.players;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}
	
	public ParkourArena getArena() {
		return this.arena;
	}

	public ParkourState getState() {
		return state;
	}

	public void setState(ParkourState state) {
		this.state = state;
	}

}