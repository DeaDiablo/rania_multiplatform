package com.game.rania.model.ammunition;

import com.game.rania.model.Indexes;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.Object;

public class Ammunition extends Object{

	public Ammunition(float time, RegionID id, float posX, float posY) {
		super(id, posX, posY);
		lifeTime = time;
		zIndex = Indexes.ammunition;
	}

}
