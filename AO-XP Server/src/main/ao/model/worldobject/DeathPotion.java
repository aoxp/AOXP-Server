package ao.model.worldobject;

import java.util.List;

import ao.model.character.Character;
import ao.model.character.archetype.UserArchetype;

/**
 * A potion to recover hit points.
 */
public class DeathPotion extends ConsumableItem {

	/**
	 * Creates a new DeathPotion instance.
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
	public DeathPotion(int id, String name, int amount, boolean tradeable,
			int graphic, int value, int usageDifficulty,
			int manufactureDifficulty, List<UserArchetype> forbiddenArchetypes) {
		super(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, forbiddenArchetypes);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new DeathPotion(id, name, amount, tradeable, graphic, value,
				usageDifficulty, manufactureDifficulty, forbiddenArchetypes);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.ConsumableItem#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		super.use(character);
		
		// Remove all his HP points!
		character.addToHitPoints(-character.getHitPoints());
	}
}
