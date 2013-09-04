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
  
  public static final int space  = 0;
  public static final int planet = 1;
  
  public static int status = space;

  public static void showDefaultPanels()
  {
    sideBar.setVisiblePanel();
    infoPanel.setVisible(status == space);
    skillsPanel.setVisible(status == space);
    planetPanel.setVisible(status == planet);
  }
  
  public static void showMenu(boolean show)
  {
    if (show)
    {
      sideBar.setVisibleMenu();
      infoPanel.setVisible(false);
      skillsPanel.setVisible(false);
      planetPanel.setVisible(false);
    }
    else
      showDefaultPanels();
  }
  
  public static void showEquip(boolean show)
  {
    if (show)
    {
      sideBar.setVisibleEquip();
      skillsPanel.setVisible(false);
      planetPanel.setVisible(false);
    }
    else
      showDefaultPanels();
  }
  
  public static void showChat(boolean show)
  {
    if (show)
    {
      sideBar.setVisibleChat();
      infoPanel.setVisible(false);
      skillsPanel.setVisible(false);
      planetPanel.setVisible(false);
    }
    else
      showDefaultPanels();
  }

  public static void showPlanetPanel(boolean show)
  {
    infoPanel.setVisible(!show);
    skillsPanel.setVisible(!show);
    sideBar.setVisiblePanel();
    planetPanel.setVisible(show);
    if (show)
    {
      status = planet;
      return;
    }

    status = space;
  }
}
