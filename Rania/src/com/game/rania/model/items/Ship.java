package com.game.rania.model.items;

public class Ship extends Device{

	public Ship(float posX, float posY) {
		super(posX, posY);
	}

	public Ship() {
		super(0, 0);
	}

	public int slot_weapons;
    public int slot_droids;
    public int slot_shield;
    public int slot_hyper;
    
    public Ship(Device dev)
    {
    	super(0, 0);
        this.id = dev.id;
        this.description = dev.description;
        this.itemType = dev.itemType;
        this.volume = dev.volume;
        this.region = dev.region;
        this.deviceType = dev.deviceType;
        this.vendorStr = dev.vendorStr;
        this.durability = dev.durability;
    }
    
    @Override
    public Ship getShip()
    {
        return this;
    }
}
