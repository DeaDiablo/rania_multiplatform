package com.game.rania.screen.part;

import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.model.Group;
import com.game.rania.model.Object;
import com.game.rania.model.RegionID;
import com.game.rania.model.element.Planet;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;
import com.game.rania.view.MainView;

public class PlanetPanel extends Group implements Part
{
  private MainView mView = RaniaGame.mView;
  private PressedButton npcButton = null;
  private PressedButton tradeButton = null;
  private PressedButton scienceButton = null;
  private PressedButton technicButton = null;
  private PressedButton lifeButton = null;
  private PressedButton exitButton = null;
  private Object panelGraphic = null;
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
 
    mView.loadTexture("data/gui/planet_services.png", RegionID.PLANET_PANEL);

    mView.loadTexture("data/gui/planet_icons.png", RegionID.PLANET_NPC, 0, 0, 96, 96);
    mView.loadTexture("data/gui/planet_icons.png", RegionID.PLANET_TRADE, 96, 0, 96, 96);
    mView.loadTexture("data/gui/planet_icons.png", RegionID.PLANET_SCIENCE, 192, 0, 96, 96);
    mView.loadTexture("data/gui/planet_icons.png", RegionID.PLANET_TECHNIC, 288, 0, 96, 96);
    mView.loadTexture("data/gui/planet_icons.png", RegionID.PLANET_LIFE, 384, 0, 96, 96);
    mView.loadTexture("data/gui/planet_icons.png", RegionID.PLANET_EXIT, 480, 0, 96, 96);

    panelGraphic = new Object(RegionID.PLANET_PANEL, 0.0f, 0.0f);
   
    npcButton = new PressedButton(RegionID.PLANET_NPC,
                                    null,
                                    -halfWidth * 0.375f, 0,
                                    new TouchAction()
                                    {
                                      @Override
                                      public void execute(boolean touch)
                                      {
                                      }
                                    });
    npcButton.scale.set(2, 2);
    
    tradeButton = new PressedButton(RegionID.PLANET_TRADE,
                                      null,
                                      -halfWidth * 0.275f, halfHeight * 0.5f,
                                      new TouchAction()
                                      {
                                        @Override
                                        public void execute(boolean touch)
                                        {
                                        }
                                      });
    tradeButton.scale.set(2, 2);
    
    scienceButton = new PressedButton(RegionID.PLANET_SCIENCE,
                                      null,
                                      0, halfHeight * 0.7f,
                                      new TouchAction()
                                      {
                                        @Override
                                        public void execute(boolean touch)
                                        {
                                        }
                                      });
    scienceButton.scale.set(2, 2);
    
    technicButton = new PressedButton(RegionID.PLANET_TECHNIC,
                                   null,
                                   halfWidth * 0.275f, halfHeight * 0.5f,
                                   new TouchAction()
                                   {
                                     @Override
                                     public void execute(boolean touch)
                                     {
                                     }
                                   });
    technicButton.scale.set(2, 2);
    
    lifeButton = new PressedButton(RegionID.PLANET_LIFE,
                                   null,
                                   halfWidth * 0.375f, 0,
                                   new TouchAction()
                                   {
                                     @Override
                                     public void execute(boolean touch)
                                     {
                                     }
                                   });
    lifeButton.scale.set(2, 2);

    exitButton = new PressedButton(RegionID.PLANET_EXIT,
                                  null,
                                  0, -halfHeight * 0.65f,
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
                                      Controllers.locController.enablePlayerController(true);
                                      RaniaGame.mView.getCamera().zoom = 1.0f;
                                    }
                                  });
    exitButton.scale.set(2, 2);

    addElement(panelGraphic);
    addElement(npcButton);
    addElement(tradeButton);
    addElement(scienceButton);
    addElement(technicButton);
    addElement(lifeButton);
    addElement(exitButton);
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
