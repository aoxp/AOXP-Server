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

package ao.model.spell.effect;

import ao.model.character.Character;
import ao.model.character.UserCharacter;
import ao.model.character.archetype.UserArchetype;
import ao.model.worldobject.WorldObject;

/**
 * An effect to mimetise characters.
 */
public class MimetismEffect implements Effect {

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#appliesTo(ao.model.character.Character, ao.model.character.Character)
	 */
	@Override
	public boolean appliesTo(Character caster, Character target) {
		// Only druids (or maybe npcs, not yet defined) can mimetize themselves with NPC's
		if (!(target instanceof UserCharacter) 
				&& (caster instanceof UserCharacter)
				&& ((UserCharacter)caster).getArchetype() != UserArchetype.DRUID.getArchetype()) {
			return false;
		}
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#appliesTo(ao.model.character.Character, ao.model.worldobject.WorldObject)
	 */
	@Override
	public boolean appliesTo(Character caster, WorldObject worldobject) {
		// Only druids (or maybe npcs, not yet defined) can mimetize themselves with world objects
		if (caster instanceof UserCharacter && ((UserCharacter)caster).getArchetype() != UserArchetype.DRUID.getArchetype()) {
			return false;
		}
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#apply(ao.model.character.Character, ao.model.character.Character)
	 */
	@Override
	public void apply(Character caster, Character target) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#apply(ao.model.character.Character, ao.model.worldobject.WorldObject)
	 */
	@Override
	public void apply(Character caster, WorldObject target) {
		// TODO Auto-generated method stub

	}

}
