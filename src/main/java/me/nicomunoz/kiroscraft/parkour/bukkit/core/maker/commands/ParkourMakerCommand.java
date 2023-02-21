package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourPermission;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Permission;

public class ParkourMakerCommand implements CommandExecutor {
	
	private static String[] help = {
			"",
			"§e/pmaker edit   -   Editar arena",
			""
	};
	
	private void help(Player player) {
		player.sendMessage(help);
	}
	
	private void tryToEdit(Player player) {
		ParkourGame game = ParkourCore.getInstance().getGameManager().getGame();
		if(game.getPlayers().isEmpty()) {
			ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getPlayer(player);
			if(makerPlayer != null && makerPlayer.isEditing()) {
				Message.key(ParkourProperties.MAKER_EVENT_EDIT_ALREADY_EDITING, player);
			} 
			else 
				player.openInventory(ParkourCore.getInstance().getMakerManager().getModeSelectorInventory().getInventory());
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(! (sender instanceof Player) ) {
			Message.key(ParkourProperties.NOT_PLAYER, sender);
			return true;
		}
		Player player = (Player) sender;
		if(args.length == 0) {
			if(Permission.has(ParkourPermission.MAKER_HELP, player, true)) {
				help(player);
			}
		} else if(args.length == 1) {
			if(args[0].equalsIgnoreCase("edit")) {
				if(Permission.has(ParkourPermission.MAKER_EDIT, player, true)) {
					tryToEdit(player);
				}
			} else {
				help(player);
			}
		}
		return false;
	}

}
