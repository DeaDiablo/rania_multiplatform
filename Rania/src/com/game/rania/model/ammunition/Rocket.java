package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Rocket extends Ammunition{

	protected static final float rocketTime = 2.0f;
	
	protected Object attacker, target;
	
	public Rocket(Object attacker, Object target, Color rocketColor){
		super(rocketTime, RegionID.ROCKET, 0, 0);
		this.attacker = attacker;
		this.target = target;
		color.set(rocketColor);
		vAlign = Align.CENTER;
	}
	
	protected Vector2 pos = new Vector2();
	protected static final float flyingTime = 2.0f;
	
	@Override
	public void update(float delta){
		super.update(delta);
		angle = (float)Math.toDegrees(Math.atan2(-(target.position.x - attacker.position.x), (target.position.y - attacker.position.y)));
		if (region != null) {
			if (dTime < flyingTime) {
				float progress = dTime/flyingTime;
				float lenX = target.position.x - attacker.position.x; 
				float lenY = target.position.y - attacker.position.y;
				float shiftX = lenX * progress;
				float shiftY = lenY * progress;
				pos.x = attacker.position.x + shiftX;
				pos.y = attacker.position.y + shiftY;
				position.set(pos);
			}
		}
	}
}
