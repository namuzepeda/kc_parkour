package me.nicomunoz.kiroscraft.parkour.bukkit.core.economy;

import java.sql.SQLException;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.api.EconomyAPI;

public class Economy {
	
	private long id;
	private String name;
	
	public Economy(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getBalance(String player) throws SQLException {
		return EconomyAPI.getBalance(this.id, player);
	}
	
}
