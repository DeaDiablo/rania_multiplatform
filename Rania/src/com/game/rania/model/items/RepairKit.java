package com.game.rania.model.items;

import com.game.rania.RaniaGame;
import com.game.rania.model.ammunition.Repair;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

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

  @Override
  public boolean use(Object user, Object target)
  {
    RaniaGame.mController.addObject(new Repair(user, target, color));
    return true;
  }
}
