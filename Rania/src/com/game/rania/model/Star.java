package com.game.rania.model;

import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.Object;

public class Star extends Object{

	public int type;
	public int id;
	
	public Star(int id, int type, float x, float y, int radius) {
		super(RegionID.STAR, x, y);
		this.type = type;
		zIndex = Indexes.star;
		if (region != null)
			scale.set(2.0f * radius / region.getRegionWidth(), 2.0f * radius / region.getRegionHeight());
	}
}
