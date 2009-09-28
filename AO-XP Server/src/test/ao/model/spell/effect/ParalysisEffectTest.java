package ao.model.spell.effect;

import org.easymock.EasyMock;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.spell.effect.Effect;
import ao.model.spell.effect.ParalysisEffect;
import ao.model.worldobject.WorldObject;

public class ParalysisEffectTest {

	private Effect paralysisEffect;
	
	@Before
	public void setUp() throws Exception {
		this.paralysisEffect = new ParalysisEffect();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApplyCharacterCharacter() {
		Character caster = EasyMock.createMock(Character.class);
		Character target = EasyMock.createMock(Character.class);
		
		target.setParalyzed(true);
		
		EasyMock.replay(caster, target);
		
		paralysisEffect.apply(caster, target);
		
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
		Assert.assertFalse(paralysisEffect.appliesTo(caster, target));
		
		/// Paralyzing an alive char is valid.
		Assert.assertTrue(paralysisEffect.appliesTo(caster, target));
	}

	@Test
	public void testAppliesToCharacterWorldObject() {
		WorldObject obj = EasyMock.createMock(WorldObject.class);
		Character caster = EasyMock.createMock(Character.class);
		
		EasyMock.replay(obj, caster);
		
		// Should always false, no matter what
		Assert.assertFalse(paralysisEffect.appliesTo(caster, obj));
	}

	@Test
	public void testApplyCharacterWorldObject() {
		WorldObject obj = EasyMock.createMock(WorldObject.class);
		Character caster = EasyMock.createMock(Character.class);
		
		EasyMock.replay(obj, caster);
		
		// Should do nothing....
		paralysisEffect.apply(caster, obj);
		
		EasyMock.verify(caster, obj);
	}

}