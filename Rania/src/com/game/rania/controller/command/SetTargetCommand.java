package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.element.User;

public class SetTargetCommand extends ControllerCommand
{

  private int    id;
  private float  x, y;
  private int    flyTime;
  private double energy;

  public SetTargetCommand(int idUser, float targetX, float targetY, int flyTime, double energy)
  {
    id = idUser;
    x = targetX;
    y = targetY;
    this.flyTime = flyTime;
    this.energy = energy;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    User user = Controllers.locController.getUser(id);
    if (user != null)
    {
      user.energy = this.energy;
      user.setPositionTarget(x, y, flyTime);
    }
  }
}
