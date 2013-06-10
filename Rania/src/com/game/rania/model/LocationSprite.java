package com.game.rania.model;

import com.game.rania.model.element.Group;
import com.game.rania.model.element.RegionID;

public class LocationSprite extends Group{

	public Location location;
		
	public LocationSprite(Location location){
		super();
		this.location = location;

		AddElement(new ParallaxLayer(RegionID.BACKGROUND_SPACE, -0.75f, 1.0f));
		AddElement(new ParallaxLayer(RegionID.BACKGROUND_STARS, -0.65f, 1.0f));

		AddElement(new Star(RegionID.STAR, location.starRadius));
	}
}
