package com.game.rania.model.items;

import com.game.rania.model.element.Object;

public class Item extends Object{
	
	public Item(float posX, float posY) {
		super(0, 0);
	}

	public int id;
    public ItemType itemType;
    public String description;
    public int volume;
    public int region_id;
}
