package com.game.rania.model;

import com.game.rania.RaniaGame;
import com.game.rania.model.element.DynamicObject;
import com.game.rania.model.element.RegionID;

public class SpaceShip extends DynamicObject{

	public String shipName;

	public SpaceShip(float posX, float posY, String ShipName){
		super(RaniaGame.mView.getTextureRegion(RegionID.SHIP), posX, posY);
		shipName = ShipName;
	}
	
	public SpaceShip(float posX, float posY, float rotAngle, String ShipName){
		super(RaniaGame.mView.getTextureRegion(RegionID.SHIP), posX, posY, rotAngle);
		shipName = ShipName;
	}
	
	public SpaceShip(float posX, float posY, float rotAngle, float scaleX, float scaleY, String ShipName){
		super(RaniaGame.mView.getTextureRegion(RegionID.SHIP), posX, posY, rotAngle, scaleX, scaleY);
		shipName = ShipName;
	}
}
