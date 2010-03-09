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
	protected Timer timer = new Timer(true);
	
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
		 * @param repeatFor How long the event repetition will last, in milliseconds.
		 */
		public TimerTaskAdapter(TimedEvent event, long interval, long repeatFor) {
			this.event = event;
			
			// How many times the event should be executed?
			executionTimes = (int) (repeatFor / interval);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			event.execute();
			
			// Stop the event repetition once event's life time ends.
			if (--executionTimes == 0) {
				getOuterType().removeEvent(event);
			}
		}

		/**
		 * Retrieves the outer type of this inner class.
		 * @return The outer type of this inner class.
		 */
		private TimedEventsServiceImpl getOuterType() {
			return TimedEventsServiceImpl.this;
		}

		/**
		 * Retrieves the number of executions the task has to perform before completing.
		 * @return The number of executions the task has to perform before completing.
		 */
		public int getExecutionTimes() {
			return executionTimes;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ao.service.TimedEventsService#addEvent(ao.model.character.Character, ao.service.timedevents.TimedEvent, long, long, long)
	 */
	@Override
	public void addEvent(Character chara, TimedEvent event, long delay, long interval, long repeatFor) {
		Map<TimedEvent, TimerTaskAdapter> characterEvents = events.get(chara);
		
		// If the character had no previous events, create his events container (this needs some synchronization).
		if (characterEvents == null) {
			synchronized (events) {
				// In case another thread just created the Map (race condition against this method itself).
				characterEvents = events.get(chara);
				
				if (characterEvents == null) {
					characterEvents = new HashMap<TimedEvent, TimerTaskAdapter>();
					events.put(chara, characterEvents);
				}
			}
		}
		
		TimerTaskAdapter adaptedEvent = new TimerTaskAdapter(event, interval, repeatFor);
		
		synchronized (characterEvents) {
			if (events.get(chara) != characterEvents) {
				return;	// This means a concurrent call to removeCharacterEvents has occurred. "Remove" this one too.
			}
			
			// Single execution or repeated?
			if (adaptedEvent.getExecutionTimes() == 1) {
				timer.schedule(adaptedEvent, delay);
			} else {
				timer.scheduleAtFixedRate(adaptedEvent, delay, interval);
			}
			
			TimerTaskAdapter previous = characterEvents.put(event, adaptedEvent);
			
			// The same event was already filed for the character, renew the execution delay. 
			if (previous != null) {
				previous.cancel();
				timer.purge();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ao.service.TimedEventsService#addEvent(ao.model.character.Character, ao.service.timedevents.TimedEvent, long)
	 */
	@Override
	public void addEvent(Character chara, TimedEvent event, long delay) {
		addEvent(chara, event, delay, delay, delay);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.service.TimedEventsService#removeCharacterEvents(ao.model.character.Character)
	 */
	@Override
	public void removeCharacterEvents(Character chara) {
		Map<TimedEvent, TimerTaskAdapter> characterEvents = events.remove(chara);
		
		if (characterEvents == null) {
			return;
		}
		
		synchronized (characterEvents) {
			Iterator<TimedEvent> it = characterEvents.keySet().iterator();
			
			// Remove them all!
			while (it.hasNext()) {
				characterEvents.get(it.next()).cancel();
				it.remove();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ao.service.TimedEventsService#removeEvent(ao.service.timedevents.TimedEvent)
	 */
	@Override
	public void removeEvent(TimedEvent event) {
		Map<TimedEvent, TimerTaskAdapter> characterEvents = events.get(event.getCharacter());
		
		if (characterEvents != null) {
			synchronized (characterEvents) {
				TimerTaskAdapter task = characterEvents.remove(event);
				
				if (task != null) {
					task.cancel();
				}
			}
		}
	}

}
