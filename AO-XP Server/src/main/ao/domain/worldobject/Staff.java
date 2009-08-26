package ao.domain.worldobject;

import ao.domain.character.Gender;
import ao.domain.character.Race;
import ao.domain.character.Reputation;
import ao.domain.character.archetype.UserArchetype;

public class Staff implements Weapon {

	public int getMagicPower() {
		// TODO : Complete this based on staff
		return 0;
	}
	
	@Override
	public int getAttackPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEquipped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEquipped(boolean equipped) {
		// TODO Auto-generated method stub

	}

	@Override
	public int addAmount(int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canBeUsedBy(Race race, Gender gender,
			UserArchetype archetype, Reputation reputation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBuyPrice() {
		// TODO Auto-generated method stub
		return 0;
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
	public int getManufactureDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSellPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUsageDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTradeable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getGraphic() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEquippedGraphic() {
		// TODO Auto-generated method stub
		return 0;
	}

}
