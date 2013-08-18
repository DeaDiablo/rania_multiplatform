package com.game.rania.model.items;

import com.game.rania.model.RegionID;
import com.game.rania.model.element.Player;

public class Fuelbag extends Device
{

  public int compress;

  public Fuelbag()
  {
    super();
  }

  public Fuelbag(Device device)
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
