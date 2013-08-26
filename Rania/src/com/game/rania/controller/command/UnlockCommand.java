package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.controller.TimeController;
import com.game.rania.model.element.User;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.weapons.Weapon;

public class UnlockCommand extends ControllerCommand
{

  private int userID  = -1;
  private int equipID = -1;

  public UnlockCommand(int userID, int equipID)
  {
    this.userID = userID;
    this.equipID = equipID;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    User user = Controllers.locController.getUser(userID);
    if (user == null || !user.isPlayer)
      return;

    Equip<Weapon> weapon = user.weapon.get(equipID);
    if (weapon == null)
      return;

    TimeController.globalCooldown.unlock();
    TimeController.getCooldown(weapon.item).unlock();
  }

}
