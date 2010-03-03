package ao.model.worldobject;

import ao.model.character.Character;
import ao.model.worldobject.properties.DefensiveItemProperties;

/**
 * Accessory items such as rings.
 */
public class Accessory extends AbstractDefensiveItem {

	/**
	 * Creates a new Accessory instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public Accessory(DefensiveItemProperties properties, int amount) {
		super(properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new Accessory((DefensiveItemProperties) properties, amount);
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
