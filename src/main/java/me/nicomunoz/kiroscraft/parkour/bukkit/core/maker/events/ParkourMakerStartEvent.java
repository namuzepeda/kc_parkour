package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ParkourMakerStartEvent extends ParkourMakerPlayerEvent {
	
	private Block block;
	
	public ParkourMakerStartEvent(Player player, Block block) {
		super(player);
		this.block = block;
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	
	
}
