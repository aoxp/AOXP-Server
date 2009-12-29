package ao.network.packet.incoming;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.util.Random;

import ao.model.character.Attribute;
import ao.model.user.LoggedUser;
import ao.network.Connection;
import ao.network.ServerPacketsManager;
import ao.network.packet.IncomingPacket;
import ao.network.packet.outgoing.DiceRollPacket;

public class ThrowDicesPacket implements IncomingPacket {
	
	/**
	 * Minimum attributes values.
	 */
	private final int MIN_STRENGTH = 15;
	private final int MIN_AGILITY = 15;
	private final int MIN_INGELLIGENCE = 16;
	private final int MIN_CHARISMA = 15;
	private final int MIN_CONSTITUTION = 16;
	
	private Random rnd = new Random();
	
	@Override
	public void handle(Connection connection) throws BufferUnderflowException,
			UnsupportedEncodingException {
		
		LoggedUser user = (LoggedUser) connection.getUser();
		
		byte strength = (byte) Math.max(MIN_STRENGTH, 13 + rnd.nextInt(4) + rnd.nextInt(3));
		byte agility = (byte) Math.max(MIN_AGILITY, 12 + rnd.nextInt(4) + rnd.nextInt(4));
		byte intelligence = (byte) Math.max(MIN_INGELLIGENCE, 13 + rnd.nextInt(4) + rnd.nextInt(3));
		byte charisma = (byte) Math.max(MIN_CHARISMA, 12 + rnd.nextInt(4) + rnd.nextInt(4));
		byte constitution = (byte) Math.max(MIN_CONSTITUTION, 16 + rnd.nextInt(2) + rnd.nextInt(2));
		
		user.setAttribute(Attribute.STRENGTH, strength);
		user.setAttribute(Attribute.AGILITY, agility);
		user.setAttribute(Attribute.INTELLIGENCE, intelligence);
		user.setAttribute(Attribute.CHARISMA, charisma);
		user.setAttribute(Attribute.CONSTITUTION, constitution);
		
		ServerPacketsManager.write(
				new DiceRollPacket(strength, agility, intelligence, charisma, constitution),
				connection.getOutputBuffer()
			);
	}

}
