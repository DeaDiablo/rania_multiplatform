package com.game.rania.net;

import com.badlogic.gdx.Gdx;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.command.SwitchScreenCommand;
import com.game.rania.screen.MainMenu;
import com.game.rania.userdata.Client;
import com.game.rania.userdata.IOStream;

public class Receiver extends Thread
{
  private volatile boolean stopThread  = false;
  private IOStream         ioStream    = null;
  private NetController    nController = null;

  public Receiver(Client client, NetController controller)
  {
    ioStream = client.stream;
    nController = controller;
  }

  public void stopThread()
  {
    stopThread = true;
    interrupt();
  }

  public void run()
  {
    try
    {
      do
      {
        nController.processingCommand(ioStream.readCommand());
      } while (!stopThread);
    } catch (Exception ex)
    {
      if (!stopThread)
      {
        Gdx.app.log("receiver", "Error: " + ex.getMessage());
      }
      // TODO : message "disconnect"
      Controllers.commandController.addCommand(new SwitchScreenCommand(new MainMenu()));
    }
  }
}
