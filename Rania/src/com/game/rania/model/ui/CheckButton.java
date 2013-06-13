package com.game.rania.model.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.rania.model.Text;
import com.game.rania.model.element.RegionID;

public class CheckButton extends Button{

	public CheckButton(RegionID regionOff, RegionID regionOn, float x, float y) {
		super(regionOff, regionOn, x, y, null, null);
	}
	
	public CheckButton(TextureRegion regionOff, TextureRegion regionOn, float x, float y) {
		super(regionOff, regionOn, x, y, null, null);
	}

	public CheckButton(RegionID regionOff, RegionID regionOn, float x, float y, TouchAction action) {
		super(regionOff, regionOn, x, y, null, action);
	}
	
	public CheckButton(TextureRegion regionOff, TextureRegion regionOn, float x, float y, TouchAction action) {
		super(regionOff, regionOn, x, y, null, action);
	}

	public CheckButton(RegionID regionOff, RegionID regionOn, float x, float y, Text text, TouchAction action) {
		super(regionOff, regionOn, x, y, text, action);
	}
	
	public CheckButton(TextureRegion regionOff, TextureRegion regionOn, float x, float y, Text text, TouchAction action) {
		super(regionOff, regionOn, x, y, text, action);
	}
	
	public CheckButton(RegionID regionOff, RegionID regionOn, float x, float y, Text text) {
		super(regionOff, regionOn, x, y, text, null);
	}
	
	public CheckButton(TextureRegion regionOff, TextureRegion regionOn, float x, float y, Text text) {
		super(regionOff, regionOn, x, y, text, null);
	}

	@Override
	public boolean touchDown(int x, int y) {
		return true;
	}

	@Override
	public boolean touchUp(int x, int y) {
		buttonPressed = !buttonPressed;
		
		if (action != null)
			action.execute(buttonPressed);

		return true;
	}
	
	public boolean getCheck(){
		return buttonPressed;
	}
	
	public void setCheck(boolean check){
		buttonPressed = check;
	}
}
