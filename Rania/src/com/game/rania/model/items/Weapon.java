package com.game.rania.model.items;

public class Weapon extends Device{

	public Weapon(float posX, float posY) {
		super(posX, posY);
	}

	public int weaponType;
    public int radius;
    public int power;
    public int time_start;
    public int time_reload;
}
