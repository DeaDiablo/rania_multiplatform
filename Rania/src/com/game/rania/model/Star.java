package com.game.rania.model;

import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.StaticObject;

public class Star extends StaticObject{

	public Star(RegionID id, int radius) {
		super(id, 0, 0);

		if (region != null)
			scale.set((float)radius / region.getRegionWidth(), (float)radius / region.getRegionHeight());
	}
}
