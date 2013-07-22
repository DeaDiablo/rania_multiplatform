package com.game.rania.model.items;

import com.game.rania.model.element.Object;

public abstract class Item extends Object{
	
	public class Type {
		public static final int none          = 0;
		public static final int device        = 1;
		public static final int consumable    = 2;
	}

	public Item(float posX, float posY) {
		super(0, 0);
	}

	public int id;
    public int itemType;
    public String description;
    public int volume;
    public int region_id;
    public int packing;
    public int use_only;
    public int price;
}
