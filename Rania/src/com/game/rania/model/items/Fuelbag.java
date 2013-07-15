package com.game.rania.model.items;

public class Fuelbag extends Device{

	public Fuelbag(float posX, float posY) {
		super(posX, posY);
	}

	public Fuelbag() {
		super(0, 0);
	}

	public int compress;
	
	public Fuelbag(Device dev)
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
    public Fuelbag getFuelbag()
    {
        return this;
    }
}
