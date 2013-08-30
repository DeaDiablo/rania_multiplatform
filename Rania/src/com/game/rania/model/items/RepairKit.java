package com.game.rania.model.items;

import com.game.rania.model.RegionID;
import com.game.rania.model.ammunition.Repair;
import com.game.rania.model.element.Player;
import com.game.rania.model.element.SpaceShip;
import com.game.rania.model.element.Target;

public class RepairKit extends Device
{
  public int power;
  public int time_reload;
  public int radius;

  public RepairKit()
  {
    super();
  }

  public RepairKit(Device device)
  {
    super(device);
  }

  @Override
  public RegionID getIconID()
  {
    return RegionID.REPAIR_KIT_SKILL;
  }

  public int repair = 0;

  @Override
  public boolean use(Player player)
  {
    if (!super.use(player) ||
        (player.target.type == Target.user &&
        player.getTargetDistance() > radius))
      return false;
    return true;
  }

  public Repair getAmmunition(SpaceShip user, SpaceShip target, int repair)
  {
    return new Repair(user, target, repair, color);
  }
}
