package ao.model.spell.effect;

import org.easymock.EasyMock;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.spell.effect.Effect;
import ao.model.spell.effect.RecoverMobilityEffect;
import ao.model.worldobject.WorldObject;

public class RecoverMobilityEffectTest {

	private Effect recoverMobilityEffect;
	
	@Before
	public void setUp() throws Exception {
		this.recoverMobilityEffect = new RecoverMobilityEffect();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApplyCharacterCharacter() {
		Character caster = EasyMock.createMock(Character.class);
		Character target = EasyMock.createMock(Character.class);
		
		target.setImmobilized(false);
		target.setParalyzed(false);
		
		EasyMock.replay(caster, target);
		
		recoverMobilityEffect.apply(caster, target);
		
		EasyMock.verify(caster, target);
	}

	@Test
	public void testAppliesToCharacterCharacter() {
		Character caster = EasyMock.createMock(Character.class);
		Character target = EasyMock.createMock(Character.class);
		
		// Pretend the target is immobilized first, then just paralyzed, finally none.
		EasyMock.expect(target.isImmobilized()).andReturn(true).once();
		EasyMock.expect(target.isDead()).andReturn(false).once();
		target.setParalyzed(false);
		target.setImmobilized(false);
		
		EasyMock.expect(target.isImmobilized()).andReturn(false).once();
		EasyMock.expect(target.isParalyzed()).andReturn(true).once();
		EasyMock.expect(target.isDead()).andReturn(false).once();
		target.setParalyzed(false);
		target.setImmobilized(false);
		
		EasyMock.expect(target.isImmobilized()).andReturn(false).once();
		EasyMock.expect(target.isParalyzed()).andReturn(false).once();
		
		EasyMock.replay(caster, target);
		
		// An immobilized char is valid.
		Assert.assertTrue(recoverMobilityEffect.appliesTo(caster, target));
		
		// A paralyzed char is valid.
		Assert.assertTrue(recoverMobilityEffect.appliesTo(caster, target));
		
		// A non-paralyzed, non/immobilized char is invalid.
		Assert.assertFalse(recoverMobilityEffect.appliesTo(caster, target));
	}

	@Test
	public void testAppliesToCharacterWorldObject() {
		WorldObject obj = EasyMock.createMock(WorldObject.class);
		Character caster = EasyMock.createMock(Character.class);
		
		EasyMock.replay(obj, caster);
		
		// Should always false, no matter what
		Assert.assertFalse(recoverMobilityEffect.appliesTo(caster, obj));
	}

	@Test
	public void testApplyCharacterWorldObject() {
		WorldObject obj = EasyMock.createMock(WorldObject.class);
		Character caster = EasyMock.createMock(Character.class);
		
		EasyMock.replay(obj, caster);
		
		// Should do nothing....
		recoverMobilityEffect.apply(caster, obj);
		
		EasyMock.verify(caster, obj);
	}

}
