package com.ao.action.worldmap;

import com.ao.model.character.UserCharacter;
import com.ao.model.map.Position;
import com.ao.service.MapService;

public class MakeUserCharAction extends WorldMapAction {

	private final UserCharacter character;
	private final Position pos;

	public MakeUserCharAction(final UserCharacter character, final Position pos) {
		this.character = character;
		this.pos = pos;
	}

	@Override
	protected void performAction(final MapService service) {
		service.putCharacterAtPos(character, pos);
	}

}
