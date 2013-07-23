package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;

public class RemoveUserCommand extends ControllerCommand {

	private int id;
	public RemoveUserCommand(int idUser) {
		id = idUser;
	}

	@Override
	public void update(MainController controller, float deltaTime) {
		Controllers.locController.removeUser(id);
	}

}
