package com.game.rania.model.element;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class DynamicObject extends Object{
	
	private Vector2 targetPosition = new Vector2(0, 0);
	private Vector2 moveVec        = new Vector2(0, 0);
	private Vector2 addVec         = new Vector2(0, 0);
	private boolean move           = false;
	private float   speed 		   = 1.0f;
	
	public DynamicObject(float posX, float posY, float rotAngle, float scaleX, float scaleY){
		super(posX, posY, rotAngle, scaleX, scaleY);
	}
	
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
	
	public void setPositionTarget(Vector2 target){
		setPositionTarget(target.x, target.y);
	}
	
	public void setPositionTarget(float x, float y){
		targetPosition.set(x, y);

		moveVec.set(targetPosition);
		moveVec.sub(position);
		moveVec.nor();
		moveVec.mul(speed * 100);

		move = true;
	}
	
	@Override
	public void update(float deltaTime){
		if (!move)
			return;

		addVec.set(moveVec);
		addVec.mul(deltaTime);

		if (!targetPosition.epsilonEquals(position, addVec.len())){
			position.add(addVec);
		}
		else {
			moveVec.set(0.0f, 0.0f);
			position.set(targetPosition);
			move = false;
		}
		angle = (float)Math.toDegrees(Math.atan2(-addVec.x, addVec.y));
	}
}
