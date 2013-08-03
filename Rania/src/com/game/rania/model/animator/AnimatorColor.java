package com.game.rania.model.animator;

import com.badlogic.gdx.graphics.Color;

public class AnimatorColor extends Animator
{
  protected Color   param = null;
  protected Color   end   = new Color();
  protected Color   start = new Color();
  protected boolean init  = false;

  public AnimatorColor(Color parametr, Color endColor, float startTime, float lifeTime)
  {
    super(startTime, lifeTime);
    this.param = parametr;
    this.end.set(endColor);
  }

  public AnimatorColor(Color parametr, float r, float g, float b, float a, float startTime, float lifeTime)
  {
    super(startTime, lifeTime);
    this.param = parametr;
    this.end.set(r, g, b, a);
  }

  protected boolean onlyAlpha = false;

  public AnimatorColor(Color parametr, float a, float startTime, float lifeTime)
  {
    super(startTime, lifeTime);
    this.param = parametr;
    this.end.set(a, a, a, a);
    onlyAlpha = true;
  }

  @Override
  protected void updateAnimation(float currentTime)
  {
    if (!init)
    {
      start.set(param);
      init = true;
    }

    param.a = start.a + (currentTime - startTime) * (end.a - start.a) / lifeTime;

    if (onlyAlpha)
      return;

    param.r = start.r + (currentTime - startTime) * (end.r - start.r) / lifeTime;
    param.g = start.g + (currentTime - startTime) * (end.g - start.g) / lifeTime;
    param.b = start.b + (currentTime - startTime) * (end.b - start.b) / lifeTime;
  }
}
