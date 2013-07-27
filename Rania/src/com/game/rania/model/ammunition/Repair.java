package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Repair extends Ammunition{

	protected static final float repairTime = 2.0f;
	protected Object attacker, target;
	
	public Repair(Object attacker, Object target, Color repairColor){
		super(repairTime, RegionID.REPAIR, 0, 0);
		this.attacker = attacker;
		this.target = target;
		color.set(repairColor);
		vAlign = Align.TOP;
	}
	
	protected Vector2 scaleY = new Vector2();
	protected static final float scaleTime = 2.0f;
	
	@Override
	public void update(float delta){
		super.update(delta);
		position.set(target.position);
		if (region != null) {
			if (dTime < scaleTime)
				scaleY.mul(dTime / scaleTime);
			scale.set(1.0f, scaleY.len() / region.getRegionHeight());
		}
	}
}
