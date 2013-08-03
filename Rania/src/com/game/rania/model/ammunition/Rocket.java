package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Rocket extends Ammunition
{

  protected static final float rocketTime = 1.0f;
  protected Object             attacker, target;

  public Rocket(Object attacker, Object target, Color rocketColor)
  {
    super(rocketTime, RegionID.ROCKET, 0, 0);
    this.attacker = attacker;
    this.target = target;
    color.set(rocketColor);
    vAlign = Align.CENTER;
  }

  @Override
  public void update(float delta)
  {
    super.update(delta);
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
  }
}
