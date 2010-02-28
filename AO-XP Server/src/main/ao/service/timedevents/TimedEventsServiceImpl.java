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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import ao.model.character.Character;
import ao.service.TimedEventsService;

public class TimedEventsServiceImpl implements TimedEventsService {

	protected Map<Character, Map<TimedEvent, TimerTaskAdapter>> events = new ConcurrentHashMap<Character, Map<TimedEvent, TimerTaskAdapter>>();
	protected Timer timer = new Timer();
	
	/**
	 * Adapts TimedEvent as TimerTask to be used by Timer.
	 */
	protected class TimerTaskAdapter extends TimerTask {
		
		protected final TimedEvent event;
		protected int executionTimes;
		
		/**
		 * Creates a new adapter.
		 * 
		 * @param event The event to adapt.
		 * @param interval Delay in milliseconds to wait before event's execution.
		 * @param repeatFor
		 */
		public TimerTaskAdapter(TimedEvent event, long interval, long repeatFor) {
			this.event = event;
			
			// How many times the event should be executed?
			executionTimes = repeatFor != -1 ? (int) (repeatFor / interval) : 1;
		}

		@Override
		public void run() {
			event.execute();
			
			// Stop the event repetition once event's life time ends.
			if (--executionTimes == 0) {
				getOuterType().removeEvent(event);
			}
		}

		private TimedEventsServiceImpl getOuterType() {
			return TimedEventsServiceImpl.this;
		}
	}

	@Override
	public void addEvent(Character chara, TimedEvent event, long delay, long interval, long repeatFor) {
		Map<TimedEvent, TimerTaskAdapter> characterEvents = events.get(chara);
		
		// If the character had no previous events, create his events container.
		if (characterEvents == null) {
			characterEvents = new HashMap<TimedEvent, TimerTaskAdapter>();
			events.put(chara, characterEvents);
		}
		
		TimerTaskAdapter adaptedEvent = new TimerTaskAdapter(event, interval, repeatFor);
		TimerTaskAdapter previous = characterEvents.put(event, adaptedEvent);
		
		// The same event was already filed for the character, renew the execution delay. 
		if (previous != null) {
			previous.cancel();
			timer.purge();
		}
		
		// Single execution or repeated?
		if (repeatFor == -1) {
			timer.schedule(adaptedEvent, delay);
		} else {
			timer.scheduleAtFixedRate(adaptedEvent, delay, interval);
		}
	}

	@Override
	public void addEvent(Character chara, TimedEvent event, long delay) {
		addEvent(chara, event, delay, -1, -1);
	}
	
	@Override
	public void removeCharacterEvents(Character chara) {
		Map<TimedEvent, TimerTaskAdapter> characterEvents = events.get(chara);
		Iterator<TimedEvent> it = characterEvents.keySet().iterator();
		
		// Remove them all!
		while(it.hasNext()) {
			characterEvents.get(it.next()).cancel();
			it.remove();
		}
	}

	@Override
	public void removeEvent(TimedEvent event) {
		Map<TimedEvent, TimerTaskAdapter> characterEvents = events.get(event.getCharacter());
		
		characterEvents.remove(event).cancel();
	}

}
