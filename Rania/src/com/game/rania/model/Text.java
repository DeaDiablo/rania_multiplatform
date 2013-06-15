package com.game.rania.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.element.HUDStaticObject;

public class Text extends HUDStaticObject{

	public String 	  content = null;
	public BitmapFont font = null;
	
	public Text(String text, BitmapFont font, Color color, float x, float y){
		super(x, y, 0, 1, 1);
		this.content = text;
		this.font = font;
		this.color = color;
	}

	protected Matrix4	 transformMatrix = new Matrix4();
	protected Matrix4	 bufferMatrix = new Matrix4();
	protected TextBounds textBound 	  = new TextBounds();
	
	public TextBounds getTextBound(){
		font.setScale(scale.x, scale.y);
		font.getBounds(content, textBound);
		return textBound;
	}
	
	public TextBounds getTextBound(int startChar, int finishChar){
		font.setScale(scale.x, scale.y);
		font.getBounds(content, startChar, finishChar, textBound);
		return textBound;
	}

	public float getLeft(){
		font.setScale(scale.x, scale.y);
		font.getBounds(content, textBound);
		return position.x - textBound.width * 0.5f;
	}
	
	public float getBottom(){
		font.setScale(scale.x, scale.y);
		font.getBounds(content, textBound);
		return position.y + textBound.height * 0.5f;
	}
	
	@Override
	public boolean draw(SpriteBatch sprite){
		return draw(sprite, position, angle, scale, color);
	}
	
	@Override
	public boolean draw(SpriteBatch sprite, Vector2 position, float angle, Vector2 scale, Color color){
		if (!visible || content.isEmpty())
			return false;

		font.getBounds(content, textBound);

		sprite.setTransformMatrix(transformMatrix.setToRotation(0, 0, 1, angle));
		font.setColor(color);
		font.setScale(scale.x, scale.y);
		font.draw(sprite, content, position.x - textBound.width * 0.5f, position.y + textBound.height * 0.5f);
		sprite.getTransformMatrix().idt();

		return true;
	}
	
	public boolean draw(SpriteBatch sprite, float x, float y){
		return draw(sprite, x, y, 0, 1, 1);
	}
	
	public boolean draw(SpriteBatch sprite, float x, float y, float angle, float scaleX, float scaleY){
		if (!visible || content.isEmpty())
			return false;

		font.getBounds(content, textBound);
		
		transformMatrix.setToRotation(0, 0, 1, angle);
		transformMatrix.mul(bufferMatrix.setToTranslation(x, y, 0));
		transformMatrix.mul(bufferMatrix.setToRotation(0, 0, 1, this.angle));

		sprite.setTransformMatrix(transformMatrix);
		font.setColor(color);
		font.setScale(scale.x * scaleX, scale.y * scaleY);
		font.draw(sprite, content, -textBound.width * 0.5f, textBound.height * 0.5f);
		sprite.setTransformMatrix(transformMatrix.idt());
		
		return true;
	}
}