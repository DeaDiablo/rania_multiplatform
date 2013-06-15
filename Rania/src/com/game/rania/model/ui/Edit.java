package com.game.rania.model.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.model.Text;
import com.game.rania.model.element.HUDDynamicObject;
import com.game.rania.model.element.RegionID;

public class Edit extends HUDDynamicObject{

	protected Text 		    text      = null;
	protected TouchAction   action    = null;
	protected TextureRegion regionOn  = null;
	protected int		    maxTextLength;

	public Edit(TextureRegion texture, TextureRegion textureOn, float x, float y, Text text, int maxLength){
		super(texture, x, y);
		this.regionOn = textureOn;
		this.text = text;
		this.maxTextLength = maxLength;
		touchObject = true;
	}

	public Edit(RegionID idTexture, RegionID idTextureOn, float x, float y, Text text, int maxLength){
		super(idTexture, x, y);
		this.regionOn = RaniaGame.mView.getTextureRegion(idTextureOn);
		this.text = text;
		this.maxTextLength = maxLength;
		touchObject = true;
	}

	public Edit(TextureRegion texture, TextureRegion textureOn, float x, float y, Text text, int maxLength, TouchAction action){
		super(texture, x, y);
		this.regionOn = textureOn;
		this.text = text;
		this.maxTextLength = maxLength;
		this.action = action;
		touchObject = true;
	}
	
	public Edit(RegionID idTexture, RegionID idTextureOn, float x, float y, Text text, int maxLength, TouchAction action){
		super(idTexture, x, y);
		this.regionOn = RaniaGame.mView.getTextureRegion(idTextureOn);
		this.text = text;
		this.maxTextLength = maxLength;
		this.action = action;
		touchObject = true;
	}
	
	public String getText(){
		return text.content;
	}
	
	protected void setText(String newText){
		text.content = newText;
		if (action != null)
			action.execute(true);
	}

	public Object     nextControll = null;
	protected Vector2 bufferPos  = new Vector2();
	protected int     cursorPos  = 0;
	protected boolean focus 	 = false;
	protected boolean oldFocus   = false;
	protected boolean showCursor = false;
	protected boolean fillEdit   = false;
	protected float   updateTime = 0.0f;
	protected float   cursorTime = 0.0f;
	protected float   keyTime    = 0.0f;

	@Override
	public boolean touchDown(float x, float y) {
		float minX = text.getTextBound().width;
		float findX = x + text.getTextBound().width * 0.5f;
		if (findX > minX){
			cursorPos = text.content.length();
			return true;
		}
		cursorPos = 0;
		for(int i = 0; i < text.content.length(); i++){
			float posChar = Math.abs(text.getTextBound(0, i).width - findX);
			if (minX > posChar){
				cursorPos = i;
				minX = posChar;
			}
		}
		return true;
	}
	
	@Override
	public boolean touchDragged(float x, float y) {
		return true;
	}
	
	@Override
	public boolean touchUp(float x, float y) {
		FocusElement.setFocus(this);
		cursorTime = updateTime;
		showCursor = true;
		return true;
	}
	
	@Override
	public void update(float deltaTime){
		updateTime += deltaTime;
		
		if (FocusElement.getFocus() == this)
			focus = true;
		else
			focus = false;
		
		if (focus) {
			if (updateTime - cursorTime > 0.5f){
				cursorTime = updateTime;
				showCursor = !showCursor;
			}
	
			if (keyTime < updateTime){
				switch (key)
				{
				case backspaceKey:
					removeLeftChar();
					break;
	
				case deleteKey:
					removeRightChar();
					break;
	
				case leftKey:
					nextLeftPosition();
					break;
	
				case rightKey:
					nextRightPosition();
					break;
					
				default:
					break;
				}
				
				keyTime = updateTime + 0.1f;
			}
		}
		
		if (focus != oldFocus) {
			oldFocus = focus;
			if (focus) {
				if (Gdx.app.getType() != Application.ApplicationType.Desktop)	{
					Gdx.input.setOnscreenKeyboardVisible(true);
					if (region != null)	{
						bufferPos.set(position);
						position.set(0, getHeight());
						fillEdit = true;
					}
				}
				keysObject = true;
			}
			else {
				if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
					Gdx.input.setOnscreenKeyboardVisible(false);
					if (region != null)	{
						position.set(bufferPos);
						fillEdit = false;
					}
				}
				keysObject = false;
			}
		}
	}

	@Override
	public boolean draw(SpriteBatch sprite){		
		if (!visible)
			return false;
		
		sprite.setColor(color);
		if (focus && regionOn != null)
			drawRegion(sprite, regionOn);
		else
			drawRegion(sprite, region);
		
		if (text != null)
			text.draw(sprite, text.position.x + position.x, text.position.y + position.y, angle, scale.x, scale.y);			
		
		return true;
	}

	@Override
	public boolean draw(ShapeRenderer shape){
		if (!focus || !visible || (!showCursor && !fillEdit))
			return false;

		shape.begin(ShapeType.FilledRectangle);
		
		if (fillEdit) {
			shape.setColor(0, 0, 0, 1);
			shape.filledRect(position.x + text.position.x - getWidth() * 0.5f,
							 position.y + text.position.y - getHeight() * 0.5f,
							 getWidth(),
							 getHeight());
		}
		
		if (showCursor)
		{		
			float widthCursor = text.font.getSpaceWidth() * 0.2f;
			float heightCursor = text.font.getCapHeight();
			shape.setColor(text.color);
			shape.filledRect(position.x + text.position.x + text.getTextBound(0, cursorPos).width - text.getTextBound().width * 0.5f - widthCursor * 0.5f,
						     position.y + text.position.y - heightCursor * 0.5f,
						     widthCursor,
						     heightCursor);
		}
		
		shape.end();
		return true;
	}
	
	@Override
	public boolean keyTyped(char character) {
		if (maxTextLength <= text.content.length())
			return true;
		setText(text.content.substring(0, cursorPos) + character + text.content.substring(cursorPos));
		cursorPos++;
		return true;
	}

	private static final int backspaceKey = 1;
	private static final int deleteKey = 2;
	private static final int leftKey = 4;
	private static final int rightKey = 8;
	protected int key = 0;
	
	@Override
	public boolean keyDown(int keycode) {
		if (key != 0)
			return true;
			
		switch (keycode) {
		case Input.Keys.BACKSPACE:
			removeLeftChar();
			key = backspaceKey;
			break;
			
		case Input.Keys.FORWARD_DEL:
			removeRightChar();
			key = deleteKey;
			break;
			
		case Input.Keys.LEFT:
			nextLeftPosition();
			key = leftKey;
			break;

		case Input.Keys.RIGHT:
			nextRightPosition();
			key = rightKey;
			break;

		default:
			break;
		}
		if (key != 0)
			keyTime = updateTime + 0.5f;
		
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {			
		case Input.Keys.ENTER:
			FocusElement.clearFocus();
		case Input.Keys.TAB:
			FocusElement.setFocus(nextControll);
			break;

		default:
			break;
		}
		key = 0;
		return true;
	}
	
	protected void removeLeftChar(){
		if (cursorPos > 0) {
			setText(text.content.substring(0, cursorPos-1) + text.content.substring(cursorPos));
			cursorPos--;
		}
	}

	protected void removeRightChar(){
		if (cursorPos != text.content.length())
			setText(text.content.substring(0, cursorPos) + text.content.substring(cursorPos+1));
	}
	
	protected void nextLeftPosition(){
		if (cursorPos > 0)
			cursorPos--;
	}
	
	protected void nextRightPosition(){
		if (cursorPos < text.content.length())
			cursorPos++;
	}
	
}
