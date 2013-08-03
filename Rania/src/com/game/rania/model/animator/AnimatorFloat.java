package com.game.rania.model.animator;

import com.game.rania.model.element.FloatLink;

public class AnimatorFloat extends Animator
{
  protected FloatLink param      = null;
  protected float     endValue   = 0.0f;
  protected float     startValue = 0.0f;
  protected boolean   init       = false;

  public AnimatorFloat(FloatLink parametr, float endValue, float startTime, float lifeTime)
  {
    super(startTime, lifeTime);
    this.param = parametr;
    this.endValue = endValue;
  }

  @Override
  protected void updateAnimation(float currentTime)
  {
    if (!init)
    {
      startValue = param.value;
      init = true;
    }
    param.value = startValue + (currentTime - startTime) * (endValue - startValue) / lifeTime;
  }
}
