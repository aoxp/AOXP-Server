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


package com.ao.model.character.archetype;

import com.ao.model.worldobject.Boat;
import com.ao.model.worldobject.Item;
import com.ao.model.worldobject.properties.manufacture.Manufacturable;

public class WorkerArchetype extends DefaultArchetype {

	public WorkerArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canBlacksmith(int blacksmithSkill, Manufacturable item) {
		// TODO Auto-generated method stub
		return super.canBlacksmith(blacksmithSkill, item);
	}

	@Override
	public boolean canIronWork(int ironWorkingSkill, Manufacturable item) {
		// TODO Auto-generated method stub
		return super.canIronWork(ironWorkingSkill, item);
	}

	@Override
	public boolean canSail(int sailingSkill, Boat boat) {
		// TODO Auto-generated method stub
		return super.canSail(sailingSkill, boat);
	}

	@Override
	public boolean canWoodWork(int woodWorkSkill, Manufacturable item) {
		// TODO Auto-generated method stub
		return super.canWoodWork(woodWorkSkill, item);
	}

	@Override
	public int getFishedMaxAmount() {
		// TODO Auto-generated method stub
		return super.getFishedMaxAmount();
	}

	@Override
	public int getFishedMinAmount() {
		// TODO Auto-generated method stub
		return super.getFishedMinAmount();
	}

	@Override
	public int getFishingStaminaCost() {
		// TODO Auto-generated method stub
		return super.getFishingStaminaCost();
	}

	@Override
	public int getLumberedMaxAmount() {
		// TODO Auto-generated method stub
		return super.getLumberedMaxAmount();
	}

	@Override
	public int getLumberedMinAmount() {
		// TODO Auto-generated method stub
		return super.getLumberedMinAmount();
	}

	@Override
	public int getLumberjackingStaminaCost() {
		// TODO Auto-generated method stub
		return super.getLumberjackingStaminaCost();
	}

	@Override
	public int getMiningMaxAmount() {
		// TODO Auto-generated method stub
		return super.getMiningMaxAmount();
	}

	@Override
	public int getMiningMinAmount() {
		// TODO Auto-generated method stub
		return super.getMiningMinAmount();
	}

	@Override
	public int getMiningStaminaCost() {
		// TODO Auto-generated method stub
		return super.getMiningStaminaCost();
	}

	@Override
	public int getSailingMinLevel() {
		// TODO Auto-generated method stub
		return super.getSailingMinLevel();
	}

	@Override
	public int getWoodWorkStaminaCost() {
		// TODO Auto-generated method stub
		return super.getWoodWorkStaminaCost();
	}

	@Override
	public float getEvasionModifier() {
		// TODO Auto-generated method stub
		return super.getEvasionModifier();
	}

	@Override
	protected float getSailingModifier() {
		// TODO Auto-generated method stub
		return super.getSailingModifier();
	}

	@Override
	protected float getBlacksmithModifier() {
		// TODO Auto-generated method stub
		return super.getBlacksmithModifier();
	}

	@Override
	protected float getIronWorkingModifier() {
		// TODO Auto-generated method stub
		return super.getIronWorkingModifier();
	}

	@Override
	protected float getWoodWorkingModifier() {
		// TODO Auto-generated method stub
		return super.getWoodWorkingModifier();
	}

}
