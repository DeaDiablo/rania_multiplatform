package com.game.rania.model;

import com.game.rania.controller.Controllers;
import com.game.rania.model.element.DynamicObject;
import com.game.rania.model.element.RegionID;

public class Planet extends DynamicObject{

	public int id				= -1;
	public int type      		= -1;
	public int speed			=  0;
	public int orbit			=  0;
	public int radius			=  0;
	public int atmosphere		= -1;
	public String name    	    = "";
	
	private static double radianAndTime = Math.PI / 180.0 / 3600.0; 
	private double time = 0.0f;

	public Planet(int id, String name, int type, int radius, int atmosphere, int speed, int orbit) {
		super(RegionID.fromInt(RegionID.PLANET_0.ordinal() + type), 0, 0);
		this.id = id;
		this.name = name;
		this.type = type;
		this.radius = radius;
		this.atmosphere = atmosphere;
		this.speed = speed;
		this.orbit = orbit;

		time = Controllers.clientController.getServerTime();
		calcPosition(time);
		if (region != null)
			scale.set((float)radius / region.getRegionWidth(), (float)radius / region.getRegionHeight());
	}
	
	@Override
	public void update(float deltaTime){
		time += deltaTime;
		calcPosition(time);
	}
	
	private void calcPosition(double currentTime) {
		position.set((float)Math.cos(speed * currentTime * radianAndTime), 
					 (float)Math.sin(speed * currentTime * radianAndTime));
		position.mul(orbit);
		angle = speed * (float)currentTime + 45.0f;
	}
}
