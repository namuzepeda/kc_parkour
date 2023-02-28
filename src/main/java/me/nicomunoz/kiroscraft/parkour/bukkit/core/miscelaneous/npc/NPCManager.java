package me.nicomunoz.kiroscraft.parkour.bukkit.core.miscelaneous.npc;

import java.util.Random;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.juliarn.npc.NPC;
import com.github.juliarn.npc.NPCPool;
import com.github.juliarn.npc.profile.Profile;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;


public class NPCManager {
	
	private NPCPool pool;
	
	private NPC selector;
	
	public NPCManager() {
		this.pool = NPCPool.createDefault(ParkourCore.getInstance().getMain());
		buildNPCs();
	}
	
	public void buildNPCs() {
		if(selector != null)
			return ;
		FileConfiguration config = ParkourCore.getInstance().getConfigManager().getMiscelanousConfig().getConfig();
		if(config.contains("npcs.selector.location") && config.contains("npcs.selector.skin")) {
			this.selector = NPC.builder()
					.imitatePlayer(false)
					.lookAtPlayer(true)
					.location(config.getLocation("npcs.selector.location"))
					.profile(createProfile(config.getString("npcs.selector.skin"), Config.asString(ParkourProperties.NPC_SELECTOR_NAME)))
					.build(pool);
		}
	}
	
	public void destroyNPCs() {
		if(selector != null)
			pool.removeNPC(selector.getEntityId());
		selector = null;
	}
	
	public NPC getSelector() {
		return this.selector;
	}
	
	private Profile createProfile(String name, String skin) {
		Profile profile = new Profile(name);
	    profile.complete();
	    profile.setName(skin);
	    profile.setUniqueId(new UUID(new Random().nextLong(), 0));

	    return profile;
	  }
	
}
