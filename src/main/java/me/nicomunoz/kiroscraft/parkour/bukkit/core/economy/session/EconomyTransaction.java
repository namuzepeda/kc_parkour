package me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.session;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.Economy;

public class EconomyTransaction {
	
	private Economy economy;
	private String player;
	private int amount;
	
	public EconomyTransaction(Economy economy, String player, int amount) {
		super();
		this.economy = economy;
		this.player = player;
		this.amount = amount;
	}
	
	public Economy getEconomy() {
		return economy;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void add(int gain) {
		this.amount += gain;
	}
	
	public void lose(int lose) {
		this.amount -= lose;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof EconomyTransaction) {
			EconomyTransaction transaction = (EconomyTransaction) obj;
			return transaction.getEconomy().equals(economy) && transaction.getPlayer() == getPlayer();
		}
		return false;
	}
	
}
