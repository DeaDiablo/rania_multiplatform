package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;

public class AddUserCommand extends ControllerCommand {

	private User user = null;
	public AddUserCommand(User user) {
		this.user = user;
	}

	@Override
	public void update(MainController controller, float deltaTime) {
		if (user == null)
			return;
		Controllers.locController.addUser(user);
	}

}
