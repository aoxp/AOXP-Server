package ao.model.worldobject;

import java.util.List;

import ao.model.character.Character;
import ao.model.character.archetype.UserArchetype;

/**
 * Raw Mineral's.
 */
public class Mineral extends AbstractItem {

	/**
	 * Creates a new Mineral instance.
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
	 */
	public Mineral(int id, String name, int amount, boolean tradeable,
			int graphic, int value, int usageDifficulty,
			int manufactureDifficulty,
			List<UserArchetype> forbiddenArchetypes, boolean newbie) {
		super(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, forbiddenArchetypes, newbie);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new Mineral(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, forbiddenArchetypes, newbie);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		// Can't be used by itself, needs a foundry
	}
}
