package com.game.rania.controller.command;

import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.controller.TimeController;
import com.game.rania.model.User;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.weapons.Weapon;

public class AttackCommand extends ControllerCommand
{

  private int userID   = -1;
  private int targetID = -1;
  private int equipID  = -1;
  private int damage   = 0;

  public AttackCommand(int userID, int targetID, int equipID, int dmg)
  {
    this.userID = userID;
    this.targetID = targetID;
    this.equipID = equipID;
    damage = dmg;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    User user = Controllers.locController.getUser(userID);
    User target = Controllers.locController.getUser(targetID);
    Equip<Weapon> weapon = null;
    if (user != null)
      weapon = user.weapon.get(equipID);

    if (weapon == null || target == null)
      return;

    if (user.isPlayer)
    {
      TimeController.globalCooldown.start();
      TimeController.getCooldown(weapon.item).start(weapon.item.time_reload);
    }
    RaniaGame.mController.addObject(weapon.item.getAmmunition(user, target, damage));
  }

}
