package com.game.rania.model.items;

import com.game.rania.model.Target;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Body extends Device
{

  public int slot_weapons;
  public int slot_droids;
  public int slot_shield;
  public int slot_hyper;

  public Body()
  {
    super();
  }

  public Body(Device device)
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
