package com.game.rania.model.element;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StaticObject extends Object{

	public StaticObject(float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(posX, posY, rotAngle, scaleX, scaleY);
	}
	
	public StaticObject(RegionID id, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(id, posX, posY, rotAngle, scaleX, scaleY);
	}

	public StaticObject(RegionID id, float posX, float posY, float rotAngle){
		super(id, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public StaticObject(RegionID id, float posX, float posY){
		super(id, posX, posY, 0.0f, 1.0f, 1.0f);
	}
	
	public StaticObject(TextureRegion textureRegion, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(textureRegion, posX, posY, rotAngle, scaleX, scaleY);
	}

	public StaticObject(TextureRegion textureRegion, float posX, float posY, float rotAngle){
		super(textureRegion, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public StaticObject(TextureRegion textureRegion, float posX, float posY){
		super(textureRegion, posX, posY, 0.0f, 1.0f, 1.0f);
	}
	
	@Override
	public StaticObject asStaticObject(){
		return this;
	}
}
