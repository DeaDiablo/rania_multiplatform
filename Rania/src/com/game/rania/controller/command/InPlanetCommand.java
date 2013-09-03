package com.game.rania.controller.command;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.element.Planet;

public class InPlanetCommand extends ControllerCommand
{
  protected int id = -1;

  public InPlanetCommand(int idPlanet)
  {
    id = idPlanet;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    Planet planet = Controllers.locController.getPlanet(id);
    if (planet == null)
      return;
    Controllers.locController.inPlanet(planet);
  }

}
