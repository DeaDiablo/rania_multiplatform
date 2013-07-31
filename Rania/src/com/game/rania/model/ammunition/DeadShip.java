package com.game.rania.model.ammunition;

import com.game.rania.RaniaGame;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class DeadShip extends Ammunition
{

  protected static final float deadTime = 1.0f;
  protected Object             target;

  public DeadShip(Object target)
  {
    super(deadTime, RegionID.DEAD_SHIP_0, 0, 0);
    this.target = target;
    scale.x = 2;
    scale.y = 2;
    vAlign = Align.CENTER;
  }

  @Override
  public void update(float delta)
  {
    super.update(delta);
    position.set(target.position);
    if (region != null)
    {
      if (dTime < deadTime)
      {
        float progress = dTime / deadTime;
        int stage = (int) (progress * 10);
        region = RaniaGame.mView.getTextureRegion(RegionID.fromInt(RegionID.DEAD_SHIP_0.ordinal() + stage));
      }
    }
  }
}
