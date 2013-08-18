package com.game.rania.controller.command;

import com.game.rania.controller.MainController;
import com.game.rania.model.Group;
import com.game.rania.model.Object;

public class AddObjectCommand extends ControllerCommand
{
  protected Group   group  = null;
  protected Object  object = null;
  protected boolean asHUD  = false;

  public AddObjectCommand(Object object)
  {
    this.object = object;
    this.asHUD = false;
  }

  public AddObjectCommand(Group group)
  {
    this.group = group;
    this.asHUD = false;
  }

  public AddObjectCommand(Object object, boolean asHUD)
  {
    this.object = object;
    this.asHUD = asHUD;
  }

  public AddObjectCommand(Group group, boolean asHUD)
  {
    this.group = group;
    this.asHUD = asHUD;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    if (object != null)
    {
      if (!asHUD)
        controller.addObject(object);
      else
        controller.addHUDObject(object);
      return;
    }

    if (group != null)
    {
      if (!asHUD)
        controller.addObject(group);
      else
        controller.addHUDObject(group);
    }
  }

}
