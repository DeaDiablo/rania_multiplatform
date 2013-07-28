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
		if (!super.touchDown(x, y))
			return false;
		FocusElement.setFocus(this);
		return true;
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		if (!super.keyDown(keyCode))
			return false;
		FocusElement.setFocus(this);
		return true;
	}
	
	@Override
	public boolean use(){
		if (FocusElement.getFocus() != this)
			return false;

		FocusElement.clearFocus();
		if (action != null)
			action.execute(true);

		return true;
	}
}
