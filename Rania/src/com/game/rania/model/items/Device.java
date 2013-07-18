package com.game.rania.model.items;

public class Device extends Item {
	
	public class Type {
		public static final int none          = 0;
		public static final int body        = 1;
		public static final int engine          = 2;
		public static final int fuelbag        = 3;
		public static final int droid          = 4;
		public static final int shield        = 5;
		public static final int hyper          = 6;
		public static final int radar        = 7;
		public static final int weapon          = 8;
	}

    public int 		deviceType;
    public String 	vendorStr;
    public int 		durability;
    
	public Device(float posX, float posY) {
		super(0, 0);
	}
	
	public Device() {
		super(0, 0);
	}

    public Device(Item item)
    {
    	super(0, 0);
        this.id = item.id;
        this.description = item.description;
        this.itemType = item.itemType;
        this.volume = item.volume;
        this.region = item.region;
    }
}
