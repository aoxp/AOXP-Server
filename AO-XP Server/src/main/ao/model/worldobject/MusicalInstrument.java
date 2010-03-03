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

import java.util.List;

import ao.model.character.Character;
import ao.model.worldobject.properties.MusicalInstrumentProperties;

/**
 * A musical instrument.
 */
public class MusicalInstrument extends AbstractItem {

	/**
	 * Creates a new MusicalInstrument instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public MusicalInstrument(MusicalInstrumentProperties properties, int amount) {
		super(properties, amount);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new MusicalInstrument((MusicalInstrumentProperties) properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		// Instruments do nothing to the character when used.
	}

	/**
	 * Retrieves a random sound to be played.
	 * @return A random sound to play.
	 */
	public int getSoundToPlay() {
		List<Integer> sounds = ((MusicalInstrumentProperties) properties).getSounds();
		return sounds.get((int) Math.floor(Math.random() * sounds.size()));
	}
}
