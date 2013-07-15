package com.game.rania.model.items;

public class Radar extends Device{

	public Radar(float posX, float posY) {
		super(posX, posY);
	}

	public Radar() {
		super(0, 0);
	}

	public int radius;
    public int defense;
    
    public Radar(Device dev)
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
    public Radar getRadar()
    {
        return this;
    }
}
