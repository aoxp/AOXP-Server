package com.ao.model.character.npc.drop;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ao.context.ApplicationContext;
import com.ao.model.character.npc.Drop;
import com.ao.model.worldobject.WorldObject;
import com.ao.model.worldobject.factory.WorldObjectFactoryException;
import com.ao.service.WorldObjectService;

public class PretorianDrop implements Drop {

	protected Map<Integer, Integer> inventory;
	
	private final WorldObjectService woService = ApplicationContext.getInstance(WorldObjectService.class);
	
	public PretorianDrop(Map<Integer, Integer> inventory) {
		this.inventory = inventory;
	}

	@Override
	public List<WorldObject> getDrops() throws WorldObjectFactoryException {
		List<WorldObject> items;
		if (null == inventory) {
			items = null;
		} else {
			items = new LinkedList<WorldObject>();
			for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
				if (null != entry.getKey() && null != entry.getValue()) {
					WorldObject item = woService.createWorldObject(entry.getKey(), entry.getValue());
					items.add(item);
				}
			}
		}
		
		return items;
	}

}
