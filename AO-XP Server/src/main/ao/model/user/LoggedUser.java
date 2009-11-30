package ao.model.user;

import ao.model.character.Character;
import ao.model.character.Gender;
import ao.model.character.Race;
import ao.model.character.Reputation;
import ao.model.character.Skill;
import ao.model.character.UserCharacter;
import ao.model.character.archetype.Archetype;
import ao.model.inventory.Inventory;
import ao.model.map.Heading;
import ao.model.map.Position;
import ao.model.spell.Spell;
import ao.model.worldobject.EquipableItem;
import ao.model.worldobject.Item;
import ao.model.worldobject.WorldObject;

/**
 * Defines a logged user.
 */
public class LoggedUser implements User, UserCharacter {
	
	
	private static final int MAX_THIRSTINESS = 100;
	private static final int MAX_HUNGER = 100;
	
	private Reputation reputation; //TODO: need to be instanced in the constructor.
	private Inventory inventory; //TODO: need to be instanced in the constructor.
	private Race race;
	private Gender gender;
	private Archetype archetype;

	
	/* UserFlags */
	private Boolean poisoned = false;
	private Boolean paralyzed = false;
	private Boolean immobilized = false;
	private Boolean invisible = false;
	private Boolean mimetized = false;
	private Boolean dumbed = false;
	private Boolean hidden = false;
	
	private int maxMana;
	private int maxHitPoints;
	private int mana;
	private int hitpoints; 
	private int thirstiness;
	private int hunger;
	private Byte lvl;
	private String name;
	private String description;
	

	public LoggedUser() {

	}
	
	@Override
	public void addToSkill(Skill skill, byte points) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Archetype getArchetype() {
		return archetype;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

	@Override
	public String getGuildName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPartyId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Race getRace() {
		return race;
	}

	@Override
	public int getSkill(Skill skill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isWorking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGuildName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPartyId(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void work() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToExperience(int experience) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToHitPoints(int points) {
		hitpoints += points;		//TODO: Check for overflows and underflows
		
		if (hitpoints > maxHitPoints) {
			hitpoints = maxHitPoints;
		}
	}

	@Override
	public void addToHunger(int points) {
		hunger += points;			//TODO: Check for overflows and underflows
		
		if (hunger > MAX_HUNGER) {
			hunger = MAX_HUNGER;
		}
	}

	@Override
	public void addToMana(int points) {
		mana += points;				 //TODO: Check for overflows and underflows
		if (mana > maxMana) {
			mana = maxMana;
		}
	}

	@Override
	public void addToMaxHitPoints(int points) {
		maxHitPoints += points; 	//TODO: Check for overflows and underflows
	}

	@Override
	public void addToMaxMana(int points) {
		maxMana += points; 			//TODO: Check for overflows and underflows
	}

	@Override
	public void addToThirstiness(int points) {
		thirstiness += points; 		//TODO: Check for overflows and underflows
		
		if (thirstiness > MAX_THIRSTINESS) {
			thirstiness = MAX_THIRSTINESS;
		}
			
	}

	@Override
	public void attack(Character character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canWalkInEarth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canWalkInWater() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cast(Spell spell, Character target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cast(Spell spell, WorldObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void equip(EquipableItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAttackPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBody() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefensePower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExperience() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getExperienceForLeveUp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHead() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHitPoints() {
		// TODO Auto-generated method stub
		return hitpoints;
	}

	@Override
	public int getHunger() {
		// TODO Auto-generated method stub
		return hunger;
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}

	@Override
	public byte getLevel() {
		// TODO Auto-generated method stub
		return lvl;
	}

	@Override
	public int getMana() {
		// TODO Auto-generated method stub
		return mana;
	}

	@Override
	public int getMaxHitPoints() {
		// TODO Auto-generated method stub
		return maxHitPoints;
	}

	@Override
	public int getMaxMana() {
		return maxMana;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getOriginalBody() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOriginalHead() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reputation getReputation() {
		return reputation;
	}

	@Override
	public Spell[] getSpells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getThirstiness() {
		return thirstiness;
	}

	@Override
	public boolean isDead() {
		return hitpoints > 0 ? false : true;
	}

	@Override
	public boolean isDumb() {
		return dumbed;
	}

	@Override
	public boolean isHidden() {
		return hidden;
	}

	@Override
	public boolean isImmobilized() {
		return immobilized;
	}

	@Override
	public boolean isInvisible() {
		return invisible;
	}

	@Override
	public boolean isMimetized() {
		return mimetized;
	}

	@Override
	public boolean isParalyzed() {
		return paralyzed;
	}

	@Override
	public boolean isPoisoned() {
		return poisoned;
	}

	@Override
	public void moveTo(Heading heading) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBody(int body) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDead(boolean dead) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDumb(boolean dumb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHead(int head) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public void setImmobilized(boolean immobilized) {
		this.immobilized = immobilized;
	}

	@Override
	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}

	@Override
	public void setMimetized(boolean mimetized) {
		this.mimetized = mimetized;
		
	}

	@Override
	public void setParalyzed(boolean paralyzed) {
		this.paralyzed = paralyzed;
	}

	@Override
	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}

	@Override
	public void setPosition(Position pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(Item item) {
		// TODO Auto-generated method stub
		
	}

}
