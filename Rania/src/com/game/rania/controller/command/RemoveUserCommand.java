package com.game.rania.controller.command;

import com.game.rania.RaniaGame;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;

public class RemoveUserCommand extends ControllerCommand{

	private int id;
	public RemoveUserCommand(int idUser){
		id = idUser;
	}
	
	@Override
	public void update(MainController controller, float deltaTime) {
		User user = RaniaGame.mClient.getUser(id);
		if (user == null)
			return;
		
		RaniaGame.mClient.getUsers().remove(String.valueOf(user.id));
		controller.removeDynamicObject(user);
	}

}
