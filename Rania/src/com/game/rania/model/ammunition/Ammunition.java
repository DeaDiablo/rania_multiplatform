package com.game.rania.model.ammunition;

import com.game.rania.model.Object;
import com.game.rania.model.RegionID;
import com.game.rania.model.element.Indexes;
import com.game.rania.model.element.SpaceShip;

public abstract class Ammunition extends Object
{
  protected SpaceShip attacker, target;
  protected int       value;
  protected float     contactTime;

  public Ammunition(SpaceShip attacker, SpaceShip target, int value, RegionID id, float lifeTime, float contactTime)
  {
    super(id, attacker.position.x, attacker.position.y);
    this.attacker = attacker;
    this.target = target;
    this.value = value;
    if (attacker == null || target == null)
      this.lifeTime = 0.0f;
    else
      this.lifeTime = lifeTime;
    this.contactTime = contactTime;
    zIndex = Indexes.ammunition;
  }

  @Override
  public boolean update(float deltaTime)
  {
    boolean result = super.update(deltaTime);
    if (timeObject > contactTime)
    {
      contact();
      contactTime = Float.MAX_VALUE;
    }
    return result;
  }

  protected abstract void contact();
}
