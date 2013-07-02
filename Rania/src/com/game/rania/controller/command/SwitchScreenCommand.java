package com.game.rania.controller.command;

import com.badlogic.gdx.Screen;
import com.game.rania.RaniaGame;
import com.game.rania.controller.MainController;

public class SwitchScreenCommand extends ControllerCommand{

	protected Screen screen;
	
	public SwitchScreenCommand(Screen screen){
		this.screen = screen;
	}
	
	@Override
	public void update(MainController controller, float deltaTime) {
		RaniaGame.mGame.getScreen().dispose();
		RaniaGame.mGame.setScreen(screen);
	}

}
