package com.game.rania.model.items;

public class Weapon extends Device{

	public class Type {
		public static final int Laser	= 0;
		public static final int Rocket	= 1;
		public static final int BFG		= 2;
	}
	
	public Weapon(float posX, float posY) {
		super(posX, posY);
	}

	public Weapon() {
		super(0, 0);
	}

	public int weaponType;
    public int radius;
    public int power;
    public int time_start;
    public int time_reload;
    
    public Weapon(Device dev)
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
