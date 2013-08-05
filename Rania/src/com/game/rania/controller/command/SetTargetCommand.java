package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.User;

public class SetTargetCommand extends ControllerCommand
{

  private int id;
  private float x, y;
  private double flyTime;

  public SetTargetCommand(int idUser, float targetX, float targetY, double fT)
  {
    id = idUser;
    x = targetX;
    y = targetY;
    flyTime = fT;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    User user = Controllers.locController.getUser(id);
    if (user != null)
      user.setPositionTarget(x, y);
  }
}
