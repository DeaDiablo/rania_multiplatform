package com.game.rania.screen;

import com.game.rania.controller.Controllers;
import com.game.rania.model.Object;
import com.game.rania.model.RegionID;
import com.game.rania.model.element.Planet;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;
import com.game.rania.screen.part.Parts;
import com.game.rania.screen.part.SideBar;

public class PlanetScreen extends RaniaScreen
{
  private PressedButton outButton = null;
  private SideBar       sideBar   = Parts.getSideBar();
  public Planet         planet;

  public PlanetScreen(Planet planet)
  {
    super();
    this.planet = planet;
  }

  @Override
  public void show()
  {
    float halfWidth = mView.getHUDCamera().getWidth() * 0.5f;
    float halfHeight = mView.getHUDCamera().getHeight() * 0.5f;

    mView.loadTexture("data/backgrounds/planet.jpg", RegionID.BACKGROUND_PLANET);
    mController.addHUDObject(new Object(RegionID.BACKGROUND_PLANET, 0, 0));

    outButton = new PressedButton(RegionID.BTN_UI_CHAT_OFF,
                                  RegionID.BTN_UI_CHAT_ON,
                                  -halfWidth * 0.9f, halfHeight * 0.9f,
                                  new TouchAction()
                                  {
                                    @Override
                                    public void execute(boolean touch)
                                    {
                                      Controllers.netController.sendOutPlanet();
                                      new LocationScreen().set();
                                    }
                                  });

    mController.addHUDObject(outButton);
    sideBar.addPart();
  }

  @Override
  public void dispose()
  {
    super.dispose();
  }

  @Override
  public void update(float deltaTime)
  {
    super.update(deltaTime);
  }
}
