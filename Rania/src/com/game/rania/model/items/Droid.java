package com.game.rania.model.items;

public class Droid extends Device{

	public Droid(float posX, float posY) {
		super(posX, posY);
	}

	public Droid() {
		super(0, 0);
	}

	public int power;
    public int time_reload;
    
    public Droid(Device dev)
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
}
