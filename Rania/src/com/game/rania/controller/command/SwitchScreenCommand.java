package com.game.rania.controller.command;

import com.game.rania.RaniaGame;
import com.game.rania.controller.MainController;
import com.game.rania.screen.LoadingScreen;
import com.game.rania.screen.RaniaScreen;

public class SwitchScreenCommand extends ControllerCommand
{

  protected RaniaScreen screen;

  public SwitchScreenCommand(RaniaScreen screen)
  {
    this.screen = screen;
  }

  @Override
  public void update(MainController controller, float deltaTime)
  {
    RaniaGame.mGame.getScreen().dispose();
    controller.init();
    if (!screen.isLoaded())
    {
      RaniaGame.mGame.setScreen(new LoadingScreen(screen.asLoadableScreen()));
      return;
    }

    RaniaGame.mGame.setScreen(screen);
  }
}
