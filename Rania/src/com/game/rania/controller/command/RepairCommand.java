package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;
import com.game.rania.model.ammunition.Repair;
import com.game.rania.model.items.RepairKit;
import com.game.rania.model.items.Equip;

public class RepairCommand extends ControllerCommand{

	private int userID 	 = -1;
	private int targetID = -1;
	private int equipID  = -1;
	private int repair   = 0;
	
	public RepairCommand(int userID, int targetID, int equipID, int rpr){
		this.userID = userID;
		this.targetID = targetID;
		this.equipID = equipID;
		this.repair = rpr;
	}
	
	@Override
	public void update(MainController controller, float deltaTime) {
		User user   = Controllers.locController.getUser(userID);
		User target = Controllers.locController.getUser(targetID);
		Equip<RepairKit> droid = null;
		if (user != null)
			droid = user.repairKit.get(equipID);
		
		if (droid == null || target == null)
			return;

		target.repair(target.body, repair);
		if (user != Controllers.locController.getPlayer())
			controller.addObject(new Repair(user, target, user.domain.color));
	}
}