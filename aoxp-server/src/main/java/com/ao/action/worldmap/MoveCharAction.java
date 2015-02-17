package com.ao.action.worldmap;

import com.ao.model.character.Character;
import com.ao.model.map.Heading;
import com.ao.service.MapService;

public class MoveCharAction extends WorldMapAction {
	private final Character character;
	private final Heading heading;

	public MoveCharAction(final Character character,
			final Heading heading) {
		this.character = character;
		this.heading = heading;
	}

	@Override
	protected void performAction(final MapService mapService) {
		mapService.moveCharacterTo(character, heading);
	}
}
