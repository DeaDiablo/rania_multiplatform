package com.game.rania.model.items.weapons;

import com.game.rania.model.SpaceShip;
import com.game.rania.model.ammunition.Ammunition;
import com.game.rania.model.ammunition.Laser;
import com.game.rania.model.element.RegionID;

public class LaserGun extends Weapon
{
  public LaserGun()
  {
    super();
  }

  public LaserGun(Weapon weapon)
  {
    super(weapon);
  }
  
  @Override
  public RegionID getIconID()
  {
    return RegionID.LASER_SKILL;
  }
  
  @Override
  public Ammunition getAmmunition(SpaceShip user, SpaceShip target, int damage)
  {
    return new Laser(user, target, damage, color);
  }
}
