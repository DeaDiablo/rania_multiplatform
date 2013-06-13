package com.game.rania.model.element;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUDDynamicObject extends DynamicObject{
	
	public HUDDynamicObject(float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(posX, posY, rotAngle, scaleX, scaleY);
	}
	
	public HUDDynamicObject(RegionID id, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(id, posX, posY, rotAngle, scaleX, scaleY);
	}

	public HUDDynamicObject(RegionID id, float posX, float posY, float rotAngle){
		super(id, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public HUDDynamicObject(RegionID id, float posX, float posY){
		super(id, posX, posY, 0.0f, 1.0f, 1.0f);
	}
	
	public HUDDynamicObject(TextureRegion textureRegion, float posX, float posY, float rotAngle, float scaleX, float scaleY) {
		super(textureRegion, posX, posY, rotAngle, scaleX, scaleY);
	}

	public HUDDynamicObject(TextureRegion textureRegion, float posX, float posY, float rotAngle){
		super(textureRegion, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public HUDDynamicObject(TextureRegion textureRegion, float posX, float posY){
		super(textureRegion, posX, posY, 0.0f, 1.0f, 1.0f);
	}

	@Override
	public DynamicObject asDynamicObject(){
		return null;
	}
	
	@Override
	public HUDDynamicObject asHUDDynamicObject(){
		return this;
	}
}
