package com.game.rania.model.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.model.Indexes;

public class Object {
	
	public static Shader currentShader = null;

	public boolean keysObject = false;
	public boolean touchObject = false;
	public boolean allTouchObject = false;
	public boolean visible = true;
	public Vector2 position = new Vector2(0.0f, 0.0f);
	public float angle = 0.0f;
	public Vector2 scale = new Vector2(1.0f, 1.0f);
	public Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	public int zIndex = Indexes.object;
	public Shader shader = null;

	public RegionID regionID = RegionID.NONE;
	public TextureRegion region = null;

	public Object(){
		this(0, 0, 0, 1, 1);
	}
	
	public Object(float posX, float posY) {
		this(posX, posY, 0, 1, 1);
	}

	public Object(float posX, float posY, float rotAngle){
		this(posX, posY, rotAngle, 1, 1);
	}

	public Object(float posX, float posY, float rotAngle, float scaleX, float scaleY){
		position.set(posX, posY);
		angle = rotAngle;
		scale.set(scaleX, scaleY);
	}

	public Object(RegionID id, float posX, float posY){
		this(id, posX, posY, 0, 1, 1);
	}

	public Object(RegionID id, float posX, float posY, float rotAngle){
		this(id, posX, posY, rotAngle, 1, 1);
	}	
	
	public Object(RegionID id, float posX, float posY, float rotAngle, float scaleX, float scaleY){
		regionID = id;
		region = RaniaGame.mView.getTextureRegion(id);
		position.set(posX, posY);
		angle = rotAngle;
		scale.set(scaleX, scaleY);
	}
	
	public void reloadTexture(){
		region = RaniaGame.mView.getTextureRegion(regionID);
	}
	
	public HUDObject asHUDObject(){
		return null;
	}
	
	public float getMaxSize(){
		return Math.max(region.getRegionWidth() * scale.x, region.getRegionHeight() * scale.y);
	}

	public float getWidth(){
		return region.getRegionWidth() * scale.x;
	}
	
	public float getHeight(){
		return region.getRegionHeight() * scale.y;
	}
	
	public float getLeft(){
		return -region.getRegionWidth() * scale.x * 0.5f;
	}
	
	public float getRight(){
		return region.getRegionWidth() * scale.x * 0.5f;
	}
	
	public float getBottom(){
		return -region.getRegionHeight() * scale.y * 0.5f;
	}
	
	public float getTop(){
		return region.getRegionHeight() * scale.y * 0.5f;
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
	
	public boolean setShader(SpriteBatch sprite){
		if (currentShader == shader)
			return false;
		sprite.setShader(shader);
		currentShader = shader;
		return true;
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
		return drawRegion(sprite, textureRegion, position.x, position.y, angle, scale.x, scale.y);
	}
	
	protected boolean drawRegion(SpriteBatch sprite, TextureRegion textureRegion, Vector2 position, float angle, Vector2 scale){		
		return drawRegion(sprite, textureRegion, position.x, position.y, angle, scale.x, scale.y);
	}
	
	protected boolean drawRegion(SpriteBatch sprite, TextureRegion textureRegion, float x, float y, float angle, float scaleX, float scaleY){
		if (textureRegion == null)
			return false;

		sprite.draw(textureRegion, 
					x - textureRegion.getRegionWidth() * 0.5f,
					y - textureRegion.getRegionHeight() * 0.5f,
					textureRegion.getRegionWidth() * 0.5f,
					textureRegion.getRegionHeight() * 0.5f,
					textureRegion.getRegionWidth(),
					textureRegion.getRegionHeight(),
					scaleX,
					scaleY,
					angle);
		
		return true;
	}
}
