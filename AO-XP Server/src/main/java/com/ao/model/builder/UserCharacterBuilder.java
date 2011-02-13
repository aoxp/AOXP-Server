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
package com.ao.model.builder;

import java.util.Map;

import javax.management.InvalidAttributeValueException;

import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.model.character.Reputation;
import com.ao.model.character.Skill;
import com.ao.model.character.UserCharacter;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.inventory.Inventory;
import com.ao.model.map.City;
import com.ao.model.map.Position;
import com.ao.model.spell.Spell;
import com.ao.model.user.ConnectedUser;
import com.ao.model.user.Guild;
import com.ao.service.ValidatorService;
import com.google.inject.internal.Preconditions;



/**
 * 
 * @author mvanotti
 *
 */
public class UserCharacterBuilder implements Builder<UserCharacter> {

	protected String name;
	protected String email;
	protected Race race;
	protected Gender gender;
	protected ConnectedUser user;
	protected City homeland;
	protected UserArchetype archetype;
	
	protected int minHp;
	protected int maxHp;
	
	private int head;
	private int body;
	private int minMana;
	private int maxMana;
	private Map<Skill, Byte> skills;
	private boolean paralyzed;
	private boolean dumbed;
	private String description;
	private boolean hidden;
	private boolean immobilized;
	private boolean invisible;
	private byte lvl;
	private boolean poisoned;
	private Guild guild;
	private long exp;
	private int maxThirstiness;
	private int maxHunger;
	private int minThirstiness;
	private int minHunger;
	private Inventory inventory;
	private Spell[] spells;
	private Reputation reputation;
	private Position position;
	
	
	public UserCharacterBuilder withCity(City homeland) {
		Preconditions.checkNotNull(homeland);
		this.homeland = homeland;
		
		return this;
	}
	
	public UserCharacterBuilder withRace(Race race){
		Preconditions.checkNotNull(race);
		
		this.race = race;
		
		return this;
	}
	
	public UserCharacterBuilder withParalyzed(boolean paralyzed) {
		this.paralyzed = paralyzed;
		
		return this;
	}
	
	public UserCharacterBuilder withPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
		
		return this;
	}
	
	public UserCharacterBuilder withImmobilized(boolean immobilized) {
		this.immobilized = immobilized;
		
		return this;
	}
	
	public UserCharacterBuilder withInvisible(boolean invisible) {
		this.invisible = invisible;
		
		return this;
	}
	
	public UserCharacterBuilder withDumbed(boolean dumbed) {
		this.dumbed = dumbed;
		
		return this;
	}
	
	public UserCharacterBuilder withHidden(boolean hidden) {
		this.hidden = hidden;
		
		return this;
	}
	
	
	public UserCharacterBuilder withLvl(byte lvl) {
		this.lvl = lvl;
		
		return this;
	}
	
	public UserCharacterBuilder withThirsthiness(int minThirstiness, int maxThirstiness) {
		this.minThirstiness = minThirstiness;
		this.maxThirstiness = maxThirstiness;
		
		return this;
	}
	
	
	public UserCharacterBuilder withHunger(int minHunger, int maxHunger) {
		this.minHunger = minHunger;
		this.maxHunger = maxHunger;
		
		return this;
	}
	
	public UserCharacterBuilder withDescription(String description) {
		Preconditions.checkArgument(ValidatorService.validCharacterName(name));
		
		this.description = description;
		return this;
		
	}
	
	public UserCharacterBuilder withGender(Gender gender)  {
		Preconditions.checkNotNull(gender);
		this.gender = gender;
		
		return this;
	}	
	
	
	public UserCharacterBuilder withName(String name) throws InvalidAttributeValueException {
		Preconditions.checkArgument(ValidatorService.validCharacterName(name));
		
		this.name = name;
		return this;
	}
	
	public UserCharacterBuilder withEmail(String email) throws InvalidAttributeValueException {
		Preconditions.checkArgument(ValidatorService.validEmail(email));
		
		this.email = email;
		return this;
	}
	
	public UserCharacterBuilder withArchetype(UserArchetype archetype){
		Preconditions.checkNotNull(archetype);
		
		this.archetype = archetype;
		
		return this;
	}
	
	public UserCharacterBuilder withHead(int head) {
		Preconditions.checkArgument(head >= 0);
		this.head = head;
		
		
		return this;
	}
	
	public UserCharacterBuilder withBody(int body) {
		Preconditions.checkArgument(body >= 0);
		this.body = body;
		
		return this;
	}
	
	public UserCharacterBuilder withConnectedUser(ConnectedUser user) {
		this.user = user;
		
		return this;
	}
	
	
	public UserCharacterBuilder withHp(int minHp, int maxHp) {
		Preconditions.checkArgument(maxHp > 0 && minHp >= 0);
		
		this.minHp = minHp;
		this.maxHp = maxHp;
		
		return this;
	}
	
	public UserCharacterBuilder withMana(int minMana, int maxMana) {
		Preconditions.checkArgument(maxMana > 0 && minMana >= 0 );
		
		this.minMana = minMana;
		this.maxMana = maxMana;
		
		return this;
	}
	
	public UserCharacterBuilder withSkills(Map<Skill, Byte> skills) throws InvalidAttributeValueException {		
		Preconditions.checkContentsNotNull(skills.values());
		
		for (Skill skill : Skill.VALUES) { 
			if (!skills.containsKey(skill)) {
				throw new InvalidAttributeValueException();
			}
		}
		
		this.skills = skills;
		
		return this;
	}
	
	public UserCharacterBuilder withGuild(Guild guild) {
		this.guild = guild;
		
		return this;
	}
	
	public UserCharacterBuilder withExp(long exp) {
		this.exp = exp;
		
		return this;
	}
	
	public UserCharacterBuilder withInventory(Inventory inventory) {
		Preconditions.checkNotNull(inventory);
		
		this.inventory = inventory;
		
		return this;
	}
	
	public UserCharacterBuilder withSpells(Spell[] spells) throws InvalidAttributeValueException {
		for (Spell spell : spells) {
			if (spell == null) {
				throw new InvalidAttributeValueException();
			}
		}
		
		this.spells = spells;
		
		return this;
	}
	
	public UserCharacterBuilder withReputation(Reputation reputation) {
		Preconditions.checkNotNull(reputation);
		
		this.reputation = reputation;
		
		return this;
	}
	
	public UserCharacterBuilder withPosition(Position position) {
		Preconditions.checkNotNull(position);
		
		this.position = position;
		
		return this;
		
	}
	
	
	@Override
	public UserCharacter build() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

}
