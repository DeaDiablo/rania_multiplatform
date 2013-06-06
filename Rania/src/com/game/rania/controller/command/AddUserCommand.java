package com.game.rania.controller.command;

import com.game.rania.RaniaGame;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;

public class AddUserCommand extends ControllerCommand{

	private User  user = null;
	public AddUserCommand(int idUser, float targetX, float targetY, String ShipName){
		user = new User(idUser, targetX, targetY, ShipName, "");
	}
	
	@Override
	public void update(MainController controller, float deltaTime) {
		if (user == null)
			return;
		RaniaGame.mClient.getUsers().put(String.valueOf(user.id), user);
		controller.addDynamicObject(user);
	}

}
