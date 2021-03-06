/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.ao.network;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.ao.network.packet.OutgoingPacket;
import com.ao.network.packet.outgoing.AreaChangedPacket;
import com.ao.network.packet.outgoing.BlockPositionPacket;
import com.ao.network.packet.outgoing.ChangeMapPacket;
import com.ao.network.packet.outgoing.ChangeSpellSlotPacket;
import com.ao.network.packet.outgoing.CharacterCreatePacket;
import com.ao.network.packet.outgoing.ConsoleMessagePacket;
import com.ao.network.packet.outgoing.DiceRollPacket;
import com.ao.network.packet.outgoing.ErrorMessagePacket;
import com.ao.network.packet.outgoing.GuildChatPacket;
import com.ao.network.packet.outgoing.ObjectCreatePacket;
import com.ao.network.packet.outgoing.ParalizedPacket;
import com.ao.network.packet.outgoing.PlayMidiPacket;
import com.ao.network.packet.outgoing.PlayWavePacket;
import com.ao.network.packet.outgoing.SetInvisiblePacket;
import com.ao.network.packet.outgoing.UpdateDexterityPacket;
import com.ao.network.packet.outgoing.UpdateHungerAndThirstPacket;
import com.ao.network.packet.outgoing.UpdateStrengthAndDexterityPacket;
import com.ao.network.packet.outgoing.UpdateStrengthPacket;
import com.ao.network.packet.outgoing.UpdateUserStatsPacket;
import com.ao.network.packet.outgoing.UserCharacterIndexInServerPacket;
import com.ao.network.packet.outgoing.UserIndexInServer;

/**
 * Manager for server-side packets.
 */
public class ServerPacketsManager {

	/**
	 * Enumerates server packets.
	 */
	private enum ServerPackets {
		LOGGED(null),
	    REMOVE_ALL_DIALOGS(null),
	    REMOVE_CHR_DIALOG(null),
	    TOGGLE_NAVIGATE(null),
	    DISCONNECT(null),
	    COMMERCE_END(null),
	    BANKING_END(null),
	    COMMERCE_INIT(null),
	    BANK_INIT(null),
	    USER_COMMERCE_INIT(null),
	    USER_COMMERCE_END(null),
	    USER_OFFER_CONFIRM(null),
	    COMMERCE_CHAT(null),
	    SHOW_BLACKSMITH_FORM(null),
	    SHOW_CARPENTER_FORM(null),
	    UPDATE_STAMINA(null),
	    UPDATE_MANA(null),
	    UPDATE_HP(null),
	    UPDATE_GOLD(null),
	    UPDATE_BANK_GOLD(null),
	    UPDATE_EXP(null),
	    CHANGE_MAP(ChangeMapPacket.class),
	    POSITION_UPDATE(null),
	    CHAT_OVER_HEAD(null),
	    CONSOLE_MESSAGE(ConsoleMessagePacket.class),
	    GUILD_CHAT(GuildChatPacket.class),
	    SHOW_MESSAGE_BOX(null),
	    USER_INDEX_IN_SERVER(UserIndexInServer.class),
	    USER_CHARACTER_INDEX_IN_SERVER(UserCharacterIndexInServerPacket.class),
	    CHARACTER_CREATE(CharacterCreatePacket.class),
	    CHARACTER_REMOVE(null),
	    CHARACTER_CHANGE_NICKNAME(null),
	    CHARACTER_MOVE(null),
	    CHARACTER_FORCE_MOVE(null),
	    CHARACTER_CHANGE(null),
	    OBJECT_CREATE(ObjectCreatePacket.class),
	    OBJECT_DEETE(null),
	    BLOCK_POSITION(BlockPositionPacket.class),
	    PLAY_MIDI(PlayMidiPacket.class),
	    PLAY_WAVE(PlayWavePacket.class),
	    GUILD_LIST(null),
	    AREA_CHANGED(AreaChangedPacket.class),
	    TOGGLE_PAUSE(null),
	    TOGGLE_RAIN(null),
	    CREATE_FX(null),
	    UPDATE_USER_STATS(UpdateUserStatsPacket.class),
	    WORK_REQUEST_TARGET(null),
	    CHANGE_INVENTORY_SLOT(null),
	    CHANGE_BANK_SLOT(null),
	    CHANGE_SPELL_SLOT(ChangeSpellSlotPacket.class),
	    ATTRIBUTES(null),
	    BLACKSMITH_WEAPONS(null),
	    BLACKSMITH_ARMORS(null),
	    CARPENTER_OBJECTS(null),
	    REST_OK(null),
	    ERROR_MESSAGE(ErrorMessagePacket.class),
	    BLIND(null),
	    DUMB(null),
	    SHOW_SIGNAL(null),
	    CHANGE_NPC_INVENTORY_SLOT(null),
	    UPDATE_HUNGER_AND_THRIST(UpdateHungerAndThirstPacket.class),
	    FAME(null),
	    MINI_STATS(null),
	    LEVEL_UP(null),
	    ADD_FORUM_MESAGE(null),
	    SHOW_FORUM_MESSAGE(null),
	    SET_INVISIBLE(SetInvisiblePacket.class),
	    ROLL_DICE(DiceRollPacket.class),
	    MEDITATE_TOGGLE(null),
	    BLIND_NO_MORE(null),
	    DUMB_NO_MORE(null),
	    SEND_SKILLS(null),
	    TRAINER_CREATURE_LIST(null),
	    GUILD_NEWS(null),
	    OFFER_DETAILS(null),
	    ALIANCE_PROPOSALS_LIST(null),
	    PEACE_PROPOSALS_LIST(null),
	    CHARACTER_INFO(null),
	    GUILD_LEADER_INFO(null),
	    GUILD_MEMBER_INFO(null),
	    GUILD_DETAILS(null),
	    SHOW_GUILD_FUNDATION_FORM(null),
	    PARALIZE_OK(ParalizedPacket.class),
	    SHOW_USER_REQUEST(null),
	    TRADE_OK(null),
	    BANK_OK(null),
	    CHANGE_USER_TRADE_SLOT(null),
	    SEND_NIGHT(null),
	    PONG(null),
	    UPDATE_TAG_AND_STATUS(null),
	    SPAWN_LIST(null),
	    SHOW_SOS_FORM(null),
	    SHOW_MOTD_EDITION_FORM(null),
	    SHOW_GM_PANEL_FORM(null),
	    USER_NAME_LIST(null),
	    SHOW_DENOUNCES(null),
	    RECORD_LIST(null),
	    RECORD_DETAILS(null),
	    SHOW_GUILD_ALIGN(null),
	    SHOW_PARTY_FORM(null),
	    UPDATE_STRENGHT_AND_DEXTERITY(UpdateStrengthAndDexterityPacket.class),
	    UPDATE_STRENGHT(UpdateStrengthPacket.class),
	    UPDATE_DEXTERITY(UpdateDexterityPacket.class),
	    ADD_SLOTS(null),
	    MULTI_MESSAGE(null),
	    STOP_WORKING(null),
	    CANCEL_OFFER_ITEM(null);

		protected Class<? extends OutgoingPacket> packetClass;

		private ServerPackets(Class<? extends OutgoingPacket> packet) {
			packetClass = packet;
		}
	}

	/**
	 * Maps packets ids to their classes.
	 */
	protected static final ServerPackets[] packets = ServerPackets.values();

	/**
	 * Maps packets classes to their ids.
	 */
	protected static final Map<Class<? extends OutgoingPacket>, Integer> packetsMap = new HashMap<Class<? extends OutgoingPacket>, Integer>();

	static {
		for (final ServerPackets packet : packets) {
			packetsMap.put(packet.packetClass, packet.ordinal());
		}
	}

	/**
	 * Writes the given packet in the given buffer.
	 * @param packet The packet to write.
	 * @param buffer The buffer where to write the packet.
	 * @throws UnsupportedEncodingException
	 */
	public static void write(final OutgoingPacket packet, final DataBuffer buffer) throws UnsupportedEncodingException {

		// Put the packet id before the packet itself.
		buffer.put(packetsMap.get(packet.getClass()).byteValue());
		packet.write(buffer);
	}
}
