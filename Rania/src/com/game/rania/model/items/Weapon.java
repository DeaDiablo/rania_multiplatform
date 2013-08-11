package com.game.rania.model.items;

import com.game.rania.model.Player;
import com.game.rania.model.SpaceShip;
import com.game.rania.model.Target;
import com.game.rania.model.ammunition.Ammunition;
import com.game.rania.model.ammunition.Bfg;
import com.game.rania.model.ammunition.Laser;
import com.game.rania.model.ammunition.Rocket;
import com.game.rania.model.element.RegionID;

public class Weapon extends Device
{

  public class Type
  {
    public static final int Laser  = 0;
    public static final int Rocket = 1;
    public static final int BFG    = 2;
  }

  public int weaponType;
  public int radius;
  public int power;
  public int time_start;
  public int time_reload;

  public Weapon()
  {
    super();
  }

  public Weapon(Device device)
  {
    super(device);
  }

  @Override
  public RegionID getIconID()
  {
    switch (weaponType)
    {
      case Type.Laser:
        return RegionID.LASER_SKILL;

      case Type.Rocket:
        return RegionID.ROCKET_SKILL;

      case Type.BFG:
        return RegionID.BFG_SKILL;
    }
    return RegionID.NONE;
  }
  
  @Override
  public boolean use(Player player)
  {
    if (!super.use(player) || player.target.type != Target.user)
      return false;
    return true;
  }

  public Ammunition getAmmunition(SpaceShip user, SpaceShip target, int damage)
  {
    Ammunition bullet = null;

    switch (weaponType)
    {
      case Type.Laser:
        bullet = new Laser(user, target, damage, color);
        break;

      case Type.Rocket:
        bullet = new Rocket(user, target, damage, color);
        break;

      case Type.BFG:
        bullet = new Bfg(user, target, damage, color);
        break;
    }

    return bullet;
  }
}
