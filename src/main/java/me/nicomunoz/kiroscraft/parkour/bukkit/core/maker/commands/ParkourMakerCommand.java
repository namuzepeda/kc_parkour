package me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.arena.ParkourArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.game.ParkourGame;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.ParkourMakerArena;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.maker.player.ParkourMakerPlayer.SetupMode;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourPermission;
import me.nicomunoz.kiroscraft.parkour.bukkit.core.utils.ParkourProperties;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Config;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Message;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.Permission;

public class ParkourMakerCommand implements CommandExecutor {
	
	private static String[] help = {
			"",
			"§e/pmaker create <name> - Crear arena",
			"§e/pmaker edit <arena>  -   Editar arena",
			"§e/pmaker menu  -   Menu arenas",
			""
	};
	
	private void help(Player player) {
		player.sendMessage(help);
	}
	
	private void tryToCreate(Player player, String name) {
		ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getForcedPlayer(player);
		
		if(makerPlayer != null && makerPlayer.isEditing()) {
			Message.key(ParkourProperties.MAKER_EVENT_EDIT_ALREADY_EDITING, player);
			 return ;
		}
		
		if(ParkourCore.getInstance().getArenaManager().exists(name.toLowerCase())) {
			Message.key(ParkourProperties.MAKER_ALREADY_EXISTS, player, "%arena", name);
			return ;
		}
		
		ParkourMakerArena arena = new ParkourMakerArena(name);
		
		makerPlayer.setArena(arena);
		makerPlayer.setSetupMode(SetupMode.CREATE);
		player.openInventory(ParkourCore.getInstance().getMakerManager().getModeSelectorInventory().getInventory());
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void tryToEdit(Player player, String name) {
		ParkourMakerPlayer makerPlayer = ParkourCore.getInstance().getMakerManager().getPlayerManager().getForcedPlayer(player);
		
		if(makerPlayer != null && makerPlayer.isEditing()) {
			Message.key(ParkourProperties.MAKER_EVENT_EDIT_ALREADY_EDITING, player);
			 return ;
		}
		
		if(!ParkourCore.getInstance().getArenaManager().exists(name.toLowerCase())) {
			Message.key(ParkourProperties.MAKER_NOT_EXIST, player, "%arena", name);
			return ;
		}
		
		ParkourGame game = ParkourCore.getInstance().getGameManager().getGame(name);
		if(game != null && !game.getPlayers().isEmpty()) {
			if(!Config.asBoolean(ParkourProperties.CONFIG_FORCE_EDIT)) {
				Message.key(ParkourProperties.MAKER_EVENT_EDIT_CANNOT_IF_PLAYERS, player, help);
				return ;
			}
			//Kickear jugadores
		}
		
		ParkourArena arena = game.getArena();
		ParkourMakerArena makerArena = new ParkourMakerArena(arena.getName());
		
		makerArena.setSpawn(arena.getSpawn());
		makerArena.setStart(arena.getStart());
		makerArena.setEnd(arena.getEnd());
		
		makerArena.getCheckpoints().addAll( (List<Location>) ParkourCore.getInstance().
				getConfigManager().getArena().getConfig().
				getList(arena.getName().toLowerCase() + ".checkpoints", new ArrayList<>()));
		
		makerPlayer.setArena(makerArena);
		makerPlayer.setSetupMode(SetupMode.EDIT);
		player.openInventory(ParkourCore.getInstance().getMakerManager().getModeSelectorInventory().getInventory());
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
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("create") && Permission.has(ParkourPermission.MAKER_CREATE, player, true)) {
				tryToCreate(player, args[1]);
			} else if(args[0].equalsIgnoreCase("edit") && Permission.has(ParkourPermission.MAKER_EDIT, player, true)) {
				tryToEdit(player, args[1]);
			} else {
				help(player);
			}
		} else {
			help(player);
		}
		return false;
	}

}
