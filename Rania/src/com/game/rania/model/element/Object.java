package com.game.rania.model.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;

public class Object {

	public boolean	keysObject  = false;
	public boolean	touchObject = false;
	public boolean	allTouchObject = false;
	public boolean  visible     = true;
	public Vector2  position    = new Vector2(0.0f, 0.0f);
	public float	angle       = 0.0f;
	public Vector2	scale       = new Vector2(1.0f, 1.0f);
	public Color	color       = new Color(1.0f, 1.0f, 1.0f, 1.0f);

	public TextureRegion region = null;
	
	public Object(float posX, float posY, float rotAngle, float scaleX, float scaleY){
		position.set(posX, posY);
		angle = rotAngle;
		scale.set(scaleX, scaleY);
	}
	
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
	
	public HUDStaticObject asHUDStaticObject(){
		return null;
	}

	public HUDDynamicObject asHUDDynamicObject(){
		return null;
	}
	
	public float getWidth(){
		return region.getRegionWidth() * scale.x;
	}
	
	public float getHeight(){
		return region.getRegionHeight() * scale.y;
	}

	public boolean intersectObject(float x, float y){
		if (region == null)
			return false;
		float width = getWidth();
		float height = getHeight();
		Rectangle rect = new Rectangle(position.x - width * 0.5f,
								       position.y - height * 0.5f,
								       width,
								       height);
		Vector2 point = new Vector2(x, y);
		point.sub(position);
		point.rotate(angle);
		point.add(position);
		return rect.contains(point.x, point.y);
	}
	
	//keyboard
	public boolean keyDown(int keycode) {
		return false;
	}

	public boolean keyUp(int keycode) {
		return false;
	}

	public boolean keyTyped(char character) {
		return true;
	}
	
	//touch
	public boolean touchDown(float x, float y) {
		return false;
	}
	
	public boolean touchDragged(float x, float y) {
		return false;
	}
	
	public boolean touchUp(float x, float y) {
		return false;
	}
	
	//update and draw
	public void update(float deltaTime){
	}
	
	public boolean draw(SpriteBatch sprite){
		if (!visible)
			return false;
		sprite.setColor(color);
		return drawRegion(sprite, region);
	}
	
	public boolean draw(SpriteBatch sprite, Vector2 position, float angle, Vector2 scale, Color color){
		if (!visible)
			return false;
		sprite.setColor(color);
		return drawRegion(sprite, region, position, angle, scale);
	}
	
	public boolean draw(ShapeRenderer shape){
		return true;
	}

	protected boolean drawRegion(SpriteBatch sprite, TextureRegion textureRegion){
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
