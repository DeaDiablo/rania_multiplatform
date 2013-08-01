package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.Location;

public class AddLocationCommand extends ControllerCommand
{

  private Location location = null;

  public AddLocationCommand(Location location)
  {
    this.location = location;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    if (location == null)
      return;
    Controllers.locController.addLocation(location);
  }

}
