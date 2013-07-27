package com.game.rania.model.items;

public class Droid extends Device{

	public int power;
	public int time_reload;
	public int radius;

	public Droid() {
		super();
	}
	
	public Droid(Device device) {
		super(device);
	}
}
