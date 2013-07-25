package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;
import com.game.rania.model.ammunition.Laser;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Weapon;

public class AttackCommand extends ControllerCommand{
	
	private int userID 	 = -1;
	private int targetID = -1;
	private int equipID  = -1;
	private int damage   = 0;
	
	public AttackCommand(int userID, int targetID, int equipID, int dmg){
		this.userID = userID;
		this.targetID = targetID;
		this.equipID = equipID;
		damage = dmg;
	}
	
	@Override
	public void update(MainController controller, float deltaTime) {
		User user   = Controllers.locController.getUser(userID);
		User target = Controllers.locController.getUser(targetID);
		Equip<Weapon> weapon = null;
		if (user != null)
			weapon = user.weapon.get(equipID);
		
		if (weapon == null || target == null)
			return;

		target.damage(target.body, damage);
		if (user != Controllers.locController.getPlayer())
			controller.addObject(new Laser(user, target, user.domain.color));
	}

}
