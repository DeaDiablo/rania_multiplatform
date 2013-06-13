package com.game.rania.model.element;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUDStaticObject extends StaticObject{
	
	public HUDStaticObject(float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(posX, posY, rotAngle, scaleX, scaleY);
	}
	
	public HUDStaticObject(RegionID id, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(id, posX, posY, rotAngle, scaleX, scaleY);
	}

	public HUDStaticObject(RegionID id, float posX, float posY, float rotAngle){
		super(id, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public HUDStaticObject(RegionID id, float posX, float posY){
		super(id, posX, posY, 0.0f, 1.0f, 1.0f);
	}
	
	public HUDStaticObject(TextureRegion textureRegion, float posX, float posY, float rotAngle, float scaleX, float scaleY) {
		super(textureRegion, posX, posY, rotAngle, scaleX, scaleY);
	}

	public HUDStaticObject(TextureRegion textureRegion, float posX, float posY, float rotAngle){
		super(textureRegion, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public HUDStaticObject(TextureRegion textureRegion, float posX, float posY){
		super(textureRegion, posX, posY, 0.0f, 1.0f, 1.0f);
	}

	@Override
	public StaticObject asStaticObject(){
		return null;
	}
	
	@Override
	public HUDStaticObject asHUDStaticObject(){
		return this;
	}
}
