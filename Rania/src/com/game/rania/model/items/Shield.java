package com.game.rania.model.items;

import com.game.rania.model.Target;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Shield extends Device
{

  public int power;

  public Shield()
  {
    super();
  }

  public Shield(Device device)
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
