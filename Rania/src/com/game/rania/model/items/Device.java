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

    public Device(Item item)
    {
    	super(0, 0);
        this.id = item.id;
        this.description = item.description;
        this.itemType = item.itemType;
        this.volume = item.volume;
        this.region = item.region;
    }
    
    @Override
	public Device getDevice() {
		return this;
	}

	@Override
	public Ship getShip() {
		return null;
	}

	@Override
	public Engine getEngine() {
		return null;
	}

	@Override
	public Fuelbag getFuelbag() {
		return null;
	}

	@Override
	public Droid getDroid() {
		return null;
	}

	@Override
	public Hyper getHyper() {
		return null;
	}

	@Override
	public Radar getRadar() {
		return null;
	}

	@Override
	public Shield getShield() {
		return null;
	}

	@Override
	public Weapon getWeapon() {
		return null;
	}
}
