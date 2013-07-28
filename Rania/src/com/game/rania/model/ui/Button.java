package com.game.rania.model.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.rania.RaniaGame;
import com.game.rania.model.Indexes;
import com.game.rania.model.Text;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public abstract class Button extends Object{

	protected Text			text		  = null;
	protected TextureRegion regionOn      = null;
	protected TouchAction	action        = null;
	protected int			keyCode		  = 0;
	
	public Button(RegionID regionOff, RegionID regionOn, float x, float y, Text text, TouchAction action){
		super(regionOff, x, y);
		this.regionOn = RaniaGame.mView.getTextureRegion(regionOn);
		this.text = text;
		this.action = action;
		touchObject = true;
		zIndex = Indexes.button;
	}
	
	public void setKey(int keyCode){
		this.keyCode = keyCode;
		keysObject = true;
	}
	
	public int getKey(){
		return keyCode;
	}
	
	@Override
	public boolean draw(SpriteBatch sprite){
		if (!visible)
			return false;
		
		sprite.setColor(color);
		if (checkButton() && regionOn != null)
			drawRegion(sprite, regionOn);
		else
			drawRegion(sprite, region);
		if (text != null)
			text.draw(sprite, text.position.x + position.x, text.position.y + position.y, angle, scale.x, scale.y);			
		
		return true;
	}
	
	public boolean checkButton(){
		return (FocusElement.getFocus() == this);
	}
	
	public abstract boolean use();
}
