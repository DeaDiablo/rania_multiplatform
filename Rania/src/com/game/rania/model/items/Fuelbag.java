package com.game.rania.model.items;

import com.game.rania.model.Target;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

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
  public boolean use(Object user, Target target)
  {
    return false;
  }
}
