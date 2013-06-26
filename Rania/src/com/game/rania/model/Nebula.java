package com.game.rania.model;

import com.game.rania.model.element.RegionID;

public class Nebula extends ParallaxObject{

	public int id;
	
	public Nebula(int id, int type, float x, float y, float angle, float scale) {
		super(RegionID.fromInt(RegionID.NEBULA_0.ordinal() + type), x, y, angle, scale, scale, -0.4f);
		this.id = id;
	}
}
