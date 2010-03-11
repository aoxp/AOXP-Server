package ao.model.worldobject.properties;

import java.util.List;

import ao.model.character.Race;
import ao.model.character.archetype.UserArchetype;
import ao.model.spell.Spell;
import ao.model.worldobject.WorldObjectType;

public class ParchmentProperties extends ItemProperties {

	protected Spell spell;
	
	/**
	 * Create a new ParchmentProperties instance.
	 * @param type The type of the item.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param value The item's value.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param forbiddenRaces List of Races not allowed to use this item.
	 * @param newbie Whether the item is newbie or not.
	 * @param spell the parchment's spell.
	 */
	public ParchmentProperties(WorldObjectType type, int id, String name,
			int graphic, boolean tradeable, int value,
			int manufactureDifficulty, List<UserArchetype> forbiddenArchetypes,
			List<Race> forbiddenRaces, boolean newbie, Spell spell) {		
		super(type, id, name, graphic, tradeable, value, manufactureDifficulty,
				forbiddenArchetypes, forbiddenRaces, newbie);
		
		this.spell = spell;
	}
	
	/**
	 * @return the parchment's spell.
	 */
	public Spell getSpell() {
		return spell;
	}

}
