package com.game.rania.screen.part;

import java.util.Vector;

public class Parts
{

  protected static Vector<Part> parts = new Vector<Part>();

  public static void removAllParts()
  {
    for (Part part : parts)
    {
      part.unloadPart();
    }
    parts.clear();
  }

  // sidebar
  protected static SideBar sideBar = null;

  public static SideBar getSideBar()
  {
    if (sideBar == null)
    {
      sideBar = new SideBar();
      parts.add(sideBar);
    }
    return sideBar;
  }

  // info panel
  protected static InfoPanel infoPanel = null;

  public static InfoPanel getInfoPanel()
  {
    if (infoPanel == null)
    {
      infoPanel = new InfoPanel();
      parts.add(infoPanel);
    }
    return infoPanel;
  }

  // info panel
  protected static SkillsPanel skillsPanel = null;

  public static SkillsPanel getSkillsPanel()
  {
    if (skillsPanel == null)
    {
      skillsPanel = new SkillsPanel();
      parts.add(skillsPanel);
    }
    return skillsPanel;
  }
  
  // planet panel
  protected static PlanetPanel planetPanel = null;

  public static PlanetPanel getPlanetPanel()
  {
    if (planetPanel == null)
    {
      planetPanel = new PlanetPanel();
      parts.add(planetPanel);
    }
    return planetPanel;
  }
}
