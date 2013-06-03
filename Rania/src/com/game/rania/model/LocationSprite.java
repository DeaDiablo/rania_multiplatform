package com.game.rania.model;

import java.util.HashMap;

import com.game.rania.RaniaGame;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.RegionID;

public class LocationSprite extends Group{

	public Location location;
		
	public LocationSprite(Location location){
		super();
		this.location = location;

		AddElement(new Star(RegionID.STAR, location.starRadius));

		RaniaGame.mUser.isWorkReciver = false;
		HashMap<String, Planet> planets = RaniaGame.nController.GetCurrentPlanets(RaniaGame.mUser);
		for (Planet planet : planets.values()) {
			AddElement(new PlanetSprite(planet));
		}
		RaniaGame.mUser.isWorkReciver = true;
	}
}
