package me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.google.common.io.Files;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.listeners.RankListener;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.ranks.listeners.RankPlayerListener;
import me.nicomunoz.kiroscraft.parkour.connection.Query;

public class RankManager {
	
	private Listener[] listeners = new Listener[] { new RankListener(), new RankPlayerListener() };
	
	private LinkedHashMap<Long, Rank> ranks;
	
	public RankManager() throws IOException, SQLException {
		this.ranks = new LinkedHashMap<>();
		defaultRanks();
		build();
		createTables();
		
		for(Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, ParkourCore.getInstance().getMain());
		}
	}
	
	public Collection<Rank> ranks() {
		return ranks.values();
	}
	
	public Rank getRank(long id) {
		return ranks.get(id);
	}
	
	private void build() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		File file = new File(ParkourCore.getInstance().getMain().getDataFolder(), "ranks.json");
		JsonArray jsonRanks = JsonParser.parseReader(new FileReader(file)).getAsJsonArray();
		int i = 0;
		for(JsonElement jsonRankElement : jsonRanks) {
			JsonObject jsonRank = jsonRankElement.getAsJsonObject();
			long id = jsonRank.get("id").getAsLong();
			String name = jsonRank.get("name").getAsString();
			String prefix = jsonRank.get("prefix").getAsString();
			int price = jsonRank.get("price").getAsInt();
			Rank rank = new Rank(id, name, prefix, price, i++);
			ranks.put(id, rank);
		}
	}
	
	private void defaultRanks() throws IOException {
		File dataFolder = ParkourCore.getInstance().getMain().getDataFolder();
		if(!dataFolder.exists())
			dataFolder.mkdir();
		File file = new File(dataFolder, "ranks.json");
		if(!file.exists()) {
			file.createNewFile();
			InputStream initialStream = ParkourCore.getInstance().getMain().getResource("ranks.json");
		    byte[] buffer = new byte[initialStream.available()];
		    initialStream.read(buffer);

		    Files.write(buffer, file);
		    initialStream.close();
		}
	}
	
	private void createTables() throws SQLException {
		new Query(
				null,
				"CREATE TABLE IF NOT EXISTS `rank_player` (\n"
				+ "  `player` varchar(16) NOT NULL,\n"
				+ "  `rank` bigint(20) NOT NULL,\n"
				+ "  PRIMARY KEY (`player`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"
		).executeUpdate();
	}

}
