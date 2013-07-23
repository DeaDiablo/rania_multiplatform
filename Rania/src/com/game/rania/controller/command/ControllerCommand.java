package com.game.rania.controller.command;

import com.game.rania.controller.MainController;

public abstract class ControllerCommand {

	public abstract void update(MainController controller, float deltaTime);
}
