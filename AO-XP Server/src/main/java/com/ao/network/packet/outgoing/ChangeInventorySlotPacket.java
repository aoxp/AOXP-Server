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

package com.ao.network.packet.outgoing;

import java.io.UnsupportedEncodingException;

import org.easymock.internal.matchers.InstanceOf;

import com.ao.model.character.UserCharacter;
import com.ao.model.inventory.Inventory;
import com.ao.model.worldobject.DefensiveItem;
import com.ao.model.worldobject.EquipableItem;
import com.ao.model.worldobject.Helmet;
import com.ao.model.worldobject.Item;
import com.ao.model.worldobject.ManaPotion;
import com.ao.model.worldobject.Shield;
import com.ao.model.worldobject.Weapon;
import com.ao.model.worldobject.WorldObject;
import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;
import com.ao.service.user.UserServiceImpl;

public class ChangeInventorySlotPacket implements OutgoingPacket {

	private byte slot;
	private UserCharacter userCharacter;
	private Item item;
	
	//private EquipableItem item;
	
	public ChangeInventorySlotPacket(short charIndex, byte slot) {
		
		UserServiceImpl userService = new UserServiceImpl();
		Inventory inventory = userCharacter.getInventory();
		
		//TODO: falta un getCharacterById
		this.userCharacter = userService.getCharacter("");
		this.slot = slot;
		this.item = inventory.getItem(slot);		
	}
	
	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.put(slot);
		buffer.putShort((short) item.getId());
		buffer.putASCIIString(item.getName());
		buffer.putShort((short) item.getAmount());
		buffer.putBoolean(this.userCharacter.isEquipped(item));
		buffer.putShort((short) item.getGraphic());
		
		if (item instanceof Weapon) {
			buffer.putShort((short) ((Weapon) item).getMaxHit());
			buffer.putShort((short) ((Weapon) item).getMinHit());
		} else {
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
		}
		
		if (item instanceof DefensiveItem) {
			buffer.putShort((short) ((DefensiveItem) item).getMaxDef());
			buffer.putShort((short) ((DefensiveItem) item).getMinDef());
		} else {
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
		}
					
		buffer.putFloat((float) item.getValue());
	}

}
