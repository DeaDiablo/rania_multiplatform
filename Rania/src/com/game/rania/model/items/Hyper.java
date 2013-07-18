package com.game.rania.model.items;

public class Hyper extends Device{

	public Hyper(float posX, float posY) {
		super(posX, posY);
	}

	public Hyper() {
		super(0, 0);
	}

	public int radius;
    public int time_start;
    public int time_reload;
    
    public Hyper(Device dev)
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
