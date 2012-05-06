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

package com.ao.model.spell.effect;

import com.ao.model.character.Character;
import com.ao.model.worldobject.WorldObject;

public interface Effect {

	/**
	 * Applies the effect in the given character.
	 * @param caster The character casting the spell with the effect.
	 * @param target The character where to apply the effect.
	 */
	void apply(Character caster, Character target);
	
	/**
	 * Applies the effect in the given object
	 * @param caster The character casting the spell with the effect
	 * @param target The world object on which to apply the effect.
	 */
	void apply(Character caster, WorldObject target);

	/**
	 * Checks whether the effect can be applied to the given character, or not.
	 * @param caster The caster character.
	 * @param target The target character.
	 * @return True if the effect can be applied, false otherwise.
	 */
	boolean appliesTo(Character caster, Character target);
	
	/**
	 * Checks whether the effect can be applied to the given world object, or not.
	 * @param caster The caster of the spell.
	 * @param worldobject The target object.
	 * @return True if the effect can be applied, false otherwise.
	 */
	boolean appliesTo(Character caster, WorldObject worldobject);
}
