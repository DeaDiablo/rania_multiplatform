package com.game.rania.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.rania.RaniaGame;

public class FrameSequence extends Object
{
  protected TextureRegion[] frames        = null;
  protected float           timeAnimation = 0.0f;
  protected boolean         loop          = false;
  protected Animation       animation     = null;

  public FrameSequence(String animationPath, int colsCount, float timeAnimation)
  {
    this(animationPath, colsCount, 1, timeAnimation, false);
  }

  public FrameSequence(String animationPath, int colsCount, float timeAnimation, boolean loop)
  {
    this(animationPath, colsCount, 1, timeAnimation, loop);
  }

  public FrameSequence(String animationPath, int colsCount, int rowsCount, float timeAnimation)
  {
    this(animationPath, colsCount, rowsCount, timeAnimation, false);
  }

  public FrameSequence(String animationPath, int colsCount, int rowsCount, float timeAnimation, boolean loop)
  {
    super();
    this.loop = loop;
    this.timeAnimation = timeAnimation;
    if (!loop)
      lifeTime = timeAnimation;
    Texture texture = RaniaGame.mView.getTexture(animationPath);
    if (texture == null)
      return;
    TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / colsCount, texture.getHeight() / rowsCount);
    frames = new TextureRegion[colsCount * rowsCount];
    int index = 0;
    for (int i = 0; i < rowsCount; i++)
    {
      for (int j = 0; j < colsCount; j++)
      {
        frames[index++] = tmp[i][j];
      }
    }
    animation = new Animation(timeAnimation / (colsCount * rowsCount), frames);
  }

  @Override
  public boolean update(float deltaTime)
  {
    if (animation == null)
      return false;

    super.update(deltaTime);
    region = animation.getKeyFrame(timeObject, loop);
    return true;
  }
}
