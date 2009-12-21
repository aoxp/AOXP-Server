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

package ao.model.character;

/**
 * Default implementation of the reputation system.
 */
public class ReputationImpl implements Reputation {

	private int assassinPoints;
	private int banditPoints;
	private int bourgeoisPoints;
	private int thiefPoints;
	private int noblePoints;
	private boolean belongsToFaction;
	
	/**
	 * Creates a new reputation instance.
	 * @param assassinPoints The assassin points.
	 * @param banditPoints The bandit points.
	 * @param bourgeoisPoints The bourgeois points.
	 * @param thieffPoints The thieff points.
	 * @param noblePoints The noble points.
	 * @param belongsToFaction Wether the character belongs to a faction or not.
	 */
	public ReputationImpl(int assassinPoints, int banditPoints, int bourgeoisPoints, int thieffPoints, int noblePoints, 
			boolean belongsToFaction) {
		this.assassinPoints = assassinPoints;
		this.banditPoints = banditPoints;
		this.bourgeoisPoints = bourgeoisPoints;
		this.thiefPoints = thieffPoints;
		this.noblePoints = noblePoints;
		this.belongsToFaction = belongsToFaction;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#addToAssassin(int)
	 */
	@Override
	public void addToAssassin(int points) {
		assassinPoints += points;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#addToBandit(int)
	 */
	@Override
	public void addToBandit(int points) {
		banditPoints += points;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#addToBourgeois(int)
	 */
	@Override
	public void addToBourgeois(int points) {
		bourgeoisPoints += points;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#addToNoble(int)
	 */
	@Override
	public void addToNoble(int points) {
		noblePoints += points;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#addToThief(int)
	 */
	@Override
	public void addToThief(int points) {
		thiefPoints += points;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#getAlignment()
	 */
	@Override
	public Alignment getAlignment() {
		long ret = bourgeoisPoints + noblePoints - assassinPoints - banditPoints - thiefPoints;
		
		return ret < 0 ? Alignment.CRIMINAL : Alignment.CITIZEN;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#getAssassin()
	 */
	@Override
	public int getAssassin() {
		return assassinPoints;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#getBandit()
	 */
	@Override
	public int getBandit() {
		return banditPoints;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#getBourgeois()
	 */
	@Override
	public int getBourgeois() {
		return bourgeoisPoints;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#getThief()
	 */
	@Override
	public int getThief() {
		return thiefPoints;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#getNoble()
	 */
	@Override
	public int getNoble() {
		return noblePoints;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#belongsToFaction()
	 */
	@Override
	public boolean belongsToFaction() {
		return belongsToFaction;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.character.Reputation#setBelongsToFaction(boolean)
	 */
	@Override
	public void setBelongsToFaction(boolean belongs) {
		belongsToFaction = belongs;
	}

}
