package com.game.rania.model.items.weapons;

import com.game.rania.model.ammunition.Ammunition;
import com.game.rania.model.element.Player;
import com.game.rania.model.element.SpaceShip;
import com.game.rania.model.element.Target;
import com.game.rania.model.items.Device;

public abstract class Weapon extends Device
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
  public boolean use(Player player)
  {
    if (!super.use(player) ||
        player.target.type != Target.user ||
        player.getTargetDistance() > radius)
      return false;
    return true;
  }

  public abstract Ammunition getAmmunition(SpaceShip user, SpaceShip target, int damage);
}
