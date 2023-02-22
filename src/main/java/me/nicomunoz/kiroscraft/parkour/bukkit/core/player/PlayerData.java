package me.nicomunoz.kiroscraft.parkour.bukkit.core.player;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.economy.api.EconomyAPI;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.Rank;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.api.RankAPI;

public class PlayerData {

    public static HashMap<String, PlayerData> playerData = new HashMap<>();
    
    public static PlayerData get(String name) throws SQLException {
        if (!playerData.containsKey(name)) {
            PlayerData data = new PlayerData(name);
            playerData.put(name, data);
            return data;
        }
        return playerData.get(name);
    }
    
    public static void delete(String player) {
    	if(playerData.containsKey(player))
    		playerData.remove(player);
    }

    private int coins;
    private Rank rank;

    private PlayerData(String player) throws SQLException {
        this.coins = EconomyAPI.getBalance(ParkourCore.getInstance().getEconomyManager().getCoins().getId(), player);
        this.rank = RankAPI.getRank(player);
    }
    
    public Rank getRank() {
    	return this.rank;
    }
    
    public int getCoins() {
    	return this.coins;
    }

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
    
}
