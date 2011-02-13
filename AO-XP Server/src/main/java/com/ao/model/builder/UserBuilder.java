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
import com.ao.model.map.City;
import com.ao.model.user.ConnectedUser;
import com.ao.model.user.User;
import com.ao.service.ValidatorService;



/**
 * 
 * @author mvanotti
 *
 */
public class UserBuilder implements Builder<User> {

	protected String name;
	protected String email;
	protected Race race;
	protected Gender gender;
	protected ConnectedUser user;
	protected City homeland;
	
	
	public UserBuilder withCity(City homeland) throws InvalidAttributeValueException {
		this.homeland = homeland;
		
		return this;
	}
	
	public UserBuilder withRace(Race race) throws ArrayIndexOutOfBoundsException {
		this.race = race;
		
		return this;
	}
	
	public UserBuilder withGender(Gender gender) throws ArrayIndexOutOfBoundsException {
		this.gender = gender;
		
		return this;
	}	
	
	
	public UserBuilder withName(String name) throws InvalidAttributeValueException {
		if (!ValidatorService.validCharacterName(name)) {
			throw new InvalidAttributeValueException();
		}
		
		this.name = name;
		return this;
	}
	
	public UserBuilder withEmail(String email) throws InvalidAttributeValueException {
		if (!ValidatorService.validEmail(email)) {
			throw new InvalidAttributeValueException();
		}
		
		this.email = email;
		return this;
	}
	
	
	
	
	
	@Override
	public User build() {
		// TODO Auto-generated method stub
		
		return null;
	}

}
