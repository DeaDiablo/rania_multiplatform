package com.game.rania.model;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.element.HUDStaticObject;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Radar extends HUDStaticObject{
	
	private Vector<Object> objects = new Vector<Object>();
	private float radius = 0.0f; 
	private float mulScale = 1.0f;
	private Vector2 posObject = new Vector2();
	private Vector2 scaleObject = new Vector2();

	public Radar(float x, float y, float radius, float mulScale) {
		super(RegionID.RADAR, x, y);
		this.radius = radius;
		this.mulScale = mulScale;
	}
	
	public void addObject(Object object){
		objects.add(object);
	}
	
	public void removeObject(Object object){
		objects.remove(object);
	}
	
	public void removeObject(int num){
		objects.remove(num);
	}
	
	@Override
	public boolean draw(SpriteBatch sprite){
		if (!super.draw(sprite))
			return false;
		
		for (Object object : objects) {
			posObject.set(object.position);
			posObject.mul(scale.x * region.getRegionWidth() / radius, scale.y * region.getRegionHeight() / radius);
			posObject.add(position);
			
			scaleObject.set(object.scale.x, object.scale.y);
			scaleObject.mul(scale.x * mulScale * region.getRegionWidth() / radius, scale.y * mulScale * region.getRegionHeight() / radius);
			
			object.draw(sprite, posObject, object.angle, scaleObject, object.color);
		}
		
		return true;
	}
	
}
