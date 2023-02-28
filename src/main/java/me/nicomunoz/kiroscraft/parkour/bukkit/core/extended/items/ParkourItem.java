package me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.items;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories.RanksInventory;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerRollbackEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.events.ParkourGamePlayerRollbackEvent.RollbackMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player.ParkourPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerCancelEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerCheckpointAddEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerCheckpointRemoveEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerEndEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerLeaderboardEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSaveEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerSpawnEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.events.ParkourMakerStartEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickAction;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickActionInterface;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.ItemImpl;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.utils.ExtendedItemBuilder;

public enum ParkourItem implements ItemImpl {

	MAKER_SPAWN(
			"maker.spawn",
			new ClickAction() {
				
				@Override
				public void execute(PlayerInteractEvent event) {
					if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
						ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(event.getPlayer());
						if(makerPlayer != null) {
							ParkourMakerSpawnEvent spawnEvent = new ParkourMakerSpawnEvent(event.getPlayer(), event.getPlayer().getLocation());
							Bukkit.getServer().getPluginManager().callEvent(spawnEvent);
						}
					}
				}
				
			}
	),
	MAKER_START(
			"maker.start",
			new ClickAction() {
				
				@Override
				public void execute(PlayerInteractEvent event) {
					if(event.hasBlock()) {
						if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
							if(event.getClickedBlock().getType().name().endsWith("PRESSURE_PLATE")) {
								ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(event.getPlayer());
								if(makerPlayer != null) {
									ParkourMakerStartEvent checkPointEvent = new ParkourMakerStartEvent(event.getPlayer(), event.getClickedBlock());
									Bukkit.getServer().getPluginManager().callEvent(checkPointEvent);
								}
							}
						}
					}
				}
				
			}
	),
	MAKER_END(
			"maker.end",
			new ClickAction() {
				
				@Override
				public void execute(PlayerInteractEvent event) {
					if(event.hasBlock()) {
						if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
							if(event.getClickedBlock().getType().name().endsWith("PRESSURE_PLATE")) {
								ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(event.getPlayer());
								if(makerPlayer != null) {
									ParkourMakerEndEvent checkPointEvent = new ParkourMakerEndEvent(event.getPlayer(), event.getClickedBlock());
									Bukkit.getServer().getPluginManager().callEvent(checkPointEvent);
								}
							}
						}
					}
				}
				
			}
	),
	MAKER_CHECKPOINT_ADD(
			"maker.checkpoint.add",
			new ClickAction() {
				
				@Override
				public void execute(PlayerInteractEvent event) {
					if(event.hasBlock()) {
						if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
							if(event.getClickedBlock().getType().name().endsWith("PRESSURE_PLATE")) {
								ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(event.getPlayer());
								if(makerPlayer != null) {
									ParkourMakerCheckpointAddEvent checkPointEvent = new ParkourMakerCheckpointAddEvent(event.getPlayer(), event.getClickedBlock());
									Bukkit.getServer().getPluginManager().callEvent(checkPointEvent);
								}
							}
						}
					}
				}
				
			}
	),
	MAKER_CHECKPOINT_REMOVE(
			"maker.checkpoint.remove",
			new ClickAction() {
				
				@Override
				public void execute(PlayerInteractEvent event) {
					if(event.hasBlock()) {
						if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
							if(event.getClickedBlock().getType().name().endsWith("PRESSURE_PLATE")) {
								ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(event.getPlayer());
								if(makerPlayer != null) {
									ParkourMakerCheckpointRemoveEvent checkPointEvent = new ParkourMakerCheckpointRemoveEvent(event.getPlayer(), event.getClickedBlock());
									Bukkit.getServer().getPluginManager().callEvent(checkPointEvent);
								}
							}
						}
					}
				}
				
			}
	),
	MAKER_LEADERBOARD(
		"maker.leaderboard",
		new ClickAction() {
			
			@Override
			public void execute(PlayerInteractEvent event) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(event.getPlayer());
					if(makerPlayer != null) {
						ParkourMakerLeaderboardEvent leaderboardEvent = new ParkourMakerLeaderboardEvent(event.getPlayer(), event.getPlayer().getLocation());
						Bukkit.getServer().getPluginManager().callEvent(leaderboardEvent);
					}
				}
			}
			
		}
	),
	MAKER_SAVE(
			"maker.save",
			new ClickAction() {
				
				@Override
				public void execute(PlayerInteractEvent event) {
					if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
						ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(event.getPlayer());
						if(makerPlayer != null) {
							ParkourMakerSaveEvent saveEvent = new ParkourMakerSaveEvent(event.getPlayer());
							Bukkit.getServer().getPluginManager().callEvent(saveEvent);
						}
					}
				}
				
			}
	),
	MAKER_CANCEL(
			"maker.cancel",
			new ClickAction() {
				
				@Override
				public void execute(PlayerInteractEvent event) {
					if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
						ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(event.getPlayer());
						if(makerPlayer != null) {
							ParkourMakerCancelEvent cancelEvent = new ParkourMakerCancelEvent(event.getPlayer());
							Bukkit.getServer().getPluginManager().callEvent(cancelEvent);
						}
					}
				}
				
			}
	),
	GAME_RESTART(
			"game.restart",
			new ClickAction() {
				
				@Override
				public void execute(Player player) {
					ParkourPlayer parkourPlayer = ParkourCore.getInstance().getGameManager().getPlayerManager().getPlayer(player);
					if(parkourPlayer.getGame() != null) {
						ParkourGamePlayerRollbackEvent restartEvent = new ParkourGamePlayerRollbackEvent(player, parkourPlayer.getGame(), RollbackMode.RESTART);
						Bukkit.getServer().getPluginManager().callEvent(restartEvent);
					}
				}
				
			}
	),
	GAME_CHECKPOINT(
			"game.checkpoint",
			new ClickAction() {
				
				@Override
				public void execute(Player player) {
					ParkourPlayer parkourPlayer = ParkourCore.getInstance().getGameManager().getPlayerManager().getPlayer(player);
					if(parkourPlayer.getGame() != null) {
						ParkourGamePlayerRollbackEvent restartEvent = new ParkourGamePlayerRollbackEvent(player, parkourPlayer.getGame(), RollbackMode.CHECKPOINT);
						Bukkit.getServer().getPluginManager().callEvent(restartEvent);
					}
				}
				
			}
	),
	GAMES(
			"join.games",
			new ClickAction() {
				
				@Override
				public void execute(Player player) {
					player.openInventory(ParkourCore.getInstance().getGameManager().getModeSelectorInventory().getInventory());
				}
				
			}
	),
	RANKS(
			"join.ranks",
			new ClickAction() {
			
				@Override
				public void execute(Player player) {
					try {
						player.openInventory(new RanksInventory(player.getName()).getInventory());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
	);
	
	private ItemStack item;
	private ClickActionInterface action;
	
	ParkourItem(String path, ClickActionInterface action) {
		this.action = action;
		this.item = ExtendedItemBuilder.build(path, ParkourCore.getInstance().getConfigManager().getItems().getConfig());
	}
	
	@Override
	public ItemStack item() {
		return this.item;
	}

	@Override
	public ClickActionInterface action() {
		return this.action;
	}

}
