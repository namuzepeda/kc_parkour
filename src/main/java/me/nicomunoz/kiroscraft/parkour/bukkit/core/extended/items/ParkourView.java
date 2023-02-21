package me.nicomunoz.kiroscraft.parkour.bukkit.core.extended.items;

import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemViewElement;
import me.nicomunoz.kiroscraft.parkour.bukkit.utils.extended.items.view.ItemViewImpl;

public enum ParkourView implements ItemViewImpl {
	
	MAKER(
			new ItemViewElement(
					0,
					ParkourItem.MAKER_SPAWN
			),
			new ItemViewElement(
					2,
					ParkourItem.MAKER_START
			),
			new ItemViewElement(
					3,
					ParkourItem.MAKER_END
			),
			new ItemViewElement(
					4,
					ParkourItem.MAKER_CHECKPOINT_ADD
			),
			new ItemViewElement(
					5,
					ParkourItem.MAKER_CHECKPOINT_REMOVE
			),
			new ItemViewElement(
					7,
					ParkourItem.MAKER_CANCEL
			),
			new ItemViewElement(
					8,
					ParkourItem.MAKER_SAVE
			)
	),
	MODE_FREE(),
	MODE_FREE_CHECKPOINT(
			new ItemViewElement(
					0,
					ParkourItem.GAME_CHECKPOINT
			)
	),
	MODE_COMPETITIVE(),
	MODE_COMPETITIVE_RESTART(
			new ItemViewElement(
					8,
					ParkourItem.GAME_RESTART
			)
	),
	MODE_COMPETITIVE_CHECKPOINT(
			new ItemViewElement(
					0,
					ParkourItem.GAME_CHECKPOINT
			),
			new ItemViewElement(
					8,
					ParkourItem.GAME_RESTART
			)
	);
	
	private ItemViewElement[] elements;
	
	ParkourView(ItemViewElement... elements) {
		this.elements = elements;
	}

	@Override
	public ItemViewElement[] elements() {
		return this.elements;
	}

}
