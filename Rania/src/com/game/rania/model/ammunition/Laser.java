package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Laser extends Ammunition{

	protected static final float laserTime = 0.25f;
	
	protected Object attacker, target;
	
	public Laser(Object attacker, Object target, Color laserColor){
		super(laserTime, RegionID.LASER, 0, 0);
		this.attacker = attacker;
		this.target = target;
		color.set(laserColor);
		vAlign = Align.TOP;
	}
	
	protected Vector2 scaleY = new Vector2();
	protected static final float scaleTime = 0.05f;
	
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
