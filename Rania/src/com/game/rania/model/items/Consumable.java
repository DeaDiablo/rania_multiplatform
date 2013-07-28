package com.game.rania.model.items;

import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Consumable extends Item{
	
	public class Type
	{
		public static final int fuel = 1;
	}
	
	public Consumable() {
		super();
	}

	public Consumable(Item item) {
		super(item);
	}
	
	@Override
	public RegionID getIconID(){
		return RegionID.NONE;
	}
	
	@Override
	public boolean use(Object user, Object target){
		return false;
	}
}
