package com.game.rania.controller.command;

import com.game.rania.RaniaGame;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;

public class RemoveUserCommand extends ControllerCommand{

	private User  user = null;
	public RemoveUserCommand(int idUser){
		user = RaniaGame.mClient.getUser(idUser);
	}
	
	@Override
	public void update(MainController controller, float deltaTime) {
		if (user == null)
			return;
		controller.removeDynamicObject(user);
	}

}
