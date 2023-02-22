package me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.inventories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.api.EconomyAPI;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.events.EconomyWithdrawEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.session.EconomyTransaction;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.player.PlayerData;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.Rank;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.api.RankAPI;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.events.RankUpEvent;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.utils.RankUtils;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.ItemBuilder;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.actions.ClickAction;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.inventories.holders.ExtendedInventory;
import me.nicomunoz.kiroscraft.parkour.connection.MasterConnection;
import me.nicomunoz.kiroscraft.parkour.shared.utils.ChatParser;
import me.nicomunoz.kiroscraft.parkour.shared.utils.StringUtils;

public class RanksInventory extends ExtendedInventory {
	
	public RanksInventory(String player) throws SQLException {
		super(
				ChatParser.toColor(ParkourCore.getInstance().getConfigManager().getInventories().getConfig().getString("ranks.title")), 
				ParkourCore.getInstance().getConfigManager().getInventories().getConfig().getInt("ranks.size")
		);
		int i = 0;
		PlayerData playerData = PlayerData.get(player);
		for(Rank rank : ParkourCore.getInstance().getRankManager().ranks()) {
			addRank(playerData, rank, i++);
		}
	}

	private void addRank(PlayerData playerData, Rank rank, int slot) {
		if(RankUtils.ownRank(playerData, rank)) {
			addItem(
					item(rank, "bought", playerData.getCoins()), 
					new ClickAction() {
						
						@Override
						public void execute(Player player) {
							player.closeInventory();
							Message.key(ParkourProperties.RANK_SHOP_ALREADY_BOUGHT, player);
						}
						
					}, 
					slot
			);
		} else if(RankUtils.enoughMoneyToBuy(playerData, rank)) {
			addItem(
					item(rank, "buy", playerData.getCoins()), 
					new ClickAction() {
						
						@Override
						public void execute(Player player) {
							player.closeInventory();
							try {
								Connection conn = MasterConnection.getConnection(true);
								conn.setAutoCommit(false);
								EconomyTransaction transaction = new EconomyTransaction(ParkourCore.getInstance().getEconomyManager().getCoins(), player.getName(), rank.getPrice());
								if(RankAPI.setRank(player.getName(), rank.getId(), conn)
										&& EconomyAPI.withdraw(transaction, conn)) {
									conn.commit();
									playerData.setRank(rank);
									playerData.setCoins(playerData.getCoins() - rank.getPrice());
									EconomyWithdrawEvent withdrawEvent = new EconomyWithdrawEvent(transaction);
									RankUpEvent rankEvent = new RankUpEvent(playerData.getRank(), rank, player);
									Bukkit.getPluginManager().callEvent(withdrawEvent);
									Bukkit.getPluginManager().callEvent(rankEvent);
								} else throw new SQLException();
							} catch (SQLException e) {
								Message.key(ParkourProperties.ERROR, player);
								e.printStackTrace();
							}
						}
						
					}, 
					slot
			);
		} else {
			addItem(
					item(rank, "not_enough", playerData.getCoins()), 
					new ClickAction() {
						
						@Override
						public void execute(Player player) {
							player.closeInventory();
							Message.key(ParkourProperties.RANK_SHOP_NO_MONEY, player);
						}
						
					}, 
					slot
			);
		}
	}
	
	private ItemStack item(Rank rank, String path, int coins) {
		path = "ranks.items." + path + ".";
		String[] replaces = new String[] {
			"%rank", rank.getName(),
			"%prefix", rank.getPrefix(),
			"%price", rank.getPrice() + "",
			"%coins", coins + ""
		};
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getInventories().getConfig();
		ItemBuilder builder = new ItemBuilder(Material.valueOf(config.getString(path + "type")));
		builder.name(StringUtils.replace(config.getString(path + ".name"), replaces));
		builder.lores(config.getStringList(path + ".lore").stream().map(pbj -> StringUtils.replace(pbj, replaces)).collect(Collectors.toList()));
		return builder.make();
	}
	
}
