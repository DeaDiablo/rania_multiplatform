package com.game.rania.model.items;

import com.game.rania.model.element.Object;

public class Item extends Object{
	
	public Item(float posX, float posY) {
		super(0, 0);
	}
	
	public int id				= -1;
	public int type      		= -1;
	public String description   = "";
	public int power			=  0;
	public int weight      		=  0;
	public int vendor			= -1;
	public int region_id		= -1;
}
