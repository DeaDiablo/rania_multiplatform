package com.game.rania.model.items;

import com.game.rania.model.RegionID;

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
}
