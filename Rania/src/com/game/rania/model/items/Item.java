package com.game.rania.model.items;

import com.game.rania.model.element.Object;

public abstract class Item extends Object{
	
	public Item(float posX, float posY) {
		super(0, 0);
	}

	public int id;
    public int itemType;
    public String description;
    public int volume;
    public int region_id;
    
    public abstract Device getDevice();
    public abstract Ship getShip();
    public abstract Engine getEngine();
    public abstract Fuelbag getFuelbag();
    public abstract Droid getDroid();
    public abstract Hyper getHyper();
    public abstract Radar getRadar();
    public abstract Shield getShield();
    public abstract Weapon getWeapon();
}
