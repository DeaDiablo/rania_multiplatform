package com.game.rania.model.items;

public class Device extends Item {
	public Device(float posX, float posY) {
		super(0, 0);
	}
	
	public Device() {
		super(0, 0);
	}

	@Override
	public Device asDevice() {
		return this;
	}
	public void setVendor(String str)
	{
		this.vendorPlanet = str;
	}
	
    public int deviceType;
    public String vendorPlanet;
    public int durability;
}
