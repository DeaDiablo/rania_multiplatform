package com.game.rania.model.items;

import com.game.rania.model.element.Object;

public abstract class Item extends Object{

	public class Type {
		public static final int none          = 0;
		public static final int device        = 1;
		public static final int consumable    = 2;
	}
	
	public Item(){
		super();
	}
	
	public Item(Item item){
		id 			= item.id;
		itemType 	= item.itemType;
		description = item.description;
		volume 		= item.volume;
		region_id 	= item.region_id;
		packing 	= item.packing;
		use_only 	= item.use_only;
		price 		= item.price;
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
