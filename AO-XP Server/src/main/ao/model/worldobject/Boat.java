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

package ao.model.worldobject;

import ao.model.character.Gender;
import ao.model.character.Race;
import ao.model.character.Reputation;
import ao.model.character.archetype.UserArchetype;

public class Boat implements Item {

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getManufactureDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getUsageDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAmount(int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canBeUsedBy(Race race, Gender gender, UserArchetype archetype, Reputation reputation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Item clone(){
		// TODO Marco-generated method stub
		
		return null;
	}

	@Override
	public int getBuyPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSellPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTradeable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getGraphic() {
		// TODO Auto-generated method stub
		return 0;
	}

}
