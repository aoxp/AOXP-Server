/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server 
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package ao.model.worldobject;

import ao.model.character.Character;
import ao.model.spell.Spell;

import ao.model.worldobject.properties.ParchmentProperties;

/**
 * A spell parchment.
 */
public class Parchment extends ConsumableItem {

	/**
	 * Creates a new Parchment instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public Parchment(ParchmentProperties properties, int amount) {
		super(properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new Parchment((ParchmentProperties) properties, amount);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.ConsumableItem#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		Spell spell = ((ParchmentProperties) properties).getSpell();
		Spell[] spells = character.getSpells();
		
		boolean hasSpell = false;
		
		for (int i = 0; i < spells.length; i++) {
			if (spell.equals(spells[i])) {
				hasSpell = true;
				break;
			}
		}
		
		if (!hasSpell) {
			super.use(character);
			character.addSpell(((ParchmentProperties) properties).getSpell());
		}

	}
	
	/**
	 * Retrieves the parchment's spell.
	 * @return The parchment's spell.
	 */
	public Spell getSpell() {
		return ((ParchmentProperties) properties).getSpell();
	}

}
