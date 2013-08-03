package com.game.rania.model.animator;

import com.badlogic.gdx.math.Vector2;

public class AnimatorVector2 extends Animator
{
  protected Vector2 param = null;
  protected Vector2 end   = new Vector2();
  protected Vector2 start = new Vector2();
  protected boolean init  = false;

  public AnimatorVector2(Vector2 parametr, float end, float startTime, float lifeTime)
  {
    super(startTime, lifeTime);
    this.param = parametr;
    this.end.set(end, end);
  }

  public AnimatorVector2(Vector2 parametr, float endX, float endY, float startTime, float lifeTime)
  {
    super(startTime, lifeTime);
    this.param = parametr;
    this.end.set(endX, endY);
  }

  @Override
  protected void updateAnimation(float currentTime)
  {
    if (!init)
    {
      start.set(param);
      init = true;
    }
    param.x = start.x + (currentTime - startTime) * (end.x - start.x) / lifeTime;
    param.y = start.y + (currentTime - startTime) * (end.y - start.y) / lifeTime;
  }
}
