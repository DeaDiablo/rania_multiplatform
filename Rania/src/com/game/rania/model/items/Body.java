package com.game.rania.model.items;

public class Body extends Device {

	public int slot_weapons;
	public int slot_droids;
	public int slot_shield;
	public int slot_hyper;
	
	public Body(){
		super();
	}

	public Body(Device device) {
		super(device);
	}
}
