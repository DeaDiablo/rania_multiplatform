package com.game.rania.model.element;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.rania.model.Object;
import com.game.rania.model.RegionID;

public class Target extends Object
{

  public static final int none   = 0;
  public static final int planet = 1;
  public static final int user   = 2;
  public static final int item   = 3;
  public static final int star   = 4;

  public Object           object = null;
  public int              id     = 0;
  public int              type   = 0;

  public Target()
  {
    super(RegionID.TARGET, 0, 0);
  }

  public Target(int id, int type, Object objectTarget)
  {
    super(RegionID.TARGET, 0, 0);
    this.object = objectTarget;
    this.id = id;
    this.type = type;
  }

  public void changeTarget(int id, int type, Object objectTarget)
  {
    this.object = objectTarget;
    this.id = id;
    this.type = type;
  }

  public <T extends Object> T getObject(Class<T> type)
  {
    return type.cast(object);
  }

  @Override
  public boolean draw(SpriteBatch sprite, ShapeRenderer shape)
  {
    if (object == null || region == null)
      return false;
    float maxSize = Math.max(object.getWidth(), object.getHeight());
    scale.set(maxSize / region.getRegionWidth(), maxSize / region.getRegionHeight());
    position.set(object.position);
    return super.draw(sprite, shape);
  }
}
