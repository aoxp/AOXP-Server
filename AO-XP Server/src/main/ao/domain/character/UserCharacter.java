package ao.domain.character;

public interface UserCharacter extends Character {

	/**
	 * Retrieves the user's requested skill points.
	 * @param skill	The skill.
	 * @return	The amount of skill points.
	 */
	public int getSkill(int skill);
	
	/**
	 * Checks if the user is a citizen, or not.
	 * @return True if the user is a citizen, false otherwise.
	 */
	public boolean isCitizen();
	
	/**
	 * Checks if the user is invisible, or not.
	 * @return True if the user is invisible, false otherwise.
	 */
	public boolean isInvisible();
	
	/**
	 * Checks if the user is doing some work.
	 * @return True if the user is working, false other wise.
	 */
	public boolean isWorking();
	
	/**
	 * Retrieves the user's guild name.
	 * @return The user's guild name.
	 */
	public String getGuildName();
	
	/**
	 * Retrieves the user's party id.
	 * @return The user's party id if the user belongs to one, -1 otherwise.
	 */
	public int getPartyId();
	
}
