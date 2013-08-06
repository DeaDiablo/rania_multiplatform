package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;

public class SetTargetCommand extends ControllerCommand
{

  private int id;
  private float x, y;
  private double flyTime;
  private int fuel;

  public SetTargetCommand(int idUser, float targetX, float targetY, double fT, int ff)
  {
    id = idUser;
    x = targetX;
    y = targetY;
    flyTime = fT;
    fuel = ff;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    User user = Controllers.locController.getUser(id);
    if (user != null)
    {
      user.fuel.num -= fuel;
      user.setPositionTarget(x, y);
    }
  }
}
