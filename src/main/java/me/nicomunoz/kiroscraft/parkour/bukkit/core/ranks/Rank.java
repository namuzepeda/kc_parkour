package me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks;

public class Rank {
	
	private long id;
	private String name;
	private String prefix;
	private int price;
	private int level;
	
	public Rank(long id, String name, String prefix, int price, int level) {
		this.id = id;
		this.name = name;
		this.prefix = prefix;
		this.price = price;
		this.level = level;
	}

	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getPrice() {
		return price;
	}
	
	public int getLevel() {
		return this.level;
	}

}
