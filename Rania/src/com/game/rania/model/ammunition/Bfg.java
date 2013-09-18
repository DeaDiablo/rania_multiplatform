package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.game.rania.model.RegionID;
import com.game.rania.model.animator.AnimatorColor;
import com.game.rania.model.animator.AnimatorFloat;
import com.game.rania.model.animator.AnimatorVector2;
import com.game.rania.model.element.SpaceShip;

public class Bfg extends Ammunition
{

  protected static final float bfgTime     = 3.0f;
  protected static final float contactTime = 1.5f;

  public Bfg(SpaceShip attacker, SpaceShip target, int damage, Color repairColor)
  {
    super(attacker, target, damage, RegionID.BFG, bfgTime, contactTime);
    color.set(repairColor);
    vAlign = Align.CENTER;

    addAnimator(new AnimatorFloat(angle, 360.0f, 0, bfgTime));
    addAnimator(new AnimatorVector2(scale, 0, 0, bfgTime));
    addAnimator(new AnimatorColor(color, 0, 0, bfgTime));
  }

  @Override
  public boolean update(float delta)
  {
    if (!super.update(delta))
      return false;
    position.set(target.position);
    return true;
  }
}
