package com.ao.model.worldobject;

import com.ao.model.character.Character;
import com.ao.model.worldobject.properties.ItemProperties;
import com.ao.model.worldobject.properties.KeyProperties;

public class Key extends AbstractItem {

	public Key(ItemProperties properties, int amount) {
		super(properties, amount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item clone() {
		return new Key((ItemProperties) properties, amount);
	}

	@Override
	public void use(Character character) {
		// TODO Auto-generated method stub
		
	}
	
	public int getCode() {
		return ((KeyProperties) properties).getCode();
	}

}
