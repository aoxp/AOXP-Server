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

package com.ao.ioc.module;

import com.ao.config.ArchetypeConfiguration;
import com.ao.model.character.archetype.Archetype;
import com.ao.model.character.archetype.AssasinArchetype;
import com.ao.model.character.archetype.BanditArchetype;
import com.ao.model.character.archetype.BardArchetype;
import com.ao.model.character.archetype.BlacksmithArchetype;
import com.ao.model.character.archetype.CarpenterArchetype;
import com.ao.model.character.archetype.ClericArchetype;
import com.ao.model.character.archetype.DruidArchetype;
import com.ao.model.character.archetype.FisherArchetype;
import com.ao.model.character.archetype.HunterArchetype;
import com.ao.model.character.archetype.LumberjackArchetype;
import com.ao.model.character.archetype.MageArchetype;
import com.ao.model.character.archetype.MinerArchetype;
import com.ao.model.character.archetype.PaladinArchetype;
import com.ao.model.character.archetype.PirateArchetype;
import com.ao.model.character.archetype.ThiefArchetype;
import com.ao.model.character.archetype.WarriorArchetype;
import com.ao.model.character.archetype.WorkerArchetype;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ArchetypeModule extends AbstractModule {

	private ArchetypeConfiguration config;
	
	@Override
 	protected void configure() {
	}
	
	public ArchetypeModule(ArchetypeConfiguration config) {
		this.config = config;
	}
	
	/**
	 * Retrieves a new instance of the given archetype class.
	 * @param archetype The archetype class.
	 * @return A new instance of the archetype.
	 * @throws Exception
	 */
	private Archetype getArchetype(Class <?extends Archetype> archetype) throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] args = new Class[] {
				float.class, float.class, float.class,
				float.class, float.class, float.class,
				float.class
			};
		
		return archetype.getConstructor(args)
			.newInstance(
				config.getEvasionModifier(archetype),
				config.getMeleeAccuracyModifier(archetype),
				config.getRangedAccuracyModifier(archetype),
				config.getMeleeDamageModifier(archetype),
				config.getRangedDamageModifier(archetype),
				config.getWrestlingDamageModifier(archetype),
				config.getBlockPowerModifier(archetype)
				);
	}
	
	@Provides
	AssasinArchetype provideAssasinArchetype() throws Exception {
		return (AssasinArchetype) getArchetype(AssasinArchetype.class);
	}
	
	@Provides
	BanditArchetype provideBanditArchetype() throws Exception {
		return (BanditArchetype) getArchetype(BanditArchetype.class);
	}
	
	@Provides
	BardArchetype provideBardArchetype() throws Exception {
		return (BardArchetype) getArchetype(BardArchetype.class);
	}
	
	@Provides
	BlacksmithArchetype provideBlacksmithArchetype() throws Exception {
		return (BlacksmithArchetype) getArchetype(BlacksmithArchetype.class);
	}
	
	@Provides
	CarpenterArchetype provideCarpenterArchetype() throws Exception {
		return (CarpenterArchetype) getArchetype(CarpenterArchetype.class);
	}
	
	@Provides
	ClericArchetype provideClericArchetype() throws Exception {
		return (ClericArchetype) getArchetype(ClericArchetype.class);
	}
	
	@Provides
	DruidArchetype provideDruidArchetype() throws Exception {
		return (DruidArchetype) getArchetype(DruidArchetype.class);
	}
	
	@Provides
	FisherArchetype provideFisherArchetype() throws Exception {
		return (FisherArchetype) getArchetype(FisherArchetype.class);
	}
	
	@Provides
	HunterArchetype provideHunterArchetype() throws Exception {
		return (HunterArchetype) getArchetype(HunterArchetype.class);
	}
	
	@Provides
	LumberjackArchetype provideLumberjackArchetype() throws Exception {
		return (LumberjackArchetype) getArchetype(LumberjackArchetype.class);
	}
	
	@Provides
	MageArchetype provideMageArchetype() throws Exception {
		return (MageArchetype) getArchetype(MageArchetype.class);
	}
	
	@Provides
	MinerArchetype provideMinerArchetype() throws Exception {
		return (MinerArchetype) getArchetype(MinerArchetype.class);
	}
	
	@Provides
	PaladinArchetype providePaladinArchetype() throws Exception {
		return (PaladinArchetype) getArchetype(PaladinArchetype.class);
	}
	
	@Provides
	PirateArchetype providePirateArchetype() throws Exception {
		return (PirateArchetype) getArchetype(PirateArchetype.class);
	}
	
	@Provides
	ThiefArchetype provideThiefArchetype() throws Exception {
		return (ThiefArchetype) getArchetype(ThiefArchetype.class);
	}

	@Provides
	WarriorArchetype provideWarriorArchetype() throws Exception {
		return (WarriorArchetype) getArchetype(WarriorArchetype.class);
	}
	
	@Provides
	WorkerArchetype provideWorkerArchetype() throws Exception {
		return (WorkerArchetype) getArchetype(WorkerArchetype.class);
	}
}
