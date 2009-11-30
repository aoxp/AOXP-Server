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

package ao.model.spell.effect;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.character.NPCCharacter;
import ao.model.character.UserCharacter;
import ao.model.spell.effect.Effect;
import ao.model.spell.effect.InvisibilityEffect;
import ao.model.worldobject.WorldObject;

public class InvisibilityEffectTest {

	private Effect invisibilityEffect;
	
	@Before
	public void setUp() throws Exception {
		this.invisibilityEffect = new InvisibilityEffect();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApplyCharacterCharacter() {
		Character caster = EasyMock.createMock(Character.class);
		Character target = EasyMock.createMock(Character.class);
		target.setInvisible(true);
		
		EasyMock.replay(caster, target);
		
		invisibilityEffect.apply(caster, target);
		
		EasyMock.verify(caster, target);
	}

	@Test
	public void testAppliesToCharacterCharacter() {
		Character caster = EasyMock.createMock(Character.class);
		UserCharacter userTarget = EasyMock.createMock(UserCharacter.class);
		NPCCharacter target = EasyMock.createMock(NPCCharacter.class);
		
		// First fake it's dead, then pretend it's alive.
		EasyMock.expect(userTarget.isDead()).andReturn(true).once();
		EasyMock.expect(userTarget.isDead()).andReturn(false).once();
		
		EasyMock.replay(caster, target, userTarget);
		
		// Test invalid target
		Assert.assertFalse(invisibilityEffect.appliesTo(caster, target));
		
		// Test dead target
		Assert.assertFalse(invisibilityEffect.appliesTo(caster, userTarget));
		
		// Test alive target
		Assert.assertTrue(invisibilityEffect.appliesTo(caster, userTarget));
	}

	@Test
	public void testAppliesToCharacterWorldObject() {
		WorldObject obj = EasyMock.createMock(WorldObject.class);
		Character caster = EasyMock.createMock(Character.class);
		
		EasyMock.replay(obj, caster);
		
		Assert.assertFalse(invisibilityEffect.appliesTo(caster, obj));
	}

	@Test
	public void testApplyCharacterWorldObject() {
		WorldObject obj = EasyMock.createMock(WorldObject.class);
		Character caster = EasyMock.createMock(Character.class);
		
		EasyMock.replay(obj, caster);
		
		// This should do nothing
		invisibilityEffect.apply(caster, obj);
		
		EasyMock.verify(caster, obj);
	}

}
