package com.game.rania.screen.part;

import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.model.Group;
import com.game.rania.model.RegionID;
import com.game.rania.model.element.Planet;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;
import com.game.rania.view.MainView;

public class PlanetPanel extends Group implements Part
{
  private MainView mView = RaniaGame.mView;
  private PressedButton outButton = null;
  private Planet planet = null;

  public PlanetPanel()
  {
  }
  
  @Override
  public void unloadPart()
  {
    removePart();
    clear();
  }
  
  public void setPlanet(Planet planet)
  {
    this.planet = planet;
  }

  @Override
  public void loadPart()
  {
    float halfWidth = mView.getHUDCamera().getWidth() * 0.5f;
    float halfHeight = mView.getHUDCamera().getHeight() * 0.5f;

    outButton = new PressedButton(RegionID.BTN_UI_CHAT_OFF,
                                  RegionID.BTN_UI_CHAT_ON,
                                  -halfWidth * 0.9f, halfHeight * 0.9f,
                                  new TouchAction()
                                  {
                                    @Override
                                    public void execute(boolean touch)
                                    {
                                      Controllers.netController.sendOutPlanet();
                                      Controllers.locController.getPlayer().position.set(planet.position);
                                      Parts.showPlanetPanel(false);
                                      Controllers.locController.getRadar().visible = true;
                                      Controllers.locController.getPlayer().planet = 0;
                                    }
                                  });

    addElement(outButton);
  }

  @Override
  public void addPart()
  {
    RaniaGame.mController.addHUDObject(this);
  }

  @Override
  public void removePart()
  {
    RaniaGame.mController.removeHUDObject(this);
  }
}
