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

	private static final float MANA_MODIFIER = 2.8f;
	private static final int HIT_INCREMENT = 1;
	private static final int STAMINA_INCREMENT = 14;

	@Override
	public boolean canCast(Spell spell, Weapon weapon, EquipableItem ring) {
		return (spell.requiresStaff() && weapon instanceof Staff && spell.getRequiredStaffPower() < ((Staff) weapon).getMagicPower());
	}
	
	@Override
	public int getStaminaIncrement(int level) {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}

	@Override
	public int getManaIncrement(int intelligence) {
		return (int) Math.round(intelligence * MANA_MODIFIER);
	}
}
