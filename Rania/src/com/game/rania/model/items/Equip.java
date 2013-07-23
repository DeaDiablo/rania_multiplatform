package com.game.rania.model.items;

public class Equip<T extends Item> {

	public Equip() {
	}

	public Equip(Equip<Item> equip, Class<T> type) {
		item = type.cast(equip.item);
		in_use = equip.in_use;
		wear = equip.wear;
		location = equip.location;
		num = equip.num;
	}

	public int id = 0;
	public T item = null;
	public boolean in_use = false;
	public int wear = -1;
	public int location = -1;
	public int num = 0;
	public float last_use;
}
