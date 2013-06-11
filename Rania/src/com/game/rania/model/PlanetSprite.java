package com.game.rania.model;

import com.game.rania.RaniaGame;
import com.game.rania.model.element.DynamicObject;
import com.game.rania.model.element.RegionID;

public class PlanetSprite extends DynamicObject{

	private static double radianAndTime = Math.PI / 180.0 / 3600.0; 
	public Planet planet = null;
	private double time = 0.0f;

	public PlanetSprite(Planet planet) {
		super(RegionID.fromInt(RegionID.PLANET_0.ordinal() + planet.id), 0, 0);
		this.planet = planet;
		color = planet.color;

		time = RaniaGame.mClient.getServerTime();
		calcPosition(time);
		if (region != null)
			scale.set((float)planet.radius / region.getRegionWidth(), (float)planet.radius / region.getRegionHeight());
	}
	
	@Override
	public void update(float deltaTime){
		time += deltaTime;
		calcPosition(time);
	}
	
	private void calcPosition(double currentTime) {
		position.set((float)Math.cos(planet.speed * currentTime * radianAndTime), 
					 (float)Math.sin(planet.speed * currentTime * radianAndTime));
		position.mul(planet.orbit);
		angle = planet.speed * (float)currentTime + 45.0f;
	}
}
