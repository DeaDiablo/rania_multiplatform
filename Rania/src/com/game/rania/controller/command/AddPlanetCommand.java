package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.element.Planet;

public class AddPlanetCommand extends ControllerCommand
{

  private Planet planet = null;

  public AddPlanetCommand(Planet planet)
  {
    this.planet = planet;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    if (planet == null)
      return;
    Controllers.locController.addPlanet(planet);
  }

}
