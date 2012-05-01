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

package com.ao.model.spell;

import com.ao.model.character.Character;
import com.ao.model.spell.effect.Effect;
import com.ao.model.worldobject.WorldObject;

/**
 * Defines a spell's model.
 * @author Juan Martín Sotuyo Dodero
 */
public class Spell {

	/**
	 * The id of the spell.
	 */
	protected int id;

	/**
	 * The effects stack.
	 */
	protected Effect[] effects;

	/**
	 * The required staff power to use the spell.
	 */
	private int requiredStaffPower;

	/**
	 * The required skill to use the spell.
	 */
	private int requiredSkill;

	/**
	 * The required mana to use the spell.
	 */
	private int requiredMana;

	/**
	 * The spell's name.
	 */
	private String name;

	/**
	 * The spell's description.
	 */
	private String description;

	/**
	 * Determines whether the effects applied to target are harmful, or not.
	 */
	private boolean negative;

	/**
	 * The spell's fx.
	 */
	private int fx;

	/**
	 * The spell's sound.
	 */
	private int sound;

	/**
	 * The pronounced words when the spell is thrown.
	 */
	private String magicWords;

	/**
	 * Instantes the spell.
	 *
	 * @param id					The spell's id.
	 * @param effects 				The spell's effect.
	 * @param requiredStaffPower	The spell's required staff power.
	 * @param requiredSkill			The spell's required skill.
	 * @param requiredMana			The spell's required mana.
	 * @param name					The spell's name.
	 * @param description			The spell's description.
	 * @param negative				Whether the spell is negative or not for the target.
	 * @param fx					The fx to use when displaying the spell.
	 * @param sound					The sound to play when casting the spell.
	 * @param magicWords			The magic words to be said by the caster when casting the spell.
	 */
	public Spell(int id, Effect[] effects, int requiredStaffPower, int requiredSkill,
			int requiredMana, String name, String description, boolean negative,
			int fx, int sound, String magicWords) {
		this.id = id;
		this.effects = effects;
		this.requiredStaffPower = requiredStaffPower;
		this.requiredSkill = requiredSkill;
		this.requiredMana = requiredMana;
		this.name = name;
		this.description = description;
		this.negative = negative;
		this.fx = fx;
		this.sound = sound;
		this.magicWords = magicWords;
	}

	/**
	 * Checks whether the spells requires a staff to be used, or not.
	 * @return True if the spells requires a staff, false otherwise.
	 */
	public boolean requiresStaff() {
		return requiredStaffPower > 0;
	}

	/**
	 * Retrieves the required staff power.
	 * @return The staff power required to use the spell.
	 */
	public int getRequiredStaffPower() {
		return requiredStaffPower;
	}

	/**
	 * Retrieves the spell's required mana points.
	 * @return The spell's required mana.
	 */
	public int getRequiredMana() {
		return requiredMana;
	}

	/**
	 * Retrieves the spell's required skills.
	 * @return The spell's required skills.
	 */
	public int getRequiredSkill() {
		return requiredSkill;
	}

	/**
	 * Checks whether the spell can be applied to the given target when is casted by the given caster.
	 * @param caster The spell's caster.
	 * @param target The spell's target.
	 * @return True if the spell applies to the target and caster, false otherwise.
	 */
	public boolean appliesTo(Character caster, Character target) {
		for (Effect effect : effects) {
			if (!effect.appliesTo(caster, target)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks whether the spell can be applied to the given target when is casted by the given caster.
	 * @param caster The spell's caster.
	 * @param target The spell's target.
	 * @return True if the spell applies to the target and caster, false otherwise.
	 */
	public boolean appliesTo(Character caster, WorldObject target) {
		for (int i = 0; i < effects.length; i++) {
			if (!effects[i].appliesTo(caster, target)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Applies the spell to the given target.
	 * @param caster The spell's caster.
	 * @param target The target to apply the spell.
	 */
	public void apply(Character caster, Character target) {
		for (int i = 0; i < effects.length; i++) {
			effects[i].apply(caster, target);
		}

		caster.addToMana(-requiredMana);
	}

	/**
	 * Applies the spell to the given target.
	 * @param caster The spell's caster.
	 * @param target The target to apply the spell.
	 */
	public void apply(Character caster, WorldObject target) {
		for (int i = 0; i < effects.length; i++) {
			effects[i].apply(caster, target);
		}

		caster.addToMana(-requiredMana);
	}

	/**
	 * Retrieves the spell's name.
	 * @return Spell's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the spell's description.
	 * @return Spell's description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Checks whether the spell is negative to target, or not.
	 * @return
	 */
	public boolean isNegative() {
		return negative;
	}

	/**
	 * Retrieves the spell's fx.
	 * @return Spell's fx.
	 */
	public int getFX() {
		return fx;
	}

	/**
	 * Retrieves the spell's sound.
	 * @return Spell's sound.
	 */
	public int getSound() {
		return sound;
	}

	/**
	 * Retrieves the spell's magic words.
	 * @return The spell's magic words.
	 */
	public String getMagicWords() {
		return magicWords;
	}

	/**
	 * Retrieves the spell's id.
	 * @return The spells id.
	 */
	public int getId() {
		return id;
	}
}
