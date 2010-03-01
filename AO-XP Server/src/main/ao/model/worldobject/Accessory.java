package ao.model.worldobject;

import java.util.List;

import ao.model.character.Character;
import ao.model.character.archetype.UserArchetype;

/**
 * Accessory items such as rings.
 */
public class Accessory extends AbstractDefensiveItem {

	/**
	 * Creates a new Accessory instance.
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
	 * @param minMagicDef The minimum magic defense granted by this item.
	 * @param maxMagicDef The maximum magic defense granted by this item.
	 */
	public Accessory(int id, String name, int amount, boolean tradeable,
			int graphic, int value, int usageDifficulty,
			int manufactureDifficulty, List<UserArchetype> forbiddenArchetypes,
			boolean newbie, int equippedGraphic, int minDef, int maxDef,
			int minMagicDef, int maxMagicDef) {
		super(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, forbiddenArchetypes, newbie, equippedGraphic,
				minDef, maxDef, minMagicDef, maxMagicDef);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new Accessory(id, name, amount, tradeable, graphic, value, usageDifficulty, manufactureDifficulty, forbiddenArchetypes, newbie, equippedGraphic, minDef, maxDef, minMagicDef, maxMagicDef);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		// Accessories are not used, just equipped
	}

}
