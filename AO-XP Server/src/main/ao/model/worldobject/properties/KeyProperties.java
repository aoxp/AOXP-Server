package ao.model.worldobject.properties;

import java.util.List;

import ao.model.character.Race;
import ao.model.character.archetype.UserArchetype;
import ao.model.worldobject.WorldObjectType;

public class KeyProperties extends ItemProperties {

	protected int code;
	
	public KeyProperties(WorldObjectType type, int id, String name,
			int graphic, int value,
			int manufactureDifficulty, List<UserArchetype> forbiddenArchetypes,
			List<Race> forbiddenRaces, boolean newbie, 
			boolean logeable, boolean falls, boolean respawnable, int code) {
		super(type, id, name, graphic, value, manufactureDifficulty,
				forbiddenArchetypes, forbiddenRaces, newbie, logeable, falls, respawnable);
		
		this.code= code;
	}

	public int getCode() {
		return code;
	}
	
}
