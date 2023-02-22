package me.nicomunoz.kiroscraft.parkour.bukkit.core.utils;

import java.io.IOException;

import me.nicomunoz.kiroscraft.parkour.bukkit.core.ParkourCore;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.PropertiesHandler;

public class ParkourProperties extends PropertiesHandler {
	
	public ParkourProperties() throws IOException {
		super(ParkourCore.getInstance().getMain(), "parkour");
	}
	
	public static String ERROR = "error";

	public static String NOT_PLAYER = "not_player";
	public static String NOT_PERMISSION = "not_permission";
	
	
	public static String MAKER_NOT_EXIST = "maker.event.edit.not_exist";
	public static String MAKER_ALREADY_EXISTS = "maker.event.create.already_exists";
	public static String MAKER_EVENT_CREATE = "maker.event.create";
	public static String MAKER_EVENT_EDIT_ALREADY_EDITING = "maker.event.edit.already_editing";
	public static String MAKER_EVENT_EDIT_START = "maker.event.edit.start";
	public static String MAKER_EVENT_EDIT_CANCEL = "maker.event.edit.cancel";
	public static String MAKER_EVENT_EDIT_SAVE = "maker.event.edit.save";
	public static String MAKER_EVENT_EDIT_SET_START = "maker.event.edit.set.start";
	public static String MAKER_EVENT_EDIT_SET_END = "maker.event.edit.set.end";
	public static String MAKER_EVENT_EDIT_SET_SPAWN = "maker.event.edit.set.spawn";
	public static String MAKER_EVENT_EDIT_ADD_CHECKPOINT = "maker.event.edit.add.checkpoint";
	public static String MAKER_EVENT_EDIT_REMOVE_CHECKPOINT = "maker.event.edit.remove.checkpoint";
	public static String MAKER_EVENT_EDIT_CONTAINS_CHECKPOINT = "maker.event.edit.contains.checkpoint";
	public static String MAKER_EVENT_EDIT_NOT_CONTAINS_CHECKPOINT = "maker.event.edit.not_contains.checkpoint";
	public static String MAKER_EVENT_EDIT_CANNOT_IF_CHECKPOINT = "maker.event.edit.cannot_if_checkpoint";
	public static String MAKER_EVENT_EDIT_CANNOT_IF_START_END = "maker.event.edit.cannot_if_end_start";
	public static String MAKER_EVENT_EDIT_CANNOT_IF_PLAYERS = "maker.event.edit.cannot_if_players";
	public static String MAKER_EVENT_EDIT_DONE = "maker.event.edit.done";
	
	public static String RANK_SHOP_NO_MONEY = "rank.shop.no_money";
	public static String RANK_SHOP_ALREADY_BOUGHT = "rank.shop.already_bought";
	
	public static String RANK_UP_SUCCESS = "rank.event.up";
	
	public static String RANK_FORMAT_DISPLAYNAME = "rank.format.displayname";
	public static String RANK_FORMAT_TABLISTNAME = "rank.format.tablistname";
	public static String RANK_CHAT_ENABLED = "rank.chat.enabled";
	public static String RANK_CHAT_FORMAT = "rank.chat.format";
	
	public static String BROADCAST_ON_JOIN = "event.join.broadcast.enabled";
	public static String BROADCAST_MESSAGE = "event.join.broadcast.message";
	
	public static String MESSAGE_ON_JOIN = "event.join.message.enabled";
	public static String MESSAGE_ON_JOIN_MSG = "event.join.message.message";
	
	public static String EVENT_START_MSG = "event.start.message";
	public static String EVENT_RESTART_MSG = "event.restart.message";
	public static String EVENT_CHECKPOINT_RESTART_MSG = "event.checkpoint_restart.message";
	public static String EVENT_CHECKPOINT_MSG = "event.checkpoint.message";
	public static String EVENT_FINISH_MSG = "event.finish.message";
	
	public static String PARKOUR_MODE = "parkour.game.mode";
	
	public static String KICK_IF_BUILDING = "kick_if_building";
	public static String KICK_BUILD_MSG = "kick_building_message";
	
	public static String CONFIG_FORCE_EDIT = "parkour.edit.force_if_players";
	
	public static String ACTIONBAR_TIMER_UPDATE = "actionbar_timer.update_time";

}