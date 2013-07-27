package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Rocket extends Ammunition{

	protected static final float rocketTime = 0.5f;
	
	protected Object attacker, target;
	
	public Rocket(Object attacker, Object target, Color rocketColor){
		super(rocketTime, RegionID.LASER, 0, 0);
		this.attacker = attacker;
		this.target = target;
		color.set(rocketColor);
		vAlign = Align.TOP;
	}
	
	protected Vector2 scaleY = new Vector2();
	protected static final float scaleTime = 0.15f;
	
	@Override
	public void update(float delta){
		super.update(delta);
		position.set(attacker.position);
		angle = (float)Math.toDegrees(Math.atan2(-(target.position.x - attacker.position.x), (target.position.y - attacker.position.y)));
		if (region != null) {
			scaleY.set(target.position);
			scaleY.sub(attacker.position);
			if (dTime < scaleTime)
				scaleY.mul(dTime / scaleTime);
			scale.set(1.0f, scaleY.len() / region.getRegionHeight());
		}
	}
}
