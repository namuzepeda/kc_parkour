package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ParkourMakerCheckpointRemoveEvent extends ParkourMakerPlayerEvent {
	
	private Block block;
	
	public ParkourMakerCheckpointRemoveEvent(Player player, Block block) {
		super(player);
		this.block = block;
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	
	
}
