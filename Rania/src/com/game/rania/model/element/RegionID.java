package com.game.rania.model.element;

public enum RegionID {
	NONE,
	
	//static elements
	BACKGROUND_MENU,
	BACKGROUND_SPACE,
	BACKGROUND_STARS,
	STAR,
	//planets
	PLANET_0,
	PLANET_1,
	PLANET_2,
	PLANET_3,
	PLANET_4,
	PLANET_5,
	PLANET_6,
	PLANET_7,
	PLANET_8,
	PLANET_9,
	PLANET_10,
	PLANET_11,
	PLANET_12,
	PLANET_13,
	PLANET_14,
	PLANET_15,
	PLANET_16,
	PLANET_17,
	
	//dynamic elements
	SHIP;
	
	public static RegionID fromInt(int pos){
		return RegionID.values()[pos];
	}
}
