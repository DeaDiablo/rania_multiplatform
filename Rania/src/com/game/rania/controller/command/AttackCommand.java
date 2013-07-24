package com.game.rania.controller.command;

import com.badlogic.gdx.Gdx;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;
import com.game.rania.model.ammunition.Laser;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Weapon;

public class AttackCommand extends ControllerCommand{
	
	private User 		  user 	 = null;
	private User 		  target = null;
	private Equip<Weapon> weapon = null;
	private int 		  damage = 0;
	
	public AttackCommand(int userID, int targetID, int equipId, int dmg){
		user   = Controllers.locController.getUser(userID);
		target = Controllers.locController.getUser(targetID);
		if (user != null)
			weapon = user.weapon.get(equipId);
		damage = dmg;
	}
	
	@Override
	public void update(MainController controller, float deltaTime) {
		if (weapon == null || target == null)
			return;

		target.damage(target.body, damage * 100);
		if (user != Controllers.locController.getPlayer())
			controller.addObject(new Laser(user.position.x, user.position.y, target.position.x, target.position.y, user.domain.color));
		Gdx.app.log("Attack", "User " + user.pilotName + " to " + target.pilotName + ": " + damage);
	}

}
