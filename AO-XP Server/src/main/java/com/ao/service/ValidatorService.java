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

package com.ao.service;

import org.apache.commons.validator.GenericValidator;

/**
 * Provides validation services.
 */
public class ValidatorService {

	private static final int CHARACTER_NAME_MIN_LENGTH = 1;
	private static final int CHARACTER_NAME_MAX_LENGTH = 30;
	private static final String CHARACTER_NAME_REGEXP = "^[A-Za-z][A-Za-z ]*";

	/**
	 * Checks if the given character name is valid, or not.
	 * 
	 * @param name The character name.
	 * @return True if the name is valid, false otherwise.
	 */
	public static boolean validCharacterName(String name) {
		//TODO: Take this to the security modules.
		boolean res = true;
		
		res = res && GenericValidator.matchRegexp(name, CHARACTER_NAME_REGEXP);
		res = res && GenericValidator.isInRange(name.length(), CHARACTER_NAME_MIN_LENGTH, CHARACTER_NAME_MAX_LENGTH);
		
		return res;
	}
	
	/**
	 * Checks if the given email address is valid, or not.
	 * @param email The email address.
	 * @return True if the email is valid, false otherwise.
	 */
	public static boolean validEmail(String email) {
		return GenericValidator.isEmail(email);
	}
}
