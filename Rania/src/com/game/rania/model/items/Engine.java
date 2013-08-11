package com.game.rania.model.items;

import com.game.rania.model.Player;
import com.game.rania.model.element.RegionID;

public class Engine extends Device
{

  public int power;
  public int economic;

  public Engine()
  {
    super();
  }

  public Engine(Device device)
  {
    super(device);
  }

  @Override
  public RegionID getIconID()
  {
    return RegionID.NONE;
  }

  @Override
  public boolean use(Player player)
  {
    return false;
  }
}
