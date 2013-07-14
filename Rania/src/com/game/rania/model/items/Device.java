package com.game.rania.model.items;

import com.game.rania.model.Planet;

public class Device extends Item {
	public Device(float posX, float posY) {
		super(0, 0);
	}
	
	public Planet vendor;
    public int deviceType;
    public String vendorStr;
    public int durability;
}
