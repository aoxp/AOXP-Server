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

package ao.service.timedevents;

import org.easymock.EasyMock;
import org.junit.Test;

import ao.model.character.Character;
import ao.service.TimedEventsService;

public abstract class AbstractTimedEventTest {

	TimedEvent event;
	
	@Test
	public void testRegisterLong() {
		TimedEventsService service = EasyMock.createMock(TimedEventsService.class);
		service.addEvent((Character) EasyMock.anyObject(), event, EasyMock.anyLong());
		
		EasyMock.replay(service);
		
		event.service = service;
		event.register(1L);
		
		EasyMock.verify(service);
	}

	@Test
	public void testRegisterLongLongLong() {
		TimedEventsService service = EasyMock.createMock(TimedEventsService.class);
		service.addEvent((Character) EasyMock.anyObject(), event, EasyMock.anyLong(), EasyMock.anyLong(), EasyMock.anyLong());
		
		EasyMock.replay(service);
		
		event.service = service;
		event.register(1L, 1L, 1L);
		
		EasyMock.verify(service);
	}

}
