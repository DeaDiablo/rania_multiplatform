package com.game.rania.model.element;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DynamicObject extends Object{
	
	public DynamicObject(RegionID id, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(id, posX, posY, rotAngle, scaleX, scaleY);
	}

	public DynamicObject(RegionID id, float posX, float posY, float rotAngle){
		super(id, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public DynamicObject(RegionID id, float posX, float posY){
		super(id, posX, posY, 0.0f, 1.0f, 1.0f);
	}
	
	public DynamicObject(TextureRegion textureRegion, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(textureRegion, posX, posY, rotAngle, scaleX, scaleY);
	}

	public DynamicObject(TextureRegion textureRegion, float posX, float posY, float rotAngle){
		super(textureRegion, posX, posY, rotAngle, 1.0f, 1.0f);
	}
	
	public DynamicObject(TextureRegion textureRegion, float posX, float posY){
		super(textureRegion, posX, posY, 0.0f, 1.0f, 1.0f);
	}
	
	@Override
	public DynamicObject asDynamicObject(){
		return this;
	}
}
