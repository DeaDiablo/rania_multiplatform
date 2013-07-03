package com.game.rania.model;

public class User extends SpaceShip{

	public int id;
	public String pilotName;
	public int Domain;
	public int InPlanet;
	public User(int Id, float posX, float posY, String ShipName, String PilotName, int domain) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		Domain = domain;
		InPlanet = 0;
		zIndex = Indexes.users;
	}
	public User(int Id, float posX, float posY, String ShipName, String PilotName, int domain, int inPlanet) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		Domain = domain;
		InPlanet = inPlanet;
		zIndex = Indexes.users;
	}
}
