package ao.domain.character;

public interface Reputation {
	
	Alignment getAlignment();
	
	/**
	 * Retrieves the bandit points.
	 * @return The reputation's bandit points.
	 */
	int getBandit();
	
	/**
	 * Adds the given points to the reputation's bandit points.
	 * @param points The points to add.
	 */
	void addToBandit(int points);
	
	/**
	 * Retrieves the assasin points.
	 * @return The reputation's assasin points.
	 */
	int getAssasin();
	
	/**
	 * Adds the given poitns to the reputation's assasin points.
	 * @param points The points to add.
	 */
	void addToAssasin(int points);
	
	/**
	 * Retrieves the noble points.
	 * @return The reputation's noble points.
	 */
	int getNoble();
	
	/**
	 * Adds the given points to the reputation's noble points.
	 * @param points The points to add.
	 */
	void addToNoblePoints(int points);
	
	/**
	 * Retrieves the bourgeois points.
	 * @return The reputation's bourgeois points.
	 */
	int getBourgeois();
	
	/**
	 * Adds the given points to the reputation's bourgeois points.
	 * @param points The points to add.
	 */
	void addToBourgeois(int points);
	
	/**
	 * Retrieves the thieff points.
	 * @return The thieff reputation's points.
	 */
	int getThieff();
	
	/**
	 * Adds the given poitns to the reputation's thieff points.
	 * @param points The points to add.
	 */
	void addToThieff(int points);
}
