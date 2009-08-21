package ao.domain.character;

import ao.domain.Gender;
import ao.domain.Race;
import ao.domain.Skill;

public interface UserCharacter extends Character {

	/**
	 * Retrieves the user's requested skill points.
	 * @param skill	The skill.
	 * @return	The amount of skill points.
	 */
	int getSkill(Skill skill);
	
	/**
	 * Adds points to the given user's skill. 
	 * @param skill		The skill to add points.
	 * @param points	The points to add.
	 */
	void addToSkill(Skill skill, byte points);
	
	/**
	 * Checks if the user is invisible, or not.
	 * @return True if the user is invisible, false otherwise.
	 */
	boolean isInvisible();
	
	/**
	 * Sets wether the user is invisible, or not.
	 * @param invisible The user's invisibility status.
	 */
	void setInvisible(boolean invisible);
	
	/**
	 * Checks if the user is hidden, or not.
	 * @return True if the user is hidden, false otherwise.
	 */
	boolean isHidden();
	
	/**
	 * Sets wether the user is hidden, or not.
	 * @param hidden The user's concealment status.
	 */
	void setHidden(boolean hidden);
	
	/**
	 * Checks if the user is doing some work.
	 * @return True if the user is working, false other wise.
	 */
	boolean isWorking();
	
	/**
	 * Sets the user to work.
	 */
	void work();
	
	/**
	 * Retrieves the user's guild name.
	 * @return The user's guild name.
	 */
	String getGuildName();
	
	/**
	 * Sets the user's guild name.
	 * @param name The guild name to be setted.
	 */
	void setGuildName(String name);
	
	/**
	 * Retrieves the user's party id.
	 * @return The user's party id if the user belongs to one, -1 otherwise.
	 */
	int getPartyId();
	
	/**
	 * Sets the user's party id.
	 * @param id The party id to be setted.
	 */
	void setPartyId(int id);
	
	/**
	 * Retrieves the user's nickname.
	 * @return The user's name-
	 */
	String getName();
	
	/**
	 * Retrieves the user's display name (includes status, guild, etc).
	 * @return The user's display name.
	 */
	String getDisplayName();
	
	/**
	 * Retrieves the user's race.
	 * @return The user's race.
	 */
	Race getRace();
	
	/**
	 * Retrieves the user's gender.
	 * @return The user's gender.
	 */
	Gender getGender();
}