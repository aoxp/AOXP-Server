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

package ao.service;

import ao.model.character.Character;
import ao.service.timedevents.TimedEvent;

/**
 * Timed events manager.
 */
public interface TimedEventsService {

	/**
	 * Schedules a new event for repeated execution.
	 * @param chara The character to apply the event.
	 * @param event The event itself.
	 * @param delay Milliseconds to wait before first event's execution.
	 * @param interval Milliseconds between each event's execution.
	 * @param repeatFor How long the event repetition will last, in milliseconds.
	 */
	void addEvent(Character chara, TimedEvent event, long delay, long interval, long repeatFor);
	
	/**
	 * Schedules a new event.
	 * @param chara The charater to apply the event.
	 * @param event The event itself.
	 * @param delay Milliseconds to wait before event's execution.
	 */
	void addEvent(Character chara, TimedEvent event, long delay);
	
	/**
	 * Removes all character's events.
	 * @param chara The character.
	 */
	void removeCharacterEvents(Character chara);
	
	/**
	 * Removes the given event.
	 * @param event The event to remove.
	 */
	void removeEvent(TimedEvent event);
}
