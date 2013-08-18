package com.game.rania.model.items.weapons;

import com.game.rania.model.SpaceShip;
import com.game.rania.model.ammunition.Ammunition;
import com.game.rania.model.ammunition.Bfg;
import com.game.rania.model.element.RegionID;

public class BfgGun extends Weapon
{
  public BfgGun()
  {
    super();
  }

  public BfgGun(Weapon weapon)
  {
    super(weapon);
  }

  @Override
  public RegionID getIconID()
  {
    return RegionID.BFG_SKILL;
  }

  @Override
  public Ammunition getAmmunition(SpaceShip user, SpaceShip target, int damage)
  {
    return new Bfg(user, target, damage, color);
  }
}
