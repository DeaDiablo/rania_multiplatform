package com.game.rania.model;

import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class SpaceShip extends Object{

	public String   shipName;

	public SpaceShip(float posX, float posY, String ShipName){
		super(RegionID.SHIP, posX, posY);
		shipName = ShipName;
	}
	
	public SpaceShip(float posX, float posY, float rotAngle, String ShipName){
		super(RegionID.SHIP, posX, posY, rotAngle);
		shipName = ShipName;
	}
	
	public SpaceShip(float posX, float posY, float rotAngle, float scaleX, float scaleY, String ShipName){
		super(RegionID.SHIP, posX, posY, rotAngle, scaleX, scaleY);
		shipName = ShipName;
	}

	private Vector2 targetPosition = new Vector2(0, 0);
	private Vector2 moveVec        = new Vector2(0, 0);
	private Vector2 addVec         = new Vector2(0, 0);
	private boolean move           = false;
	private float   speed          = 5.0f;

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
