package com.game.rania.controller;

import java.util.HashMap;

import com.game.rania.model.Cooldown;
import com.game.rania.model.items.Item;

public class TimeController
{
  public static float                     globalTime     = 0.0f;
  public static Cooldown                  globalCooldown = new Cooldown(1.0f);
  public static HashMap<String, Cooldown> cooldowns      = new HashMap<String, Cooldown>();

  public static void update(float deltaTime)
  {
    globalTime += deltaTime;
    globalCooldown.update(deltaTime);
    for (Cooldown cooldown : cooldowns.values())
      cooldown.update(deltaTime);
  }

  public static Cooldown getCooldown(Item item)
  {
    Cooldown cooldown = cooldowns.get(item.getClass().getSimpleName());
    if (cooldown != null)
      return cooldown;
    cooldown = new Cooldown();
    cooldowns.put(item.getClass().getSimpleName(), cooldown);
    return cooldown;
  }
}
