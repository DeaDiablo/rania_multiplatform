package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.game.rania.model.RegionID;
import com.game.rania.model.animator.AnimatorColor;
import com.game.rania.model.animator.AnimatorFloat;
import com.game.rania.model.animator.AnimatorVector2;
import com.game.rania.model.element.SpaceShip;

public class Repair extends Ammunition
{
  protected static final float repairTime  = 2.0f;
  protected static final float contactTime = 0.0f;

  public Repair(SpaceShip attacker, SpaceShip target, int repair, Color repairColor)
  {
    super(attacker, target, repair, RegionID.REPAIR, repairTime, contactTime);
    color.set(repairColor);
    position.set(target.position);
    addAnimator(new AnimatorFloat(angle, 360.0f, 0.0f, repairTime));
    addAnimator(new AnimatorVector2(scale, 0, 0, repairTime));
    addAnimator(new AnimatorColor(color, 0, 0, repairTime));
  }

  @Override
  public boolean update(float delta)
  {
    if (!super.update(delta))
      return false;
    position.set(target.position);
    return true;
  }

  @Override
  protected void contact()
  {
    target.repair(target.body, value);
  }
}
