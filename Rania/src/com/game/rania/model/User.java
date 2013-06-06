package com.game.rania.model;

public class User extends SpaceShip{

	public int id;
	public String pilotName;

	public User(int Id, float posX, float posY, String ShipName, String PilotName) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
	}
}
