package com.ao.service.map;

import com.ao.model.character.Character;
import com.ao.model.map.Heading;
import com.ao.model.map.WorldMap;
import com.ao.model.user.LoggedUser;

public interface AreaService {

	void checkIfUserNeedsUpdate(WorldMap map, Character character,
			Heading heading);

	void addCharToMap(WorldMap map, Character character);

	void removeUserFromMap(WorldMap map, LoggedUser user);

}