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

package com.ao.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses a comma-separated list of numbers and retrieves it.
 * The list also can contain numerical ranges indicated with dashes which also
 * separates the starting and ending number of the numerical range, both inclusive.
 * e.g:
 * The following string: "1,2,3-5,6-9,10" would produce the following return:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 */
public class RangeParser {

	private static final String CONFIG_VALUES_DELIMITER = ",";
	private static final String CONFIG_RANGE_INDICATOR = "-";
	
	/**
	 * Parses the string in to the appropriate extensive list of integers.
	 * @param value The string value to be parsed.
	 * @return The list of numbers extracted from the list.
	 */
	public static List<Integer> parseIntegers(String value) {
		String[] splittedValues = value.split(CONFIG_VALUES_DELIMITER);
		List<Integer> ret = new ArrayList<Integer>();
		
		for(String val : splittedValues) {
			if (val.contains(CONFIG_RANGE_INDICATOR)) {
				String[] rangePoints = val.split(CONFIG_RANGE_INDICATOR);
				int from = Integer.valueOf(rangePoints[0]);
				int to = Integer.valueOf(rangePoints[1]);
				
				for (int i = from; i <= to; i++) {
					ret.add(i);
				}
				
			} else {
				ret.add(Integer.valueOf(val));
			}
		}
		
		return ret;
	}
	
	/**
	 * Parses the string in to the appropriate extensive list of short.
	 * @param value The string value to be parsed.
	 * @return The list of numbers extracted from the list.
	 */
	public static List<Short> parseShorts(String value) {
		String[] splittedValues = value.split(CONFIG_VALUES_DELIMITER);
		List<Short> ret = new ArrayList<Short>();
		
		for(String val : splittedValues) {
			if (val.contains(CONFIG_RANGE_INDICATOR)) {
				String[] rangePoints = val.split(CONFIG_RANGE_INDICATOR);
				short from = Short.valueOf(rangePoints[0]);
				short to = Short.valueOf(rangePoints[1]);
				
				for (short i = from; i <= to; i++) {
					ret.add(i);
				}
				
			} else {
				ret.add(Short.valueOf(val));
			}
		}
		
		return ret;
	}
}
