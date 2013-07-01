package com.game.rania.model;

public class Player extends User{

	public Player(int id, float posX, float posY, String ShipName, String PilotName, int domain) {
		super(id, posX, posY, ShipName, PilotName, domain);
		zIndex = Indexes.player;
	}
}
