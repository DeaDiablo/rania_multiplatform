package com.game.rania.controller.command;

import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.model.element.Planet;
import com.game.rania.model.element.Target;
import com.game.rania.screen.part.Parts;

public class InPlanetCommand extends ControllerCommand
{
  protected int                id         = -1;
  protected static final float zoomFactor = 1 / 160.0f;

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

    Parts.getPlanetPanel().setPlanet(planet);
    Parts.showPlanetPanel(true);
    Controllers.locController.removeUsers();
    Controllers.locController.getRadar().visible = false;
    Controllers.locController.enablePlayerController(false);
    Controllers.locController.getPlayer().target.changeTarget(planet.id, Target.planet, planet);
    Controllers.locController.getPlayer().planet = planet.id;
    RaniaGame.mView.getCamera().zoom = planet.radius * zoomFactor;
  }

}
