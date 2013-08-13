package com.game.rania.controller;

public class TimeController
{
  public static float globalTime = 0.0f;
  
  public static final float globalCooldownCount = 1.0f;
  public static float globalCooldown = 0.0f;

  public static void update(float deltaTime)
  {
    globalTime += deltaTime;
    if (globalCooldown > 0)
      globalCooldown -= deltaTime;
  }
  
  public static void startGlobalCooldown()
  {
    globalCooldown = globalCooldownCount;
  }
  
  public static float getProgressGlobalCooldown()
  {
    return Math.min((globalCooldownCount - globalCooldown) / globalCooldownCount, 1.0f);
  }
  
  public static boolean checkGlobalCooldown()
  {
    return (globalCooldown > 0);
  }
}
