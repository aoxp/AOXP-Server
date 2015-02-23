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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.ao.exception.InvalidTargetException;
import com.ao.model.character.Character;
import com.ao.model.spell.effect.Effect;
import com.ao.model.user.ConnectedUser;
import com.ao.model.user.User;
import com.ao.model.worldobject.AbstractItem;
import com.ao.model.worldobject.Item;
import com.ao.model.worldobject.WorldObject;
import com.ao.network.Connection;
import com.ao.service.timedevents.TimedEvent;

/**
 * Centralizes the common mocks creation.
 */
public class MockFactory {

	public static Item mockItem(final int id, final int initialAmount) {
		final Item item = mock(AbstractItem.class);
		when(item.getId()).thenReturn(id);
		when(item.getAmount()).thenCallRealMethod();
		when(item.addAmount(anyInt())).thenCallRealMethod();
		when(item.clone()).thenAnswer(new Answer<Item>() {
			@Override
			public Item answer(final InvocationOnMock invocation) throws Throwable {
				return mockItem(item.getId(), item.getAmount());
			}
		});

		// initialize amount
		item.addAmount(initialAmount);

		return item;
	}

	/**
	 * Creates a new Connection mock.
	 *
	 * @param user The User object to be retrieved by getUser.
	 * @param outgoing A capture on which to leave any outgoing packets sent. May be null.
	 * @return The mock.
	 */
	public static Connection mockConnection(final User user) {
		final Connection conn = mock(Connection.class);
		when(conn.getUser()).thenReturn(user);

		return conn;
	}

	/**
	 * Creates a new Connection mock.
	 *
	 * @return The mock.
	 */
	public static Connection mockConnection() {
		final ConnectedUser user = mock(ConnectedUser.class);
		final Connection connection = mockConnection(user);
		when(user.getConnection()).thenReturn(connection);

		return connection;
	}

	/**
	 * Creates a new effect mock.
	 * @param appliesToChar Whether the effect to be mocked should apply to characters.
	 * @param appliesToWorldObject Whether the effect to be mocked should apply to world objects.
	 * @return The created mock.
	 */
	public static Effect mockEffect(final boolean appliesToChar, final boolean appliesToWorldObject) {
		final Effect effect = mock(Effect.class);
		when(effect.appliesTo(any(Character.class), any(Character.class))).thenReturn(appliesToChar);
		when(effect.appliesTo(any(Character.class), any(WorldObject.class))).thenReturn(appliesToWorldObject);

		if (!appliesToChar) {
			doThrow(InvalidTargetException.class).when(effect).apply(any(Character.class), any(Character.class));
		}

		if (!appliesToWorldObject) {
			doThrow(InvalidTargetException.class).when(effect).apply(any(Character.class), any(WorldObject.class));
		}

		return effect;
	}

	/**
	 * Creates a new character mock.
	 * @return The created mock.
	 */
	public static Character mockCharacter() {
		final Character character = mock(Character.class);

		// TODO : Fill this in as needed

		return character;
	}

	/**
	 * Creates a new world object mock.
	 * @return The created mock.
	 */
	public static WorldObject mockWorldObject() {
		final WorldObject worldObject = mock(WorldObject.class);

		// TODO : Fill this in as needed

		return worldObject;
	}

	/**
	 * Creates a new TimedEvent mock.
	 * @param chara The event's Character.
	 * @return The created mock.
	 */
	public static TimedEvent mockTimedEvent(final Character chara) {
		final TimedEvent event = mock(TimedEvent.class);
		when(event.getCharacter()).thenReturn(chara);

		return event;
	}

	/**
	 * Creates a new TimedEvent mock with a default mocked Character.
	 * @param executions The amount of the executions the event would have.
	 * @return The created mock.
	 */
	public static TimedEvent mockTimedEvent() {
		return mockTimedEvent(mockCharacter());
	}
}
