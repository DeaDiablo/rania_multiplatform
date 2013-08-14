package com.game.rania.controller;

import com.game.rania.model.element.Cooldown;

public class TimeController
{
  public static float globalTime = 0.0f;
  public static Cooldown globalCooldown = new Cooldown(1.0f);

  public static void update(float deltaTime)
  {
    globalTime += deltaTime;
    globalCooldown.update(deltaTime);
  }
}
