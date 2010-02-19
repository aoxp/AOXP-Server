package ao.model.worldobject;

import java.util.List;

import ao.model.character.archetype.UserArchetype;

public abstract class AbstractDefensiveItem extends AbstractEquipableItem implements
		DefensiveItem {

	protected int minDef;
	protected int maxDef;
	
	/**
	 * Creates a new AbstractDefensiveitem instance.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param amount The item's amount.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param graphic The graphic for the item.
	 * @param value The item's value.
	 * @param usageDifficulty The item's usage difficulty.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param newbie Whether the item is newbie or nor.
	 * @param equippedGraphic The index of the graphic when equipped.
	 * @param minDef The minimum defense granted by this item.
	 * @param maxDef The maximum defense granted by this item.
	 */
	public AbstractDefensiveItem(int id, String name, int amount,
			boolean tradeable, int graphic, int value, int usageDifficulty,
			int manufactureDifficulty, List<UserArchetype> forbiddenArchetypes,
			boolean newbie, int equippedGraphic, int minDef, int maxDef) {
		super(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, forbiddenArchetypes, newbie, equippedGraphic);
		
		this.minDef = minDef;
		this.maxDef = maxDef;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.DefensiveItem#getMaxDef()
	 */
	@Override
	public int getMaxDef() {
		return maxDef;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.DefensiveItem#getMinDef()
	 */
	@Override
	public int getMinDef() {
		return minDef;
	}
}
