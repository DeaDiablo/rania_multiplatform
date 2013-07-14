package com.game.rania.model.items;

public class Device extends Item {
	public Device(float posX, float posY) {
		super(0, 0);
	}
	
	public Device() {
		super(0, 0);
	}

    public int deviceType;
    public String vendorStr;
    public int durability;
}
