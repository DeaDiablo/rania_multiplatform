package com.game.rania.model.items;

public class Ship extends Device{

	public Ship(float posX, float posY) {
		super(posX, posY);
	}

	public Ship() {

	}

	public int slot_weapons;
    public int slot_droids;
    public int slot_shield;
    public int slot_hyper;
}
