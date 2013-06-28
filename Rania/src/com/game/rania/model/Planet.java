package com.game.rania.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.game.rania.controller.Controllers;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Planet extends Object{

	public int id				= -1;
	public int type      		= -1;
	public int speed			=  0;
	public int orbit			=  0;
	public int radius			=  0;
	public int atmosphere		= -1;
	public String name    	    = "";
	public int  idLocation   	= -1;
	public Star star 			= null;
	
	private static float radianAndTime = MathUtils.PI / 180.0f / 3600.0f; 
	private float time = 0.0f;

	public Planet(int id, String name, int type, int radius, int atmosphere, int speed, int orbit, int idLocation) {
		super(RegionID.fromInt(RegionID.PLANET_0.ordinal() + type), 0, 0);
		this.id = id;
		this.name = name;
		this.type = type;
		this.radius = radius;
		this.atmosphere = atmosphere;
		this.speed = speed;
		this.orbit = orbit;
		this.idLocation = idLocation;
		this.star = Controllers.locController.getLocation(idLocation).star;
		zIndex = 6;
		updatePosition();
	}
	
	public void updatePosition(){
		time = Controllers.netController.getServerTime();
		calcPosition(time);
		if (region != null)
			scale.set(2.0f * radius / region.getRegionWidth(), 2.0f * radius / region.getRegionHeight());
	}
	
	@Override
	public void update(float deltaTime){
		time += deltaTime;
		calcPosition(time);
	}
	
	@Override
	public boolean draw(SpriteBatch sprite){
		if (star == null)
			return false;
		return super.draw(sprite);
	}
	
	private void calcPosition(float currentTime) {
		if (star == null)
			return;
		position.set(MathUtils.cos(speed * currentTime * radianAndTime), 
					 MathUtils.sin(speed * currentTime * radianAndTime));
		position.mul(orbit);
		position.add(star.position);
		angle = speed * currentTime + 45.0f;
	}
}
