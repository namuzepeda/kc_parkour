package me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.ItemBuilder;
import me.nicomunoz.kiroscraft.parkour.shared.utils.ChatParser;

public class ExtendedItemBuilder {
	
	public static ItemStack build(String section, FileConfiguration config) {
		ItemBuilder builder = new ItemBuilder(Material.valueOf(config.getString(section + ".type")));
		if(config.contains(section + ".durability"))
			builder.durability(Short.parseShort(config.getString(section + ".durability")));
		if(config.contains(section + ".lore")) {
			List<String> lore = config.getStringList(section + ".lore").stream().map(obj -> ChatParser.toColor(obj)).collect(Collectors.toList());
			builder.lores(lore.toArray(new String[lore.size()]));
		}
		if(config.contains(section + ".name"))
			builder.name(ChatParser.toColor(config.getString(section + ".name")));
		if(config.contains(section + ".skull"))
			builder.skullOwner(config.getString(section + ".skull"));
		return builder.make();
	}

}
