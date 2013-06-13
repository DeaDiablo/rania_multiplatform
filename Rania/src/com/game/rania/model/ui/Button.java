package com.game.rania.model.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.rania.RaniaGame;
import com.game.rania.model.Text;
import com.game.rania.model.element.HUDStaticObject;
import com.game.rania.model.element.RegionID;

public abstract class Button extends HUDStaticObject{

	protected Text			text		  = null;
	protected boolean 	  	buttonPressed = false;
	protected TextureRegion regionOn      = null;
	protected TouchAction	action        = null;

	public Button(TextureRegion regionOff, TextureRegion regionOn, float x, float y, Text text, TouchAction action){
		super(regionOff, x, y);
		this.regionOn = regionOn;
		this.text = text;
		this.action = action;
		touchObject = true;
	}
	
	public Button(RegionID regionOff, RegionID regionOn, float x, float y, Text text, TouchAction action){
		super(regionOff, x, y);
		this.regionOn = RaniaGame.mView.getTextureRegion(regionOn);
		this.text = text;
		this.action = action;
		touchObject = true;
	}
	
	@Override
	public boolean draw(SpriteBatch sprite){
		if (!visible)
			return false;
		
		if (!buttonPressed)
			drawRegion(sprite, region);
		else
			drawRegion(sprite, regionOn);
		
		if (text != null)
			text.draw(sprite, text.position.x + position.x, text.position.y + position.y, angle, scale.x, scale.y);			
		
		return true;
	}
}