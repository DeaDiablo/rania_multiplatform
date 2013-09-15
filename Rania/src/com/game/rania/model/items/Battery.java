package com.game.rania.model.items;

import com.game.rania.model.RegionID;
import com.game.rania.model.element.Player;

public class Battery extends Device
{

  public int compress;

  public Battery()
  {
    super();
  }

  public Battery(Device device)
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
