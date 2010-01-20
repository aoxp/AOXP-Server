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

package ao.model.map;

import ao.model.character.Character;
import ao.model.character.NPCCharacter;
import ao.model.worldobject.Item;
import ao.model.worldobject.WorldObject;

/**
 * A map's tile.
 */
public class Tile {

	public static final byte LAYERS = 4; 
	public static final byte OBLIGATORY_LAYERS_AMOUNT = 1;
	
	protected Character character;
	protected WorldObject worldObject;
	protected Trigger trigger;
	protected short[] layers; 
	protected boolean blocked; 
	protected Position tileExit;
	protected Character npc; 

	public Tile(boolean blocked, short[] layers, short trigger, Position tileExit, NPCCharacter npc, WorldObject worldObject) {
		this.blocked = blocked;		
		this.layers = layers;
		this.trigger = Trigger.get(trigger);
		this.tileExit = tileExit;
		
		//TODO Create the Implementations.
		this.worldObject = worldObject;
		this.npc = npc;

	}
	
	/**
	 * Sets the given graphic at the given layer.
	 * @param graphic The graphic to be seted.
	 * @param layer The layer where the graphic must be seted.
	 */
	public void setLayer(short graphic, byte layer) {
		this.layers[layer - 1] = graphic;
	}	
	
	/**
	 * Retrieves the graphic in the given layer. 
	 * @param layer The layer of the tile.
	 * @return
	 */
	public int getLayer(byte layer) {
		return layers[layer - 1];
	}
	
	/**
	 * Retrieves the tile exit at the tile. 
	 * @return The tile exit at the tile.
	 */
	public Position getTileExit() {
		return this.tileExit;
	}
	
	/**
	 * Sets the tile exit at the tile. 
	 * @param tileExit The tileExit to be set at the tile.
	 */
	public void setTileExit(Position tileExit) {
		this.tileExit = tileExit;
	}

	/**
	 * Retrieves the blocked at the tile.
	 * @return True if the tile is blocked, false otherwise.
	 */
	public boolean isBlocked() {
		return blocked;
	}
	
	/**
	 * Sets the block at the tile.
	 * @param block The blocked to be set at the tile.
	 */
	public void setBlocked(boolean block) {	
		this.blocked = block;
	}
	

	/**
	 * Sets the NPCCharacter at the tile.
	 * @param npc The NPCCharacter to be set at the tile.
	 */
	public void setNPC(Character npc) {
		this.npc = npc;
	}
	
	/**
	 * Retrieves the NPCCharacter at the tile.
	 * @return The NPCCharacter at the tile.
	 */
	public Character getNPC() {
		return npc ;
	}	
	
	/**
	 * Retrieves the character at the tile.
	 * @return The character at the tile.
	 */
	public Character getCharacter() {
		return character;
	}
	
	/**
	 * Sets the character at the tile.
	 * @param character The character to be set at the tile.
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	/**
	 * Retrieves the world object at the tile.
	 * @return The world object at the tile.
	 */
	public WorldObject getWorldObject() {
		return worldObject;
	}
	
	/**
	 * Sets the world object at the tile.
	 * @param worldObject The worldObject to set at the tile.
	 */
	public void setWorldObject(WorldObject worldObject) {
		this.worldObject = worldObject;
	}
	
	/**
	 * Retrieves the trigger at the tile.
	 * @return The trigger at the tile.
	 */
	public Trigger getTrigger() {
		return trigger;
	}
	
	/**
	 * Sets the trigger at the tile.
	 * @param trigger The trigger to be set at the tile.
	 */
	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
}
