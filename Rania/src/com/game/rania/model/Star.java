package com.game.rania.model;

import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.StaticObject;

public class Star extends StaticObject{

	private int type = -1;
	
	public Star(int type, float x, float y, int radius) {
		super(RegionID.STAR, x, y);
		this.type = type;

		if (region != null)
			scale.set(2.0f * radius / region.getRegionWidth(), 2.0f * radius / region.getRegionHeight());
	}
	
	public int getType(){
		return type;
	}
}
