package me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.events;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.session.EconomyTransaction;

public class EconomyWithdrawEvent extends EconomyEvent {
	
	private final EconomyTransaction transaction;

	public EconomyWithdrawEvent(EconomyTransaction transaction) {
		this.transaction = transaction;
	}
	
	public EconomyTransaction getTransaction() {
		return this.transaction;
	}

}
