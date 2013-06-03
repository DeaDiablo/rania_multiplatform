package com.game.rania.model.element;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUDObject extends Object{
	
	public HUDObject(RegionID id, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(id, posX, posY, rotAngle, scaleX, scaleY);
	}

	public HUDObject(RegionID id, float posX, float posY, float rotAngle){
		super(id, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public HUDObject(RegionID id, float posX, float posY){
		super(id, posX, posY, 0.0f, 1.0f, 1.0f);
	}
	
	public HUDObject(TextureRegion textureRegion, float posX, float posY, float rotAngle, float scaleX, float scaleY) {
		super(textureRegion, posX, posY, rotAngle, scaleX, scaleY);
	}

	public HUDObject(TextureRegion textureRegion, float posX, float posY, float rotAngle){
		super(textureRegion, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public HUDObject(TextureRegion textureRegion, float posX, float posY){
		super(textureRegion, posX, posY, 0.0f, 1.0f, 1.0f);
	}
	
	public HUDObject asHUDObject(){
		return this;
	}
}
