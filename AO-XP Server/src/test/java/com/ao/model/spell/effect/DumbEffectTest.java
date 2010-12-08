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

package com.ao.model.spell.effect;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.ao.exception.InvalidTargetException;
import com.ao.model.character.Character;
import com.ao.model.character.UserCharacter;
import com.ao.model.spell.effect.DumbEffect;
import com.ao.model.worldobject.WorldObject;

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

	@Test
	public void testApplyCharacterWorldObject() {
		WorldObject obj = EasyMock.createMock(WorldObject.class);
		Character caster = EasyMock.createMock(Character.class);
		
		EasyMock.replay(obj, caster);
		
		// Should do nothing....
		try {
			dumbEffect.apply(caster, obj);
			Assert.fail("Applying an effect for characters to a world object didn't fail.");
		} catch (InvalidTargetException e) {
			// this is ok
		}
		
		EasyMock.verify(caster, obj);
	}

}
