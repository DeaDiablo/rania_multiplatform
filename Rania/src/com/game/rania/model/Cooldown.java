package com.game.rania.model;

public class Cooldown
{
  protected float   reload = 1.0f;
  protected float   time   = 0.0f;
  protected boolean lock   = false;

  public Cooldown()
  {
  }

  public Cooldown(float reloadTime)
  {
    reload = reloadTime;
  }

  public void lock()
  {
    lock = true;
  }

  public void start()
  {
    time = reload;
    lock = false;
  }

  public void start(float reloadTime)
  {
    reload = reloadTime;
    time = reload;
    lock = false;
  }

  public float progress()
  {
    if (lock)
      return 0.0f;
    return Math.min((reload - time) / reload, 1.0f);
  }

  public boolean check()
  {
    return (time > 0);
  }

  public void update(float deltaTime)
  {
    if (time > 0)
      time -= deltaTime;
  }
}
