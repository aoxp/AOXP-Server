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

package com.ao.mock;

import org.easymock.Capture;
import org.easymock.IAnswer;
import org.easymock.classextension.EasyMock;

import com.ao.exception.InvalidTargetException;
import com.ao.model.character.Attribute;
import com.ao.model.character.Character;
import com.ao.model.spell.effect.Effect;
import com.ao.model.user.Account;
import com.ao.model.user.ConnectedUser;
import com.ao.model.user.User;
import com.ao.model.worldobject.WorldObject;
import com.ao.network.Connection;
import com.ao.network.packet.OutgoingPacket;
import com.ao.service.timedevents.TimedEvent;

/**
 * Centralizes the common mocks creation.
 */
public class MockFactory {

	/**
	 * Creates a new Connection mock.
	 *
	 * @param user The User object to be retrieved by getUser.
	 * @param outgoing A capture on which to leave any outgoing packets sent. May be null.
	 * @return The mock.
	 */
	public static Connection mockConnection(User user, Capture<? extends OutgoingPacket> outgoing) {
		Connection conn = EasyMock.createMock(Connection.class);

		EasyMock.expect(conn.getUser()).andReturn(user).anyTimes();

		if (outgoing != null) {
			conn.send(EasyMock.capture(outgoing));
		}

		conn.disconnect();

		EasyMock.replay(conn);

		return conn;
	}

	/**
	 * Creates a new Connection mock.
	 *
	 * @return The mock.
	 */
	public static Connection mockConnection() {
		return mockConnection(null);
	}

	/**
	 * Creates a new Connection mock.
	 *
	 * @param outgoing A capture on which to leave any outgoing packets sent. May be null.
	 * @return The mock.
	 */
	public static Connection mockConnection(Capture<? extends OutgoingPacket> outgoing) {
		return mockConnection(EasyMock.createMock(ConnectedUser.class), outgoing);
	}

	/**
	 * Creates a new ConnectedUser mock.
	 *
	 * @return The created mock.
	 */
	public static ConnectedUser mockConnectedUser() {
		ConnectedUser user = EasyMock.createMock(ConnectedUser.class);

		EasyMock.expect(user.getAttribute(Attribute.AGILITY)).andReturn((byte) 18).anyTimes();
		EasyMock.expect(user.getAttribute(Attribute.CHARISMA)).andReturn((byte) 18).anyTimes();
		EasyMock.expect(user.getAttribute(Attribute.CONSTITUTION)).andReturn((byte) 18).anyTimes();
		EasyMock.expect(user.getAttribute(Attribute.INTELLIGENCE)).andReturn((byte) 18).anyTimes();
		EasyMock.expect(user.getAttribute(Attribute.STRENGTH)).andReturn((byte) 18).anyTimes();

		EasyMock.expect(user.getConnection()).andReturn(mockConnection()).anyTimes();

		// Capture the received Account object in setAccount to later return it on getAccount.
		final Capture<Account> account = new Capture<Account>();

		user.setAccount(EasyMock.capture(account));

		EasyMock.expect(user.getAccount()).andAnswer(new IAnswer<Account>() {

			@Override
			public Account answer() throws Throwable {
				return account.getValue();
			}

		});

		EasyMock.replay(user);

		return user;
	}

	/**
	 * Creates a new effect mock.
	 * @param appliesToChar Whether the effect to be mocked should apply to characters.
	 * @param appliesToWorldObject Whether the effect to be mocked should apply to world objects.
	 * @return The created mock.
	 */
	public static Effect mockEffect(boolean appliesToChar, boolean appliesToWorldObject) {
		Effect effect = EasyMock.createMock(Effect.class);
		EasyMock.expect(effect.appliesTo((Character) EasyMock.anyObject(), (Character) EasyMock.anyObject())).andReturn(appliesToChar).anyTimes();
		EasyMock.expect(effect.appliesTo((Character) EasyMock.anyObject(), (WorldObject) EasyMock.anyObject())).andReturn(appliesToWorldObject).anyTimes();

		effect.apply((Character) EasyMock.anyObject(), (Character) EasyMock.anyObject());
		if (!appliesToChar) {
			EasyMock.expectLastCall().andThrow(new InvalidTargetException()).anyTimes();
		}

		effect.apply((Character) EasyMock.anyObject(), (WorldObject) EasyMock.anyObject());
		if (!appliesToWorldObject) {
			EasyMock.expectLastCall().andThrow(new InvalidTargetException()).anyTimes();
		}

		EasyMock.replay(effect);

		return effect;
	}

	/**
	 * Creates a new character mock.
	 * @return The created mock.
	 */
	public static Character mockCharacter() {
		Character character = EasyMock.createMock(Character.class);

		// TODO : Fill this in as needed

		EasyMock.replay(character);

		return character;
	}

	/**
	 * Creates a new world object mock.
	 * @return The created mock.
	 */
	public static WorldObject mockWorldObject() {
		WorldObject worldObject = EasyMock.createMock(WorldObject.class);

		// TODO : Fill this in as needed

		EasyMock.replay(worldObject);

		return worldObject;
	}

	/**
	 * Creates a new TimedEvent mock.
	 * @param executions The amount of the executions the event would have.
	 * @param chara The event's Character.
	 * @return The created mock.
	 */
	public static TimedEvent mockTimedEvent(int executions, Character chara) {
		TimedEvent event = EasyMock.createMock(TimedEvent.class);

		EasyMock.expect(event.getCharacter()).andReturn(chara).anyTimes();

		for(int i = 0; i < executions; i++) {
			event.execute();
		}

		EasyMock.replay(event);

		return event;
	}

	/**
	 * Creates a new TimedEvent mock with a default mocked Characted.
	 * @param executions The amount of the executions the event would have.
	 * @return The created mock.
	 */
	public static TimedEvent mockTimedEvent(int executions) {
		return mockTimedEvent(executions, mockCharacter());
	}
}
