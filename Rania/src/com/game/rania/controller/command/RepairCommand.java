package com.game.rania.controller.command;

import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.controller.TimeController;
import com.game.rania.model.element.User;
import com.game.rania.model.items.RepairKit;
import com.game.rania.model.items.Equip;

public class RepairCommand extends ControllerCommand
{

  private int userID   = -1;
  private int targetID = -1;
  private int equipID  = -1;
  private int repair   = 0;

  public RepairCommand(int userID, int targetID, int equipID, int rpr)
  {
    this.userID = userID;
    this.targetID = targetID;
    this.equipID = equipID;
    this.repair = rpr;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    User user = Controllers.locController.getUser(userID);
    User target = Controllers.locController.getUser(targetID);
    Equip<RepairKit> repairKit = null;
    if (user != null)
      repairKit = user.repairKit.get(equipID);

    if (repairKit == null || target == null)
      return;

    if (user.isPlayer)
    {
      TimeController.globalCooldown.start();
      TimeController.getCooldown(repairKit.item).start(repairKit.item.time_reload);
    }
    RaniaGame.mController.addObject(repairKit.item.getAmmunition(user, target, repair));
  }
}
