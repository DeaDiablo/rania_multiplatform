package com.game.rania.model.items;

public class Weapon extends Device{

	public class Type {
		public static final int Laser	= 0;
		public static final int Rocket	= 1;
		public static final int BFG		= 2;
	}

	public int weaponType;
	public int radius;
	public int power;
	public int time_start;
	public int time_reload;
	
	public Weapon() {
		super();
	}
	
	public Weapon(Device device) {
		super(device);
	}
}
