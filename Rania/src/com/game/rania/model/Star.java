package com.game.rania.model;

import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.Object;

public class Star extends Object
{

  public int    type;
  public int    id;
  public int    radius;
  public String name;

  public Star(int id, String name, int type, float x, float y, int radius)
  {
    super(RegionID.STAR, x, y);
    this.type = type;
    this.radius = radius;
    this.name = name;
    zIndex = Indexes.star;
    if (region != null)
      scale.set(2.0f * radius / region.getRegionWidth(), 2.0f * radius / region.getRegionHeight());
  }
}
