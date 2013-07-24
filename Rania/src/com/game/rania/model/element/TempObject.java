package com.game.rania.model.element;

import com.game.rania.RaniaGame;


public class TempObject extends Object{
	
	protected float lifeTime = 0.0f;
	
	public TempObject(float time){
		super();
		lifeTime = time;
	}
	
	public TempObject(float time, RegionID id, float posX, float posY){
		super(id, posX, posY);
		lifeTime = time;
	}
	
	public TempObject(float time, RegionID id, float posX, float posY, float rotAngle){
		super(id, posX, posY, rotAngle);
		lifeTime = time;
	}
	
	public TempObject(float time, RegionID id, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(id, posX, posY, rotAngle, scaleX, scaleY);
		lifeTime = time;
	}
	
	protected float dTime = 0.0f;
	
	@Override
	public void update(float delta){
		dTime += delta;
		if (dTime > lifeTime)
			RaniaGame.mController.removeObject(this);
	}
}
