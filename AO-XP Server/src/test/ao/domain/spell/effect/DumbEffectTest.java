package ao.domain.spell.effect;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import ao.domain.character.Character;
import ao.domain.character.UserCharacter;
import ao.domain.worldobject.WorldObject;

public class DumbEffectTest {

	private DumbEffect dumbEffect;
	
	@Before
	public void setUp() throws Exception {
		dumbEffect = new DumbEffect();
	}

	@Test
	public void testApplyCharacterCharacter() {
		Character target = EasyMock.createMock(Character.class);
		Character caster = EasyMock.createMock(Character.class);
		
		target.setDumb(true);
		
		EasyMock.replay(target, caster);
		
		dumbEffect.apply(caster, target);
		
		EasyMock.verify(target);
	}

	@Test
	public void testAppliesToCharacterCharacter() {
		Character target = EasyMock.createMock(Character.class);
		Character caster = EasyMock.createMock(Character.class);
		
		Assert.assertFalse(dumbEffect.appliesTo(caster, target));
		
		target = EasyMock.createMock(UserCharacter.class);
		
		EasyMock.expect(target.isDead()).andReturn(false).once();
		EasyMock.expect(target.isDead()).andReturn(true).once();
		EasyMock.replay(target, caster);
		
		Assert.assertTrue(dumbEffect.appliesTo(caster, target));
		Assert.assertFalse(dumbEffect.appliesTo(caster, target));
	}

	@Test
	public void testAppliesToCharacterWorldObject() {
		Character caster = EasyMock.createMock(Character.class);
		WorldObject target = EasyMock.createMock(WorldObject.class);
		
		EasyMock.replay(caster, target);
		
		Assert.assertFalse(dumbEffect.appliesTo(caster, target));
	}

}
