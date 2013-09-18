package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.RegionID;
import com.game.rania.model.element.SpaceShip;

public class Laser extends Ammunition
{

  protected static final float laserTime   = 0.5f;
  protected static final float contactTime = 0.4f;

  public Laser(SpaceShip attacker, SpaceShip target, int damage, Color laserColor)
  {
    super(attacker, target, damage, RegionID.LASER, laserTime, contactTime);
    color.set(laserColor);
    vAlign = Align.TOP;
  }

  protected Vector2            scaleY    = new Vector2();
  protected static final float scaleTime = 0.3f;

  @Override
  public boolean update(float delta)
  {
    if (!super.update(delta))
      return false;
    position.set(attacker.position);
    angle.value = (float) Math.toDegrees(Math.atan2(-(target.position.x - attacker.position.x), (target.position.y - attacker.position.y)));
    if (region != null)
    {
      scaleY.set(target.position);
      scaleY.sub(attacker.position);
      if (timeObject < scaleTime)
        scaleY.scl(timeObject / scaleTime);
      scale.set(1.0f, scaleY.len() / region.getRegionHeight());
    }
    return true;
  }
}
