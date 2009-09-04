package ao.model.spell;

import ao.model.spell.effect.Effect;
import ao.model.worldobject.WorldObject;
import ao.model.character.Character;

public class Spell {

	/**
	 * The effects stack.
	 */
	private Effect[] effects;
	
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
	 * @param effects 				The spell's effect.
	 * @param requiredStaffPower	The spell's required staff power.
	 * @param requiredSkill			The spell's required skill.
	 * @param requiredMana			The spell's required mana.
	 */
	public Spell(Effect[] effects, int requiredStaffPower, int requiredSkill, int requiredMana) {
		this.effects = effects;
		this.requiredStaffPower = requiredStaffPower;
		this.requiredSkill = requiredSkill;
		this.requiredMana = requiredMana;
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
}
