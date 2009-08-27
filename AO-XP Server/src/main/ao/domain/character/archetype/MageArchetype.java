package ao.domain.character.archetype;

import ao.domain.spell.Spell;
import ao.domain.worldobject.EquipableItem;
import ao.domain.worldobject.Staff;
import ao.domain.worldobject.Weapon;

/**
 * A mage archetype.
 * @author jsotuyod
 */
public class MageArchetype extends DefaultArchetype {
	
	MageArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
		// TODO Auto-generated constructor stub
	}

	private static final float MANA_MODIFIER = 2.8f;
	private static final int HIT_INCREMENT = 1;
	private static final int STAMINA_INCREMENT = 14;

	@Override
	public boolean canCast(Spell spell, Weapon weapon, EquipableItem ring) {
		return (spell.requiresStaff() && weapon instanceof Staff && spell.getRequiredStaffPower() < ((Staff) weapon).getMagicPower());
	}
	
	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}

	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return (int) Math.round(intelligence * MANA_MODIFIER);
	}
}
