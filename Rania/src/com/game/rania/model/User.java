package com.game.rania.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.rania.model.element.Font;

public class User extends SpaceShip{

	protected boolean isPlayer = false;
	public int id;
	public String pilotName;
	public int domain;
	public int inPlanet;

	public User(int Id, float posX, float posY, String ShipName, String PilotName, int Domain) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		domain = Domain;
		inPlanet = 0;
		zIndex = Indexes.users;
		textShip.content = shipName;
	}

	public User(int Id, float posX, float posY, String ShipName, String PilotName, int Domain, int InPlanet) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		domain = Domain;
		inPlanet = InPlanet;
		zIndex = Indexes.users;
		textShip.content = shipName;
	}
	
	protected Text textShip = new Text("", Font.getFont("data/fonts/arial.ttf", 20), new Color(1, 1, 1, 1), 0, 0);
	
	@Override
	public boolean draw(SpriteBatch sprite){
		if (!super.draw(sprite))
			return false;
		if (!isPlayer)
			textShip.draw(sprite, position.x, position.y + region.getRegionHeight() * 0.5f);
		return true;
	}
}
