package com.game.rania.model.animator;

public abstract class Animator
{
  protected float startTime = 0.0f;
  protected float lifeTime  = 0.0f;
  protected float endTime   = 0.0f;

  public Animator(float startTime, float lifeTime)
  {
    this.startTime = startTime;
    this.lifeTime = lifeTime;
    this.endTime = startTime + lifeTime;
  }

  public void setStartTime(float newStartTime)
  {
    startTime = newStartTime;
    endTime = startTime + lifeTime;
  }

  public float getStartTime()
  {
    return startTime;
  }

  public void setLifeTime(float newLifeTime)
  {
    lifeTime = newLifeTime;
    endTime = startTime + lifeTime;
  }

  public float getLifeTime()
  {
    return lifeTime;
  }

  public void setEndTime(float newEndTime)
  {
    endTime = newEndTime;
    lifeTime = endTime - startTime;
  }

  public float getEndTime()
  {
    return endTime;
  }

  // return true if animation ended
  public boolean update(float currentTime)
  {
    if (currentTime < startTime)
      return false;

    if (currentTime > endTime)
      return true;

    updateAnimation(currentTime);

    return false;
  }

  protected abstract void updateAnimation(float currentTime);
}
