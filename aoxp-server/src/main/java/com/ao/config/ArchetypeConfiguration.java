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

package com.ao.config;

import com.ao.model.character.archetype.Archetype;

public interface ArchetypeConfiguration {

	/**
	 * Retrieves the given archetype's evasion modifier
	 * @param archetype The archetype's class.
	 * @return The archetype's evasion modifier.
	 */
	float getEvasionModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's melee accuracy modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's melee accuracy.
	 */
	float getMeleeAccuracyModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's ranged accuracy modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's ranged accuracy modifier.
	 */
	float getRangedAccuracyModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's melee damage modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's melee damage modifier.
	 */
	float getMeleeDamageModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's ranged damage modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's ranged damage modifier.
	 */
	float getRangedDamageModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's wrestling damage modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's wrestling damage modifier.
	 */
	float getWrestlingDamageModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's block power modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's block power modifier.
	 */
	float getBlockPowerModifier(Class <?extends Archetype> archetype);
	
}
