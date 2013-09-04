package com.game.rania.model.items;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.TimeController;

public class Equip<T extends Item>
{
  public int     id        = 0;
  public T       item      = null;
  public boolean in_use    = false;
  public int     wear      = -1;
  public int     in_planet = -1;
  public int     num       = 0;
  public double  last_use  = 0.0f;

  public Equip()
  {
  }

  public Equip(Equip<Item> equip, Class<T> type)
  {
    id = equip.id;
    item = type.cast(equip.item);
    in_use = equip.in_use;
    wear = equip.wear;
    in_planet = equip.in_planet;
    num = equip.num;
    last_use = equip.last_use;
  }

  public void startUse()
  {
    if (item.use(Controllers.locController.getPlayer()))
    {
      TimeController.globalCooldown.lock();
      TimeController.getCooldown(item).lock();
      Controllers.netController.sendUseEquip(id);
    }
  }
}
