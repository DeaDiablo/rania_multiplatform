package com.game.rania.model;

public class Player extends User{

	public int idLocation;

	public Player(int id, int idLoc ,float posX, float posY, String ShipName, String PilotName) {
		super(id, posX, posY, ShipName, PilotName);
		idLocation = idLoc;
	}
}
