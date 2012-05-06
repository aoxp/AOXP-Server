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

package com.ao.model.character;

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
	 * Retrieves the assassin points.
	 * @return The reputation's assassin points.
	 */
	int getAssassin();
	
	/**
	 * Adds the given points to the reputation's assassin points.
	 * @param points The points to add.
	 */
	void addToAssassin(int points);
	
	/**
	 * Retrieves the noble points.
	 * @return The reputation's noble points.
	 */
	int getNoble();
	
	/**
	 * Adds the given points to the reputation's noble points.
	 * @param points The points to add.
	 */
	void addToNoble(int points);
	
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
	 * Retrieves the thief points.
	 * @return The thief reputation's points.
	 */
	int getThief();
	
	/**
	 * Adds the given points to the reputation's thief points.
	 * @param points The points to add.
	 */
	void addToThief(int points);
	
	/**
	 * Checks if the reputation belongs to any faction.
	 * @return True if the reputation belongs to a faction, false otherwise.
	 */
	boolean belongsToFaction();
	
	/**
	 * Sets whether the reputation belongs to a faction, or not.
	 * @param belongs Determines if the reputation belongs to a faction.
	 */
	void setBelongsToFaction(boolean belongs);

}
