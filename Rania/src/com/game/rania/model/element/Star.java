package com.game.rania.model.element;

import com.game.rania.model.Object;
import com.game.rania.model.RegionID;

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
    setSize(2.0f * radius);
  }
}
