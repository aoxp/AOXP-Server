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
