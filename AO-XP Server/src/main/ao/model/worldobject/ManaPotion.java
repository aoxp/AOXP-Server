package ao.model.worldobject;

import java.util.List;

import ao.model.character.Character;
import ao.model.character.archetype.UserArchetype;

/**
 * A potion to recover mana.
 */
public class ManaPotion extends ConsumableItem {

	protected int minMana;
	protected int maxMana;
	
	/**
	 * Creates a new ManaPotion instance.
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
	 * @param minMana The minimum mana replenished by the potion.
	 * @param maxMana The maximum mana replenished by the potion.
	 */
	public ManaPotion(int id, String name, int amount, boolean tradeable,
			int graphic, int value, int usageDifficulty,
			int manufactureDifficulty, List<UserArchetype> forbiddenArchetypes,
			boolean newbie, int minMana, int maxMana) {
		super(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, forbiddenArchetypes, newbie);
		
		this.minMana = minMana;
		this.maxMana = maxMana;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new ManaPotion(id, name, amount, tradeable, graphic, value,
				usageDifficulty, manufactureDifficulty, forbiddenArchetypes,
				newbie, minMana, maxMana);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.ConsumableItem#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		super.use(character);
		
		character.addToMana((int) (Math.random() * (maxMana - minMana + 1)) + minMana);
	}

	/**
	 * Retrieves the minimum man restored by the potion.
	 * @return The minimum mana restored by the potion.
	 */
	public int getMinMana() {
		return minMana;
	}

	/**
	 * Retrieves the maximum mana restored by the potion.
	 * @return The maximum mana restored by the potion.
	 */
	public int getMaxMana() {
		return maxMana;
	}
}
