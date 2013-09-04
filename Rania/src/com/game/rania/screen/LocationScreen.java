package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.LocationController;
import com.game.rania.screen.part.InfoPanel;
import com.game.rania.screen.part.Parts;
import com.game.rania.screen.part.PlanetPanel;
import com.game.rania.screen.part.SideBar;
import com.game.rania.screen.part.SkillsPanel;

public class LocationScreen extends LoadableScreen
{

  private LocationController locController = Controllers.locController;

  private SideBar            sideBar       = Parts.getSideBar();
  private InfoPanel          infoPanel     = Parts.getInfoPanel();
  private SkillsPanel        skillsPanel   = Parts.getSkillsPanel();
  private PlanetPanel        planetPanel   = Parts.getPlanetPanel();

  public LocationScreen()
  {
    super();

    LoadObject loadObject = new LoadObject(new String("Загрузка текстур..."))
    {
      @Override
      public void load()
      {
        locController.loadTextures();
      }
    };
    addLoadObject(loadObject);

    loadObject = new LoadObject(new String("Загрузка мира..."))
    {
      @Override
      public void load()
      {
        locController.loadDomains();
      }
    };
    addLoadObject(loadObject);

    loadObject = new LoadObject(new String("Загрузка предметов..."))
    {
      @Override
      public void load()
      {
        locController.loadItems();
      }
    };
    addLoadObject(loadObject);

    loadObject = new LoadObject(new String("Загрузка игрока..."))
    {
      @Override
      public void load()
      {
        if (!locController.loadPlayer())
          Gdx.app.log("Loading...", "Error load player");
      }
    };
    addLoadObject(loadObject);

    loadObject = new LoadObject(new String("Загрузка фона..."))
    {
      @Override
      public void load()
      {
        locController.loadBackground();
      }
    };
    addLoadObject(loadObject);

    loadObject = new LoadObject(new String("Загрузка туманностей..."))
    {
      @Override
      public void load()
      {
        locController.loadNebulas();
      }
    };
    addLoadObject(loadObject);

    loadObject = new LoadObject(new String("Загрузка планет..."))
    {
      @Override
      public void load()
      {
        locController.loadPlanets();
      }
    };
    addLoadObject(loadObject);

    loadObject = new LoadObject(new String("Загрузка систем корабля..."))
    {
      @Override
      public void load()
      {
        locController.loadRadar();
        infoPanel.loadPart();
        skillsPanel.loadPart();
        planetPanel.loadPart();
      }
    };
    addLoadObject(loadObject);

    loadObject = new LoadObject(new String("Загрузка завершена."))
    {
      @Override
      public void load()
      {
        locController.loadComplete();
      }
    };
    addLoadObject(loadObject);
  }

  @Override
  public void show()
  {
    if (locController.getPlayer() == null || locController.getCurrentLocation() == null)
      return;

    locController.addBackground();
    locController.addNebulas();
    locController.addPlanets();
    locController.addRadar();
    locController.addPlayer();
    sideBar.addPart();
    infoPanel.addPart();
    skillsPanel.addPart();
    planetPanel.addPart();
    Parts.showDefaultPanels();
  }

  @Override
  public void dispose()
  {
    super.dispose();
    locController.clearObjects();
    infoPanel.unloadPart();
    skillsPanel.unloadPart();
    planetPanel.unloadPart();
  }

  @Override
  public void update(float deltaTime)
  {
    super.update(deltaTime);
    locController.update(deltaTime);
  }
}
