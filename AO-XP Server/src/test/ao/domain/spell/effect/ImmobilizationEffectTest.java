package ao.domain.spell.effect;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ao.domain.character.Character;
import ao.domain.worldobject.WorldObject;

public class ImmobilizationEffectTest {

	private Effect immobilizationEffect;
	
	@Before
	public void setUp() throws Exception {
		this.immobilizationEffect = new ImmobilizationEffect();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApplyCharacterCharacter() {
		Character caster = EasyMock.createMock(Character.class);
		Character target = EasyMock.createMock(Character.class);
		
		target.setImmobilized(true);
		
		EasyMock.replay(caster, target);
		
		immobilizationEffect.apply(caster, target);
		
		EasyMock.verify(caster, target);
	}

	@Test
	public void testAppliesToCharacterCharacter() {
		Character caster = EasyMock.createMock(Character.class);
		Character target = EasyMock.createMock(Character.class);
		
		// Pretend the target is dead first, then alive.
		EasyMock.expect(target.isDead()).andReturn(true).once();
		EasyMock.expect(target.isDead()).andReturn(false).once();
		
		EasyMock.replay(caster, target);
		
		// Paralyzing a dead char is invalid.
		Assert.assertFalse(immobilizationEffect.appliesTo(caster, target));
		
		/// Paralyzing an alive char is valid.
		Assert.assertTrue(immobilizationEffect.appliesTo(caster, target));
	}

	@Test
	public void testAppliesToCharacterWorldObject() {
		WorldObject obj = EasyMock.createMock(WorldObject.class);
		Character caster = EasyMock.createMock(Character.class);
		
		EasyMock.replay(obj, caster);
		
		// Should always false, no matter what
		Assert.assertFalse(immobilizationEffect.appliesTo(caster, obj));
	}

	@Test
	public void testApplyCharacterWorldObject() {
		WorldObject obj = EasyMock.createMock(WorldObject.class);
		Character caster = EasyMock.createMock(Character.class);
		
		EasyMock.replay(obj, caster);
		
		// Should do nothing....
		immobilizationEffect.apply(caster, obj);
		
		EasyMock.verify(caster, obj);
	}

}
