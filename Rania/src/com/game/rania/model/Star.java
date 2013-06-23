package com.game.rania.model;

import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.StaticObject;

public class Star extends StaticObject{

	public Star(RegionID id, float x, float y, int radius) {
		super(id, x, y);

		if (region != null)
			scale.set(2.0f * radius / region.getRegionWidth(), 2.0f * radius / region.getRegionHeight());
	}
}
