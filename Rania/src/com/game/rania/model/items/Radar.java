package com.game.rania.model.items;

import com.game.rania.model.RegionID;

public class Radar extends Device
{

  public int radius;
  public int defense;
  public int big_radius;

  public Radar()
  {
    super();
  }

  public Radar(Device device)
  {
    super(device);
  }

  @Override
  public RegionID getIconID()
  {
    return RegionID.NONE;
  }
}
