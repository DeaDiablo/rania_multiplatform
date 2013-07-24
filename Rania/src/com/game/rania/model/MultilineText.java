package com.game.rania.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MultilineText extends Text{
	
	public MultilineText(String text, BitmapFont font, Color color, float x, float y){
		super(text, font, color, x, y);
	}
	
	public MultilineText(String text, BitmapFont font, Color color, float x, float y, Align horzAlign, Align vertAligin){
		super(text, font, color, x, y, horzAlign, vertAligin);
	}
	
	@Override
	public TextBounds getTextBound(){
		font.setScale(scale.x, scale.y);
		font.getMultiLineBounds(content, textBound);
		return textBound;
	}

	public float getLeft(){
		font.setScale(scale.x, scale.y);
		font.getMultiLineBounds(content, textBound);
		return position.x - textBound.width * 0.5f;
	}
	
	public float getBottom(){
		font.setScale(scale.x, scale.y);
		font.getMultiLineBounds(content, textBound);
		return position.y + textBound.height * 0.5f;
	}
	
	@Override
	protected void draw(SpriteBatch sprite, String string){
		font.getMultiLineBounds(string, textBound);
		calcOffset(textBound.width, textBound.height);
		sprite.setTransformMatrix(transformMatrix);
		font.drawMultiLine(sprite, string, offset.x, -offset.y);
		sprite.setTransformMatrix(transformMatrix.idt());
	}
}