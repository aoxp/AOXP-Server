package com.ao.model.worldobject.properties;

import java.util.List;

import com.ao.model.character.Race;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.worldobject.WorldObjectType;

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
