package com.ao.service.map;

import com.ao.model.character.Character;
import com.ao.model.character.UserCharacter;
import com.ao.model.map.Heading;
import com.ao.model.map.Tile;
import com.ao.model.map.WorldMap;
import com.ao.model.map.area.AreaInfo;
import com.ao.model.user.LoggedUser;
import com.ao.model.worldobject.Door;
import com.ao.model.worldobject.WorldObject;
import com.ao.network.Connection;
import com.ao.network.packet.outgoing.AreaChangedPacket;
import com.ao.network.packet.outgoing.BlockPositionPacket;
import com.ao.network.packet.outgoing.ObjectCreatePacket;
import com.ao.network.packet.outgoing.SetInvisiblePacket;

public class AreaServiceImpl {
	/*
	 * For a better understanding of areas, please refer to
	 * https://github.com/aoxp/AOXP-Server/wiki/Areas-for-dummies
	 */

	// we can see 3x3 areas around us
	private static final int VISIBLE_AREAS = 3;


	private final int[] areasReceive;

	public AreaServiceImpl() {
		// This assumes maps will always be square
		areasReceive = new int[WorldMap.MAP_WIDTH / AreaInfo.AREA_SIZE + 1];

		for (int i = 0; i < areasReceive.length; i++) {
			// Set the bit for the current index + the previous one + the
			// following one (if they exist)
			areasReceive[i] = (2 ^ i) | (i != 0 ? 2 ^ (i - 1) : 0)
					| (i != 11 ? 2 ^ (i + 1) : 0);
		}
	}

	public void checkIfUserNeedsUpdate(final WorldMap map, final LoggedUser user, final Heading heading) {
		final AreaInfo areaInfo = user.getCurrentAreaInfo();

		if (areaInfo.isInSameArea(user.getPosition())) {
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
			maxX = minVisibleX + AreaInfo.AREA_SIZE * VISIBLE_AREAS;
			break;

		case SOUTH:
			// All new VISIBLE_AREAS to the bottom
			minY = minVisibleY + AreaInfo.AREA_SIZE * VISIBLE_AREAS;
			maxY = minY + AreaInfo.AREA_SIZE - 1;
			minX = minVisibleX;
            maxX = minVisibleX + AreaInfo.AREA_SIZE * VISIBLE_AREAS;
			break;

		case WEST:
			maxX = minVisibleX - 1;
            minX = minVisibleX - AreaInfo.AREA_SIZE;
            minY = minVisibleY;
            maxY = minVisibleY + AreaInfo.AREA_SIZE * VISIBLE_AREAS;
            break;

		case EAST:
             minX = minVisibleX + AreaInfo.AREA_SIZE * VISIBLE_AREAS;
             maxX = minX + AreaInfo.AREA_SIZE;
             minY = minVisibleY;
             maxY = minVisibleY + AreaInfo.AREA_SIZE * VISIBLE_AREAS;
             break;

         default:
        	 throw new AssertionError("Unexpected heading found in switch: " + heading);
		}

		// TODO : check if this should be done here, or at the end of the method
		// Update the user's area
		user.getCurrentAreaInfo().changeCurrentAreTowards(heading);

		// TODO : new users sould probably be handled in a separate method
//            ElseIf Head = USER_NUEVO Then
//                'Esto pasa por cuando cambiamos de mapa o logeamos...
//                MinY = ((.Pos.Y \ 9) - 1) * 9
//                MaxY = MinY + 26
//
//                MinX = ((.Pos.X \ 9) - 1) * 9
//                MaxX = MinX + 26
//
//                .AreasInfo.MinX = CInt(MinX)
//                .AreasInfo.MinY = CInt(MinY)
//            End If

		// Tell the user he changed areas, so he can get rid of old data
		user.getConnection().send(new AreaChangedPacket(user.getPosition()));

		updateRegion(map, minX, minY, maxX, maxY, user);
	}

	private void updateRegion(final WorldMap map, final int minX, final int minY,
			final int maxX, final int maxY, final LoggedUser user) {
		final Connection userConnection = user.getConnection();

		// TODO : I believe these bounds are always safe, check it
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				final Tile tile = map.getTile(x, y);
				final Character tileCharacter = tile.getCharacter();
				final WorldObject worldObject = tile.getWorldObject();

				if (tileCharacter != null) {
					if (tileCharacter != user) {	// Same instance, we can use ==
						// TODO : It would be awesome if MakeUserChar could handle this transparently
						// If it's not an invisible admin, we tell the new user
						if (!tileCharacter.isAdminHidden()) {
							// TODO : MakeUserChar

							// FIXME : Move this logic away!
							if (!tileCharacter.isSailing() && (tileCharacter.isInvisible() || tileCharacter.isHidden())) {
								// If .flags.Privilegios And (PlayerType.User Or PlayerType.Consejero Or PlayerType.RoleMaster) Then
								userConnection.send(new SetInvisiblePacket(tileCharacter, true));
								// End If
							}
						}

						// If our moving user is not admin hidden, notify the other user
						if (tileCharacter instanceof UserCharacter) {
							if (!user.isAdminHidden()) {
								// TODO : MakeUserChar

								// FIXME : Move this logic away!
								if (!user.isSailing() && (user.isInvisible() || user.isHidden())) {
									// If UserList(TempInt).flags.Privilegios And PlayerType.User Then
									((LoggedUser) tileCharacter).getConnection().send(new SetInvisiblePacket(user, true));
									// End If
								}
							}
						} else {
							// TODO : MakeNPCChar
						}
					}
					// FIXME : New characters should be handled separately (plus, this breaks the "don't tell if admin hidden" rule
					// ElseIf Head = USER_NUEVO Then
					// If Not ButIndex Then
					// Call MakeUserChar(False, UserIndex, UserIndex, Map, X, Y)
					// End If
					// End If
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
}