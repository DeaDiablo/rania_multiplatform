package com.game.rania.model;

import java.util.HashMap;

public class User extends SpaceShip{

	public int id;
	public String pilotName;
	public int Domain;
	public int InPlanet;
	public HashMap<Integer, Item> Equip = null;

	public User(int Id, float posX, float posY, String ShipName, String PilotName, int domain) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		Domain = domain;
		InPlanet = 0;
		zIndex = Indexes.users;
		Equip = new HashMap<Integer, Item>();
	}
	public User(int Id, float posX, float posY, String ShipName, String PilotName, int domain, int inPlanet) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		Domain = domain;
		InPlanet = inPlanet;
		zIndex = Indexes.users;
		Equip = new HashMap<Integer, Item>();
	}
}
