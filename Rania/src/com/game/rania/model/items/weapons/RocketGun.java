package com.game.rania.model.items.weapons;

import com.game.rania.model.SpaceShip;
import com.game.rania.model.ammunition.Ammunition;
import com.game.rania.model.ammunition.Rocket;
import com.game.rania.model.element.RegionID;

public class RocketGun extends Weapon
{
  public RocketGun()
  {
    super();
  }

  public RocketGun(Weapon weapon)
  {
    super(weapon);
  }
  
  @Override
  public RegionID getIconID()
  {
    return RegionID.ROCKET_SKILL;
  }
  
  @Override
  public Ammunition getAmmunition(SpaceShip user, SpaceShip target, int damage)
  {
    return new Rocket(user, target, damage, color);
  }
}
