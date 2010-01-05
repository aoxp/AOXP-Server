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

package ao.model.character.archetype;

import ao.ioc.ArchetypeLocator;

/**
 * User Archetype enumerator. Wraps archetype classes in an enum.
 */
public enum UserArchetype {
	MAGE(ArchetypeLocator.getArchetype(MageArchetype.class)),
	CLERIC(ArchetypeLocator.getArchetype(ClericArchetype.class)),
	WARRIOR(ArchetypeLocator.getArchetype(WarriorArchetype.class)),
	ASSASIN(ArchetypeLocator.getArchetype(AssasinArchetype.class)),
	THIEF(ArchetypeLocator.getArchetype(ThiefArchetype.class)),
	BARD(ArchetypeLocator.getArchetype(BardArchetype.class)),
	DRUID(ArchetypeLocator.getArchetype(DruidArchetype.class)),
	BANDIT(ArchetypeLocator.getArchetype(BanditArchetype.class)),
	PALADIN(ArchetypeLocator.getArchetype(PaladinArchetype.class)),
	HUNTER(ArchetypeLocator.getArchetype(HunterArchetype.class)),
	FISHER(ArchetypeLocator.getArchetype(FisherArchetype.class)),
	BLACKSMITH(ArchetypeLocator.getArchetype(BlacksmithArchetype.class)),
	LUMBERJACK(ArchetypeLocator.getArchetype(LumberjackArchetype.class)),
	MINER(ArchetypeLocator.getArchetype(MinerArchetype.class)),
	CARPENTER(ArchetypeLocator.getArchetype(CarpenterArchetype.class)),
	PIRATE(ArchetypeLocator.getArchetype(PirateArchetype.class));
	
	private static UserArchetype[] values = UserArchetype.values();
	
	private Archetype archetype;
	
	/**
	 * Retrieves the UserArchetype for the given index.
	 * @param index The UserArchetype index.
	 * @return The UserArchetype.
	 */
	public static UserArchetype get(byte index) {
		return values[index];
	}
	
	/**
	 * Create a new UserArchetype
	 * @param archetype The Archetype class corresponding to this UserArchetype.
	 */
	private UserArchetype(Archetype archetype) {
		this.archetype = archetype;
	}

	/**
	 * Retrieves the Archetype related to this UserArchetype.
	 * @return The Archetype related to this UserArchetype.
	 */
	public Archetype getArchetype() {
		return archetype;
	}
	
	/**
	 * Allows to retrieve the enum back from the Archetype.
	 * @param archetype The Archetype to look up in the enum.
	 * @return The UserArchetype matching the given Archetype, null if none matches.
	 */
	public static UserArchetype valueOf(Archetype archetype) {
		String archetypeClassName = archetype.getClass().getSimpleName();
		
		for (UserArchetype arch : UserArchetype.values()) {
			if (arch.getArchetype().getClass().getSimpleName().equals(archetypeClassName)) {
				return arch;
			}
		}
		
		return null;
	}
	
}
