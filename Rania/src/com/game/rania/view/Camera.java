package com.game.rania.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class Camera extends OrthographicCamera{
	
	public Camera(float width, float height){
		super(width, height);
	}
	
	public Camera(float width){
		super(width, width * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
	}
	
	public void SetNewSize(float width, float height){
		setToOrtho(false, width, height);
		position.set(0.0f, 0.0f, 0.0f);
		update();
	}

	public float getWidth(){
		return viewportWidth * zoom;
	}
	
	public float getHeight(){
		return viewportHeight * zoom;
	}
	
	public float getLeft(){
		return position.x - viewportWidth * zoom * 0.5f;
	}
	
	public float getRight(){
		return position.x + viewportWidth * zoom * 0.5f;
	}
	
	public float getBottom(){
		return position.y - viewportHeight * zoom * 0.5f;
	}
	
	public float getTop(){
		return position.y + viewportHeight * zoom * 0.5f;
	}
	
	public void toCameraCoord(Vector2 coord){
		coord.x = coord.x * zoom * viewportWidth / Gdx.graphics.getWidth() + getLeft();
		coord.y = coord.y * zoom * viewportHeight / Gdx.graphics.getHeight() + getBottom();
	}
	
	public void toScreenCoord(Vector2 coord){
		coord.x = (coord.x - getLeft()) / (zoom * viewportWidth / Gdx.graphics.getWidth());
		coord.y = (coord.y - getBottom()) / (zoom * viewportHeight / Gdx.graphics.getHeight());
	}
}
