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
