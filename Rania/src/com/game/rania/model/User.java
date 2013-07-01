package com.game.rania.model;

public class User extends SpaceShip{

	public int id;
	public String pilotName;
	public int Domain;

	public User(int Id, float posX, float posY, String ShipName, String PilotName, int domain) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		Domain = domain;
		zIndex = Indexes.users;
	}
}
