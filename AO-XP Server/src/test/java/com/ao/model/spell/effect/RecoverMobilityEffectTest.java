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

import org.easymock.EasyMock;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.exception.InvalidTargetException;
import com.ao.model.character.Character;
import com.ao.model.spell.effect.Effect;
import com.ao.model.spell.effect.RecoverMobilityEffect;
import com.ao.model.worldobject.WorldObject;

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
		
		// Pretend the target is immobilized
		EasyMock.expect(target.isImmobilized()).andReturn(true).once();
		EasyMock.expect(target.isDead()).andReturn(false).once();
		target.setParalyzed(false);
		target.setImmobilized(false);
		
		// Pretend the target is paralyzed
		EasyMock.expect(target.isImmobilized()).andReturn(false).once();
		EasyMock.expect(target.isParalyzed()).andReturn(true).once();
		EasyMock.expect(target.isDead()).andReturn(false).once();
		target.setParalyzed(false);
		target.setImmobilized(false);
		
		// Pretend the target is just fine
		EasyMock.expect(target.isImmobilized()).andReturn(false).once();
		EasyMock.expect(target.isParalyzed()).andReturn(false).once();
		
		// Pretend the target is dead and paralyzed
		EasyMock.expect(target.isImmobilized()).andReturn(false).once();
		EasyMock.expect(target.isParalyzed()).andReturn(true).once();
		EasyMock.expect(target.isDead()).andReturn(true).once();
		target.setParalyzed(false);
		target.setImmobilized(false);
		
		// Pretend the target is dead and immobilized
		EasyMock.expect(target.isImmobilized()).andReturn(true).once();
		EasyMock.expect(target.isParalyzed()).andReturn(false).once();
		EasyMock.expect(target.isDead()).andReturn(true).once();
		target.setParalyzed(false);
		target.setImmobilized(false);
		
		EasyMock.replay(caster, target);
		
		// An immobilized char is valid.
		Assert.assertTrue(recoverMobilityEffect.appliesTo(caster, target));
		
		// A paralyzed char is valid.
		Assert.assertTrue(recoverMobilityEffect.appliesTo(caster, target));
		
		// A non-paralyzed, non/immobilized char is invalid.
		Assert.assertFalse(recoverMobilityEffect.appliesTo(caster, target));
		
		// A paralyzed dead char is invalid.
		Assert.assertFalse(recoverMobilityEffect.appliesTo(caster, target));
		
		// A immobilized dead char is invalid.
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
		try {
			recoverMobilityEffect.apply(caster, obj);
			Assert.fail("Applying an effect for characters to a world object didn't fail.");
		} catch (InvalidTargetException e) {
			// this is ok
		}
		
		EasyMock.verify(caster, obj);
	}

}
