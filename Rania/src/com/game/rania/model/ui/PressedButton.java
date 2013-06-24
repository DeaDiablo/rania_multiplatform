package com.game.rania.model.ui;

import com.game.rania.model.Text;
import com.game.rania.model.element.RegionID;

public class PressedButton extends Button{

	public PressedButton(RegionID regionOff, RegionID regionOn, float x, float y) {
		super(regionOff, regionOn, x, y, null, null);
	}

	public PressedButton(RegionID regionOff, RegionID regionOn, float x, float y, TouchAction action) {
		super(regionOff, regionOn, x, y, null, action);
	}

	public PressedButton(RegionID regionOff, RegionID regionOn, float x, float y, Text text, TouchAction action) {
		super(regionOff, regionOn, x, y, text, action);
	}
	
	public PressedButton(RegionID regionOff, RegionID regionOn, float x, float y, Text text) {
		super(regionOff, regionOn, x, y, text, null);
	}

	@Override
	public boolean touchDown(float x, float y) {
		if (!visible)
			return false;
		FocusElement.setFocus(this);
		return true;
	}

	@Override
	public boolean touchUp(float x, float y) {
		if (!visible)
			return false;
		if (FocusElement.getFocus() != this)
			return false;
		
		if (action != null)
			action.execute(true);
		return true;
	}
}
