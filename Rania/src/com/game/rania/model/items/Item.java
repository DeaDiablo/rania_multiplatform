package com.game.rania.model.items;

import com.game.rania.model.element.Object;

public class Item extends Object{
	
	public Item(float posX, float posY) {
		super(0, 0);
	}

	public Item() {
		super(0, 0);
	}
	
	public Device asDevice() {
		return null;
	}
	
	public int id;
    public int itemType;
    public String description;
    public int volume;
    public int region_id;
}
