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

import com.ao.model.inventory.Inventory;
import com.ao.model.map.Heading;
import com.ao.model.map.Position;
import com.ao.model.spell.Spell;
import com.ao.model.worldobject.Accessory;
import com.ao.model.worldobject.Armor;
import com.ao.model.worldobject.EquipableItem;
import com.ao.model.worldobject.Helmet;
import com.ao.model.worldobject.Item;
import com.ao.model.worldobject.Shield;
import com.ao.model.worldobject.Weapon;
import com.ao.model.worldobject.WorldObject;

public interface Character {

	/**
	 * Retrieves the character's hit points.
	 * @return The character's hit points.
	 */
	int getHitPoints();

	/**
	 * Adds (or subtracts if the given number is negative) points to the character's hit points.
	 * @param points	The points to add.
	 */
	void addToHitPoints(int points);

	/**
	 * Retrieves the maximum character's hit points.
	 * @return The maximum caracter's hit points.
	 */
	int getMaxHitPoints();

	/**
	 * Adds (or subtracts if the given number is negative) points to character's max hit points.
	 * @param points 	The points to add.
	 */
	void addToMaxHitPoints(int points);

	/**
	 * Retrieves the character's mana points.
	 * @return The caracter's mana points.
	 */
	int getMana();

	/**
	 * Adds (or subtracts if the given number is negative) points to the character's mana points.
	 * @param points The points to add.
	 */
	void addToMana(int points);

	/**
	 * Retrieves the maximum character's mana points.
	 * @return The maximum character's mana points.
	 */
	int getMaxMana();

	/**
	 * Adds (or subtracts if the given number is negative) points to the character's maximum mana points.
	 * @param points The points to add.
	 */
	void addToMaxMana(int points);

	/**
	 * Retrieves the character's thirstiness.
	 * @return The character's thirstiness points.
	 */
	int getThirstiness();

	/**
	 * Adds (or subtracts if the given number is negative) points to the character's thirstiness.
	 * @param points The points to add.
	 */
	void addToThirstiness(int points);

	/**
	 * Retrieves the character's hunger points.
	 * @return The character's hunger points.
	 */
	int getHunger();

	/**
	 * Adds (or subtracts if the given number is negative) points to the character's hunger.
	 * @param points
	 */
	void addToHunger(int points);

	/**
	 * Checks if the character is paralyzed.
	 * @return True if the character is paralyzed, false otherwise.
	 */
	boolean isParalyzed();

	/**
	 * Sets whether the character is paralyzed, or not.
	 * @param paralyzed The character's paralysis status.
	 */
	void setParalyzed(boolean paralyzed);

	/**
	 * Checks if the character is immobilized.
	 * @return True if the character is paralyzed, false otherwise.
	 */
	boolean isImmobilized();

	/**
	 * Sets whether the character is immobilized, or not.
	 * @param immobilized The character's immobilization status.
	 */
	void setImmobilized(boolean immobilized);

	/**
	 * Retrieves the character's position.
	 * @return The character's position.
	 */
	Position getPosition();

	/**
	 * Sets the character's position.
	 * @param pos The new character's position.
	 */
	void setPosition(Position pos);

	/**
	 * Uses the given item (must be in the character's inventory).
	 * @param item The item to use.
	 */
	void use(Item item);

	/**
	 * Equips the given item (must be in the character's inventory).
	 * @param item The item to use.
	 */
	void equip(EquipableItem item);

	/**
	 * Retrieves the character's total attack power (considering items and effects).
	 * @return The character's total attack power (considering items and effects).
	 */
	int getAttackPower();

	/**
	 * Retrieves the character's total defense power (considering items and effects).
	 * @return The character's total defense power (considering items and effects).
	 */
	int getDefensePower();

	/**
	 * Retrieves a character's inventory.
	 * @return The character's inventory.
	 */
	Inventory getInventory();

	/**
	 * Retrieves the character's reputation.
	 * @return The character's reputation.
	 */
	Reputation getReputation();

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
	 * Retrieves the Character's description.
	 * @return The Character's description.
	 */
	String getDescription();

	/**
	 * Retrieves the character's body.
	 * @return The character's body.
	 */
	int getBody();

	/**
	 * Retrieves the character's head.
	 * @return The character's head.
	 */
	int getHead();

	/**
	 * Retrieves the original character's body.
	 * @return The character's body.
	 */
	int getOriginalBody();

	/**
	 * Retrieves the original character's head.
	 * @return The character's head.
	 */
	int getOriginalHead();

	/**
	 * Sets the character's head
	 * @param head the new head.
	 */
	void setHead(int head);

	/**
	 * Sets the character's body
	 * @param body the new body
	 */
	void setBody(int body);

	/**
	 * Retrieves the character's poisoning status.
	 * @return The character's poisoning status.
	 */
	boolean isPoisoned();

	/**
	 * Sets whether the character is poisoned, or not.
	 * @param poisoned The new character's poisoning status.
	 */
	void setPoisoned(boolean poisoned);

	/**
	 * Retrieves the character's level.
	 * @return The character's level.
	 */
	byte getLevel();

	/**
	 * Retrieves the character's experience.
	 * @return The character's experience.
	 */
	int getExperience();

	/**
	 * Retrieves the experience needed to level up
	 * @return the experience needed to level up.
	 */
	int getExperienceForLeveUp();

	/**
	 * Adds experience to the character's current experience. Will level up if possible.
	 * @param exp the experience to add.
	 */
	void addToExperience(int experience);

	/**
	 * Retrieves the character's status.
	 * @return True if the character is dead, false otherwise.
	 */
	boolean isDead();

	/**
	 * Sets whether the character is dead, or not.
	 * @param dead The character's status.
	 */
	void setDead(boolean dead);

	/**
	 * Checks if the user is mimetized, or not.
	 * @return True if the user is mimetized, false otherwise.
	 */
	boolean isMimetized();

	/**
	 * Sets whether the character is mimetized, or not.
	 * @param mimetized The character's mimetized status.
	 */
	void setMimetized(boolean mimetized);

	/**
	 * Checks if the user is invisible, or not.
	 * @return True if the user is invisible, false otherwise.
	 */
	boolean isInvisible();

	/**
	 * Sets whether the user is invisible, or not.
	 * @param invisible The user's invisibility status.
	 */
	void setInvisible(boolean invisible);

	/**
	 * Checks if the user is hidden, or not.
	 * @return True if the user is hidden, false otherwise.
	 */
	boolean isHidden();

	/**
	 * Sets whether the user is hidden, or not.
	 * @param hidden The user's concealment status.
	 */
	void setHidden(boolean hidden);

	/**
	 * Checks if the user is dumb, or not.
	 * @return True if the user is dumb, false otherwise.
	 */
	boolean isDumb();

	/**
	 * Sets whether the user is dumb, or not.
	 * @param dumb The new user's dumb status.
	 */
	void setDumb(boolean dumb);

	/**
	 * Checks if the character can walk in the water.
	 * @return True if the character can move in water, false otherwise.
	 */
	boolean canWalkInWater();

	/**
	 * Checks if the character can walk in the earth.
	 * @return True if the character can walk in the earth.
	 */
	boolean canWalkInEarth();

	/**
	 * Performs an attack on the given character.
	 * @param character The target to attack.
	 */
	void attack(Character character);

	/**
	 * Moves the character on the given direction.
	 * @param heading The heading in which to move.
	 */
	void moveTo(Heading heading);

	/**
	 * Casts a spell on the given character.
	 * @param spell The spell to cast.
	 * @param target The character on which to cast the spell.
	 */
	void cast(Spell spell, Character target);

	/**
	 * Casts a spell on the given world object.
	 * @param spell The spell to cast.
	 * @param object The object on which to cast the spell.
	 */
	void cast(Spell spell, WorldObject object);

	/**
	 * Retrieves the character's spell list.
	 * @return The character's spells.
	 */
	Spell[] getSpells();

	/**
	 * Adds (or subtracts if the given number is negative) points to the character's agility.
	 * @param points	The points to add.
	 * @param duration		The time for which the effect is valid.
	 */
	void addToAgility(int points, int duration);

	/**
	 * Adds (or subtracts if the given number is negative) points to the character's strength.
	 * @param points	The points to add.
	 * @param duration		The time for which the effect is valid.
	 */
	void addToStrength(int points, int duration);

	/**
	 * Adds the given spell.
	 * @param spell Spell to be added
	 */
	void addSpell(Spell spell);

	/**
	 * Retrieves the character's money.
	 * @return The character's money.
	 */
	int getMoney();

	/**
	 * Increases the amount of money.
	 * @param The amount of gold to add.
	 */
	void addMoney(int amount);

	/**
	 * Retrieves the character's heading.
	 * @return The character's heading.
	 */
	Heading getHeading();

	/**
	 * Sets the character heading.
	 * @param heading The heading.
	 */
	void setHeading(Heading heading);

	/**
	 * Retrieves the character's weapon.
	 * @return The character's weapon.
	 */
	Weapon getWeapon();

    /**
     * Sets the character weapon.
     * @param weapon The character's weapon.
     */
    void setWeapon(Weapon weapon);

	/**
	 * Retrieves the character's weapon
	 * @return the character's weapon
	 */
	Helmet getHelmet();

    /**
     * Sets the character helmet.
     * @param weapon The character's helmet.
     */
    void setHelmet(Helmet helmet);

	/**
	 * Retrieves the character's shield.
	 * @return The character's shield.
	 */
	Shield getShield();

    /**
     * Sets the character shield.
     * @param weapon The character's shield.
     */
    void setShield(Shield shield);

	/**
	 * Retrieves the character's armor
	 * @return the character's armor
	 */
	Armor getArmor();
	
	/**
     * Sets the character's armor.
     * @param weapon The character's armor.
     */
	void setArmor(Armor armor);

	/**
	 * Retrieves the character's accessory
	 * @return the character's accessory
	 */
	Accessory getAccessory();
	
	/**
     * Sets the character's accessory.
     * @param weapon The character's accessory.
     */
	void setAccessory(Accessory accessory);

	/**
	 * Retrieves the character's privileges.
	 * @return The character's privileges.
	 */
	Privileges getPrivileges();

	/**
	 * Sets the character privileges.
	 * @param weapon The character's privileges.
	 */
	void setPrivileges(Privileges privileges);

	/**
	 * Retrieves the character's nick's color.
	 * @return The character's nick's color.
	 */
	int getNickColor();

	/**
	 * Sets the character nick's color.
	 * @param weapon The character's nick's color.
	 */
	void setNickColor(int colorIndex);

	/**
	 * Retrieves the character's fx.
	 * @return The character's fx.
	 */
	Fx getFx();

	/**
	 * Sets the character's fx.
	 * @param weapon The character's fx.
	 */
	void setFx(Fx fx);

	/**
	 * Retrieves the character's index.
	 * @return The character's index.
	 */
	int getCharIndex();

	/**
	 * Sets the character's index.
	 * @param weapon The character's index.
	 */
	void setCharIndex(int index);

}
