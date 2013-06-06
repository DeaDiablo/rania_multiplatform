package com.game.rania.controller.command;

import com.game.rania.RaniaGame;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;

public class SetTargetCommand extends ControllerCommand{

	private User user = null;
	private float    x, y;

	public SetTargetCommand(int idUser, float targetX, float targetY){
		user = RaniaGame.mClient.getUser(idUser);
		x = targetX;
		y = targetY;
	}
	
	@Override
	public void update(MainController controller, float deltaTime) {
		if (user == null)
			return;
		user.setPositionTarget(x, y);
	}
}
