package com.game.rania.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.rania.controller.Controllers;
import com.game.rania.model.element.Font;
import com.game.rania.model.items.Equip;

public class User extends SpaceShip{

	protected boolean isPlayer = false;
	public int id;
	public String pilotName;
	public Domain domain;
	public int inPlanet;
	public List<Equip> equips = new ArrayList<Equip>();

	public User(int Id, float posX, float posY, String ShipName, String PilotName, int Domain) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		domain = Controllers.locController.getDomain(Domain);
		inPlanet = 0;
		zIndex = Indexes.users;
		textShip.content = shipName;
	}

	public User(int Id, float posX, float posY, String ShipName, String PilotName, int Domain, int InPlanet) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		domain = Controllers.locController.getDomain(Domain);
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
