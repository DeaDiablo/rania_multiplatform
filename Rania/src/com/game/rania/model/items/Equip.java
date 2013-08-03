package com.game.rania.model.items;

import com.game.rania.controller.Controllers;
import com.game.rania.model.Target;
import com.game.rania.model.User;

public class Equip<T extends Item>
{
  public int     id        = 0;
  public T       item      = null;
  public boolean in_use    = false;
  public int     wear      = -1;
  public int     in_planet = -1;
  public int     num       = 0;
  public float   last_use  = 0.0f;

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
  
  public void use(User user, Target target){
    if (item.use(user, target))
      Controllers.netController.sendUseEquip(id);
  }
}
