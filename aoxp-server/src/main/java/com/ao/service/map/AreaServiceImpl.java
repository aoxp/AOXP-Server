package com.ao.service.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import com.ao.model.character.Character;
import com.ao.model.character.NPCCharacter;
import com.ao.model.character.UserCharacter;
import com.ao.model.map.Heading;
import com.ao.model.map.Tile;
import com.ao.model.map.WorldMap;
import com.ao.model.map.area.AreaInfo;
import com.ao.model.user.ConnectedUser;
import com.ao.model.user.LoggedUser;
import com.ao.model.worldobject.Door;
import com.ao.model.worldobject.WorldObject;
import com.ao.network.Connection;
import com.ao.network.packet.outgoing.AreaChangedPacket;
import com.ao.network.packet.outgoing.BlockPositionPacket;
import com.ao.network.packet.outgoing.CharacterCreatePacket;
import com.ao.network.packet.outgoing.ObjectCreatePacket;
import com.ao.network.packet.outgoing.SetInvisiblePacket;

public class AreaServiceImpl implements AreaService {
	/*
	 * For a better understanding of areas, please refer to
	 * https://github.com/aoxp/AOXP-Server/wiki/Areas-for-dummies
	 */

	// we can see 3x3 areas around us
	private static final int VISIBLE_AREAS = 3;

	private final List<Set<ConnectedUser>> connectionGroups;

	@Inject
	public AreaServiceImpl(@Named("mapsAmount") final int mapsAmount) {
		connectionGroups = new ArrayList<>(mapsAmount);
		for (int i = 0; i < mapsAmount; i++) {
			connectionGroups.add(new HashSet<ConnectedUser>());
		}
	}

	/* (non-Javadoc)
	 * @see com.ao.service.map.AreaService#checkIfUserNeedsUpdate(com.ao.model.map.WorldMap, com.ao.model.character.Character, com.ao.model.map.Heading)
	 */
	@Override
	public void checkIfUserNeedsUpdate(final WorldMap map, final Character character, final Heading heading) {
		final AreaInfo areaInfo = character.getCurrentAreaInfo();

		if (areaInfo.isInSameArea(character.getPosition())) {
			return;
		}

		// Compute the portion of the map that needs to be updated
		final int maxX, maxY, minX, minY;
		final int minVisibleX = areaInfo.getMinX();
		final int minVisibleY = areaInfo.getMinY();

		switch (heading) {
		case NORTH:
			// All new VISIBLE_AREAS to the top
			maxY = minVisibleY - 1;
			minY = minVisibleY - AreaInfo.AREA_SIZE;
			minX = minVisibleX;
			maxX = minVisibleX + AreaInfo.AREA_SIZE * VISIBLE_AREAS - 1;
			break;

		case SOUTH:
			// All new VISIBLE_AREAS to the bottom
			minY = minVisibleY + AreaInfo.AREA_SIZE * VISIBLE_AREAS;
			maxY = minY + AreaInfo.AREA_SIZE - 1;
			minX = minVisibleX;
            maxX = minVisibleX + AreaInfo.AREA_SIZE * VISIBLE_AREAS - 1;
			break;

		case WEST:
			maxX = minVisibleX - 1;
            minX = minVisibleX - AreaInfo.AREA_SIZE;
            minY = minVisibleY;
            maxY = minVisibleY + AreaInfo.AREA_SIZE * VISIBLE_AREAS - 1;
            break;

		case EAST:
             minX = minVisibleX + AreaInfo.AREA_SIZE * VISIBLE_AREAS;
             maxX = minX + AreaInfo.AREA_SIZE;
             minY = minVisibleY;
             maxY = minVisibleY + AreaInfo.AREA_SIZE * VISIBLE_AREAS - 1;
             break;

         default:
        	 throw new AssertionError("Unexpected heading found in switch: " + heading);
		}

		// Update the user's area
		character.getCurrentAreaInfo().changeCurrentAreTowards(heading);

		// Is the one moving a user or an NPC?
		if (character instanceof LoggedUser) {
			final LoggedUser user = (LoggedUser) character;

			userEnteredRegion(map, minX, minY, maxX, maxY, user);
		} else {
			npcEnteredRegion(map, minX, minY, maxX, maxY, (NPCCharacter) character);
		}
	}

	private void npcEnteredRegion(final WorldMap map, final int minX, final int minY,
			final int maxX, final int maxY, final NPCCharacter character) {
		// TODO : I believe these bounds are always safe, check it
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				final Character tileCharacter = map.getTile(x, y).getCharacter();
				if (tileCharacter != null && tileCharacter instanceof LoggedUser) {
					// TODO : Call MakeNPCChar(False, MapData(.Pos.Map, X, Y).UserIndex, NpcIndex, .Pos.Map, .Pos.X, .Pos.Y)
				}
			}
		}
	}

	private void userEnteredRegion(final WorldMap map, final int minX, final int minY,
			final int maxX, final int maxY, final LoggedUser user) {
		final Connection userConnection = user.getConnection();

		// Tell the user he changed areas, so he can get rid of old data
		userConnection.send(new AreaChangedPacket(user.getPosition()));

		// TODO : I believe these bounds are always safe, check it
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				final Tile tile = map.getTile(x, y);
				final Character tileCharacter = tile.getCharacter();
				final WorldObject worldObject = tile.getWorldObject();

				if (tileCharacter != null) {
					if (tileCharacter != user) { // Same instance, we can use ==
						// If it's not an invisible admin, we tell the new user
						if (!tileCharacter.isAdminHidden()) {
							userConnection.send(new CharacterCreatePacket(tileCharacter));

							// FIXME : Move this logic away!
							if (!tileCharacter.isSailing() && (tileCharacter.isInvisible() || tileCharacter.isHidden())) {
								// TODO : If .flags.Privilegios And (PlayerType.User Or PlayerType.Consejero Or PlayerType.RoleMaster) Then
								userConnection.send(new SetInvisiblePacket(tileCharacter, true));
								// End If
							}
						}

						// If our moving user is not admin hidden, notify the other user
						if (tileCharacter instanceof UserCharacter) {
							if (!user.isAdminHidden()) {
								final Connection otherUserConnection = ((LoggedUser) tileCharacter).getConnection();
								otherUserConnection.send(new CharacterCreatePacket(user));

								// FIXME : Move this logic away!
								if (!user.isSailing() && (user.isInvisible() || user.isHidden())) {
									// If UserList(TempInt).flags.Privilegios And PlayerType.User Then
									otherUserConnection.send(new SetInvisiblePacket(user, true));
									// End If
								}
							}
						}
					}
				}

				if (worldObject != null) {
					if (!worldObject.isFixed()) {
						userConnection.send(new ObjectCreatePacket(worldObject, (byte) x, (byte) y));

						// TODO : Do we really need to send it every time? Can't the client default to closed / blocked, and just send data when it's not?
						if (worldObject instanceof Door) {
							userConnection.send(new BlockPositionPacket((byte) x, (byte) y, tile.isBlocked()));
							userConnection.send(new BlockPositionPacket((byte) (x - 1), (byte) y, map.getTile(x - 1, y).isBlocked()));
						}
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.ao.service.map.AreaService#addCharToMap(com.ao.model.map.WorldMap, com.ao.model.character.Character)
	 */
	@Override
	public void addCharToMap(final WorldMap map, final Character character) {
		final AreaInfo areaInfo = character.getCurrentAreaInfo();
		areaInfo.setForPosition(character.getPosition());

		final int maxX = areaInfo.getMinX() + AreaInfo.AREA_SIZE * VISIBLE_AREAS - 1;
		final int maxY = areaInfo.getMinY() + AreaInfo.AREA_SIZE * VISIBLE_AREAS - 1;

		// Is the one moving a user or an NPC?
		if (character instanceof LoggedUser) {
			final LoggedUser user = (LoggedUser) character;

			connectionGroups.get(map.getId() - 1).add(user);
			// Tell the user he is in the map
			user.getConnection().send(new CharacterCreatePacket(character));
			userEnteredRegion(map, areaInfo.getMinX(), areaInfo.getMinY(), maxX, maxY, user);
		} else {
			npcEnteredRegion(map, areaInfo.getMinX(), areaInfo.getMinY(), maxX, maxY, (NPCCharacter) character);
		}
	}

	/* (non-Javadoc)
	 * @see com.ao.service.map.AreaService#removeUserFromMap(com.ao.model.map.WorldMap, com.ao.model.user.LoggedUser)
	 */
	@Override
	public void removeUserFromMap(final WorldMap map, final LoggedUser user) {
		connectionGroups.get(map.getId() - 1).remove(user);
	}
}