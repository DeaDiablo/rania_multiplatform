package com.game.rania.model.items;

public class Consumable extends Item{
	
	public class Type
	{
		public static final int fuel = 1;
	}

	public Consumable(float posX, float posY) {
		super(posX, posY);
	}

	public Consumable() {
		super(0, 0);
	}
}
