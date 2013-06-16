package com.game.rania.model.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.rania.model.Text;
import com.game.rania.model.element.RegionID;

public class CheckButton extends Button{

	private boolean check = false;
	
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
	public boolean touchDown(float x, float y) {
		return true;
	}

	@Override
	public boolean touchUp(float x, float y) {
		check = !check;
		
		if (action != null)
			action.execute(check);

		return true;
	}
	
	public boolean getCheck(){
		return check;
	}
	
	public void setCheck(boolean check){
		this.check = check;
	}
}
