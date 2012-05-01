package com.ao.network.packet.incoming;

import java.io.UnsupportedEncodingException;

import com.ao.context.ApplicationContext;
import com.ao.model.character.archetype.PirateArchetype;
import com.ao.model.fonts.Font;
import com.ao.model.map.Heading;
import com.ao.model.user.LoggedUser;
import com.ao.network.Connection;
import com.ao.network.DataBuffer;
import com.ao.network.packet.IncomingPacket;
import com.ao.network.packet.outgoing.ConsoleMessagePacket;
import com.ao.service.MapService;

public class WalkPacket implements IncomingPacket {

	private MapService marService = ApplicationContext.getInstance(MapService.class);

	@Override
	public boolean handle(DataBuffer buffer, Connection connection) throws IndexOutOfBoundsException, UnsupportedEncodingException {

		if (buffer.getReadableBytes() < 1) {
			return false;
		}

		LoggedUser user = ((LoggedUser)connection.getUser());

		//TODO check security issues

		if (! user.isParalyzed()) {
			if (user.isMeditating()) {
				// Stop meditating, next action will start movement.
				user.setMeditate(false);

				connection.send(
					new ConsoleMessagePacket("Dejas de meditar.", Font.INFO)
				);

				/* TODO
				 * .Char.FX = 0
				 * .Char.loops = 0
				 * Call WriteMeditateToggle(UserIndex)
				 * Call SendData(SendTa rget.ToPCArea, UserIndex, PrepareMessageCreateFX(.Char.CharIndex, 0, 0))
				 */

			} else {
				Heading heading = Heading.get(buffer.get());
				if (heading != null) {

					// Move user
					marService.moveCharacterTo(user, heading);

					//TODO Stop resting if needed

				} else {
					return true;
				}

			}
		} else { //if paralized
			//TODO set last message flag
			//TODO aplicar seguridad contra SH
		}

		// Check if hidden and can move while hidden
		if (user.isHidden() && !user.isAdminHidden() && !user.getArchetype().canWalkHidden()) {
			user.setHidden(false);

			if (user.isSailing()) {
				// TODO : Find a nicer way to do this...
				if (user.getArchetype() instanceof PirateArchetype) {

//	                    ' Pierde la apariencia de fragata fantasmal
//	                    Call ToggleBoatBody(UserIndex)
				
					connection.send(
						new ConsoleMessagePacket("¡Has recuperado tu apariencia normal!", Font.INFO)
					);
//	                    Call WriteConsoleMsg(UserIndex, "¡Has recuperado tu apariencia normal!", FontTypeNames.FONTTYPE_INFO)

//	                    Call ChangeUserChar(UserIndex, .Char.body, .Char.Head, .Char.heading, NingunArma, _
//	                                    NingunEscudo, NingunCasco)
				}

			} else if (user.isInvisible()) {

				// If not under a spell effect, show character
				connection.send(
					new ConsoleMessagePacket("Has vuelto a ser visible.", Font.INFO)
				);
//                    Call UsUaRiOs.SetInvisible(UserIndex, .Char.CharIndex, False)
			}
		}
		
		return true;
	}

}
