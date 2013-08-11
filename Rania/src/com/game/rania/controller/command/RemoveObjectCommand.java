package com.game.rania.controller.command;

import com.game.rania.controller.MainController;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.Object;

public class RemoveObjectCommand extends ControllerCommand
{
  protected Group   group  = null;
  protected Object  object = null;

  public RemoveObjectCommand(Object object)
  {
    this.object = object;
  }

  public RemoveObjectCommand(Group group)
  {
    this.group = group;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    if (object != null)
    {
      controller.removeObject(object);
      controller.removeHUDObject(object);
      return;
    }

    if (group != null)
    {
      controller.removeObject(group);
      controller.removeHUDObject(group);
    }
  }

}
