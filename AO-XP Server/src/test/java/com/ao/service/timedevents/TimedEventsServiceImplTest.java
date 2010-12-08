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

package com.ao.service.timedevents;

import org.easymock.classextension.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.mock.MockFactory;
import com.ao.model.character.Character;

public class TimedEventsServiceImplTest {

	private TimedEvent event;
	private TimedEventsServiceImpl service;
	
	@Before
	public void setUp() throws Exception {
		service = new TimedEventsServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		event = null;
	}
	
	@Test
	public void testAddEventCharacterTimedEventLongLongLong() throws Exception {
		Character chara = MockFactory.mockCharacter();
		event = MockFactory.mockTimedEvent(4, chara);
		
		// This one shouldn't be executed, the next is supposed to replace it and renew the timing.
		service.addEvent(chara, event, 100L, 10L, 20L);
		
		service.addEvent(chara, event, 20L, 10L, 40L);
		
		// Wait for the events to be executed.
		Thread.sleep(70L);
		
		EasyMock.verify(event);
	}

	@Test
	public void testAddEventCharacterTimedEventLong() throws Exception {
		event = MockFactory.mockTimedEvent(1);
		
		service.addEvent(event.getCharacter(), event, 10L);
		
		// Wait for the event to be executed.
		Thread.sleep(15L);
		
		EasyMock.verify(event);
		
	}

	@Test
	public void testRemoveCharacterEvents() throws Exception {
		Character chara = MockFactory.mockCharacter();
		TimedEvent event2 = MockFactory.mockTimedEvent(0, chara);
		TimedEvent event3 = MockFactory.mockTimedEvent(0, chara);
		
		event = MockFactory.mockTimedEvent(1, chara);
		
		service.addEvent(chara, event, 50L, 50L, 300L);
		service.addEvent(chara, event2, 100L);
		service.addEvent(chara, event3, 200L);
		
		// Let the first event execute and then stop its repetition.
		Thread.sleep(55L);
		
		service.removeCharacterEvents(chara);
		EasyMock.verify(event);
	}

	@Test
	public void testRemoveEvent() {
		event = MockFactory.mockTimedEvent(0);
		
		// Schedule for execution in 50ms, but cancel before.
		service.addEvent(event.getCharacter(), event, 50L);
		service.removeEvent(event);
	}

}
