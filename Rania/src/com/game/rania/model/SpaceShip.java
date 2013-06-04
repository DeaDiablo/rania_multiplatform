package com.game.rania.model;

import com.game.rania.RaniaGame;
import com.game.rania.model.element.DynamicObject;
import com.game.rania.model.element.RegionID;

public class SpaceShip extends DynamicObject{

	public SpaceShip(float posX, float posY){
		super(RaniaGame.mView.getTextureRegion(RegionID.SHIP), posX, posY);
	}
	
	public SpaceShip(float posX, float posY, float rotAngle){
		super(RaniaGame.mView.getTextureRegion(RegionID.SHIP), posX, posY, rotAngle);
	}
	
	public SpaceShip(float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(RaniaGame.mView.getTextureRegion(RegionID.SHIP), posX, posY, rotAngle, scaleX, scaleY);
	}
}
