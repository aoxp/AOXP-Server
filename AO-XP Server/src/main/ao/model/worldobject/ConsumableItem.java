package ao.model.worldobject;

import java.util.List;

import ao.model.character.Character;
import ao.model.character.archetype.UserArchetype;

public abstract class ConsumableItem extends AbstractItem {

	/**
	 * Creates a new ConsumableItem instance.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param amount The item's amount.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param graphic The graphic for the item.
	 * @param value The item's value.
	 * @param usageDifficulty The item's usage difficulty.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 */
	public ConsumableItem(int id, String name, int amount, boolean tradeable,
			int graphic, int value, int usageDifficulty,
			int manufactureDifficulty, boolean smallRace,
			List<UserArchetype> forbiddenArchetypes) {
		super(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, smallRace, forbiddenArchetypes);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		// Discount element being used.
		if (--this.amount == 0) {
			// Clean the inventory
			character.getInventory().cleanup();
		}
	}

}
