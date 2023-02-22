package me.nicomunoz.kiroscraft.parkour.bukkit.core.game.timer;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.player.ParkourPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.utils.ParkourMode;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ParkourTimer extends BukkitRunnable {
	
	public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	@Override
	public void run() {
		Iterator<ParkourGame> iterator = ParkourCore.getInstance().getGameManager().games().iterator();
		Date time = new Date();
		while(iterator.hasNext()) {
			ParkourGame game = iterator.next();
			if(game.getArena().getMode() != ParkourMode.COMPETITIVE)
				return ;
			Iterator<ParkourPlayer> playerIterator = game.getPlayers().iterator();
			while(playerIterator.hasNext()) {
				ParkourPlayer parkourPlayer = playerIterator.next();
				if(parkourPlayer.getStart() != null) {
					Player player = parkourPlayer.getPlayer();
					double diff = getDateDiff(parkourPlayer.getStart(), time, TimeUnit.MILLISECONDS) * 0.001;
					player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aTiempo: §e" + String.format("%.3f", diff)));
				}
			}
		}
	}

}
