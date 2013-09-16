package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.game.rania.model.RegionID;
import com.game.rania.model.element.SpaceShip;

public class Rocket extends Ammunition
{

  protected static final float rocketTime  = 1.0f;
  protected static final float contactTime = 1.0f;

  public Rocket(SpaceShip attacker, SpaceShip target, int damage, Color rocketColor)
  {
    super(attacker, target, damage, RegionID.ROCKET, rocketTime, contactTime);
    color.set(rocketColor);
  }

  @Override
  public boolean update(float delta)
  {
    if (!super.update(delta))
      return false;
    angle.value = (float) Math.toDegrees(Math.atan2(-(target.position.x - attacker.position.x), (target.position.y - attacker.position.y)));
    if (region != null)
    {
      if (timeObject < rocketTime)
      {
        float progress = timeObject / rocketTime;
        position.set(attacker.position.x + (target.position.x - attacker.position.x) * progress,
                     attacker.position.y + (target.position.y - attacker.position.y) * progress);
      }
    }
    return true;
  }

  @Override
  protected void contact()
  {
    if (target.shield.wear <= 0)
      target.damage(target.body, value);
    else
      target.damage(target.shield, value);
  }
}
