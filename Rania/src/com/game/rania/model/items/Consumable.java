package com.game.rania.model.items;

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
}
