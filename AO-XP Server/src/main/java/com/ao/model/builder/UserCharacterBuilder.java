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

import javax.management.InvalidAttributeValueException;

import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.model.character.UserCharacter;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.map.City;
import com.ao.model.user.ConnectedUser;
import com.ao.service.ValidatorService;



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
	
	private int head;
	private int body;
	
	
	public UserCharacterBuilder withCity(City homeland) {
		this.homeland = homeland;
		
		return this;
	}
	
	public UserCharacterBuilder withRace(Race race){
		this.race = race;
		
		return this;
	}
	
	public UserCharacterBuilder withGender(Gender gender)  {
		this.gender = gender;
		
		return this;
	}	
	
	
	public UserCharacterBuilder withName(String name) throws InvalidAttributeValueException {
		if (!ValidatorService.validCharacterName(name)) {
			throw new InvalidAttributeValueException();
		}
		
		this.name = name;
		return this;
	}
	
	public UserCharacterBuilder withEmail(String email) throws InvalidAttributeValueException {
		if (!ValidatorService.validEmail(email)) {
			throw new InvalidAttributeValueException();
		}
		
		this.email = email;
		return this;
	}
	
	public UserCharacterBuilder withArchetype(UserArchetype archetype){
		this.archetype = archetype;
		
		return this;
	}
	
	public UserCharacterBuilder withHead(int head) {
		this.head = head;
		
		
		return this;
	}
	
	public UserCharacterBuilder withBody(int body) {
		this.body = body;
		
		return this;
	}
	
	public UserCharacterBuilder withConnectedUser(ConnectedUser user) {
		this.user = user;
		
		return this;
	}
	
	
	@Override
	public UserCharacter build() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

}
