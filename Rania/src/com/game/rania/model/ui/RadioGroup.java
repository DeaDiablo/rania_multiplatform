package com.game.rania.model.ui;

import java.util.Vector;

public class RadioGroup {

	protected Vector<RadioButton> buttons = new Vector<RadioButton>();

	public void addButton(RadioButton button) {
		if (buttons.contains(button))
			return;
		button.group = this;
		buttons.add(button);
	}

	public void updateChecks(RadioButton button) {
		for (RadioButton chButton : buttons) {
			if (button != chButton)
				chButton.setCheck(false);
		}
	}
}
