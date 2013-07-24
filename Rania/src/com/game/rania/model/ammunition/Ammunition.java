package com.game.rania.model.ammunition;

import com.game.rania.model.Indexes;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.TempObject;

public class Ammunition extends TempObject{

	public Ammunition(float time, RegionID id, float posX, float posY) {
		super(time, id, posX, posY);
		zIndex = Indexes.ammunition;
	}

}
