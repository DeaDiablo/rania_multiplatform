package com.game.rania.controller;

import com.game.rania.net.NetController;
import com.game.rania.view.MainView;

public class Controllers
{
  public static CommandController  commandController = null;
  public static NetController      netController     = null;
  public static InputController    inputController   = null;
  public static LocationController locController     = null;
  public static ShaderManager      shaderManager     = null;

  public static void initSystemControllers(MainView mView, MainController mController)
  {
    commandController = new CommandController(mController);
    netController = new NetController(commandController);
    inputController = new InputController(mController);
    locController = new LocationController(mController, mView, netController);
    shaderManager = new ShaderManager();
  }

  public static void clearGlobalControllers()
  {
    shaderManager.clear();

    shaderManager = null;
    locController = null;
    inputController = null;
    netController = null;
    commandController = null;
  }
}
