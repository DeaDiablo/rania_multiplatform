package com.game.rania.model.items;

public class Body extends Device {

	public Body(float posX, float posY) {
		super(posX, posY);
	}

	public Body() {
		super(0, 0);
	}

	public int slot_weapons;
	public int slot_droids;
	public int slot_shield;
	public int slot_hyper;

	public Body(Device dev) {
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
