package com.game.rania.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.rania.RaniaGame;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.StaticObject;

public class PlanetSprite extends StaticObject{

	private static double radianAndTime = Math.PI / 180.0 / 3600.0; 
	public Planet planet = null;
	private double time = 0.0f;

	public PlanetSprite(Planet planet) {
		super(RegionID.fromInt(RegionID.PLANET_0.ordinal() + planet.id), 0, 0);
		this.planet = planet;

		time = RaniaGame.mClient.getServerTime();
		calcPosition(time);
		if (region != null)
			scale.set((float)planet.radius / region.getRegionWidth(), (float)planet.radius / region.getRegionHeight());
	}
	
	@Override
	public boolean draw(SpriteBatch sprite){
		if (!visible)
			return false;

		time += Gdx.graphics.getDeltaTime();
		calcPosition(time);
		
		sprite.setColor(planet.color);
		return super.drawRegion(sprite, region, position, angle, scale);
	}
	
	private void calcPosition(double currentTime) {
		position.set((float)Math.cos(planet.speed * currentTime * radianAndTime), 
					 (float)Math.sin(planet.speed * currentTime * radianAndTime));
		position.mul(planet.orbit);
		angle = planet.speed * (float)currentTime + 45.0f;
	}
}
