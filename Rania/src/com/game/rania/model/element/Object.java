package com.game.rania.model.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;

public class Object {
	
	public boolean  visible  = true;
	public Vector2  position = new Vector2(0.0f, 0.0f);
	public float	angle    = 0.0f;
	public Vector2	scale    = new Vector2(1.0f, 1.0f);
	public Color	color    = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	
	public TextureRegion region = null;
	
	public Object(RegionID id, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		region = RaniaGame.mView.getTextureRegion(id);
		position.set(posX, posY);
		angle = rotAngle;
		scale.set(scaleX, scaleY);
	}
	
	public Object(TextureRegion textureRegion, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		region = textureRegion;
		position.set(posX, posY);
		angle = rotAngle;
		scale.set(scaleX, scaleY);
	}
	
	public DynamicObject asDynamicObject(){
		return null;
	}
	
	public StaticObject asStaticObject(){
		return null;
	}
	
	public HUDObject asHUDObject(){
		return null;
	}

	public void update(float deltaTime){
	}
	
	public boolean draw(SpriteBatch sprite){
		if (!visible)
			return false;
		sprite.setColor(color);
		return drawRegion(sprite, region, position, angle, scale);
	}
	
	public boolean draw(SpriteBatch sprite, Vector2 position, float angle, Vector2 scale, Color color){
		if (!visible)
			return false;
		sprite.setColor(color);
		return drawRegion(sprite, region, position, angle, scale);
	}

	protected boolean drawRegion(SpriteBatch sprite, TextureRegion textureRegion, Vector2 position, float angle, Vector2 scale){
		if (textureRegion == null)
			return false;

		sprite.draw(textureRegion, 
					position.x - textureRegion.getRegionWidth() * 0.5f,
					position.y - textureRegion.getRegionHeight() * 0.5f,
					textureRegion.getRegionWidth() * 0.5f,
					textureRegion.getRegionHeight() * 0.5f,
					textureRegion.getRegionWidth(),
					textureRegion.getRegionHeight(),
					scale.x,
					scale.y,
					angle);
		
		return true;
	}
}
