package com.game.rania.model.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.model.Object;
import com.game.rania.model.RegionID;

public class Slider extends Object
{
  public int min = 0;
  public int max = 100;
  public int value = 50;
  
  public float widthScroller = 20; //only not fill
  public float width = 500;
  public float height = 20;
  
  protected boolean fillLine = false;
  protected boolean texture = false;
  protected SliderAction action = null;
  protected TextureRegion regionButton = null;
  protected Vector2 sliderPosition = new Vector2();

  public Slider(float x, float y, Color color, boolean fillLine, SliderAction action)
  {
    super(x, y);
    this.color = color;
    this.fillLine = fillLine;
    this.action = action;
    touchObject = true;
    focusObject = true;
    texture = false;
  }
  
  public Slider(float x, float y, RegionID sliderRegionID, float buttonX, float buttonY, RegionID sliderButtonID, SliderAction action)
  {
    super(sliderRegionID, x, y);
    this.regionButton = RaniaGame.mView.getTextureRegion(sliderButtonID);
    this.fillLine = false;
    this.action = action;
    sliderPosition.set(buttonX, buttonY);
    touchObject = true;
    focusObject = true;
    texture = true;
  }
  
  public float getPercent(int value)
  {
    if (value <= min)
      return 0.0f;
    if (value >= max)
      return 1.0f;
    return (float)value / (max + 1 - min);
  }
  
  public int getValue(float percent)
  {
    if (percent <= 0.0f)
      return min;
    if (percent >= 1.0f)
      return max;
    return (int)(percent * (max + 1 - min));
  }
  
  @Override
  public boolean draw(SpriteBatch sprite, ShapeRenderer shape)
  {
    if (texture)
    {
      if (!super.draw(sprite, shape))
        return false;
      drawRegion(sprite, regionButton,
                 position.x + offset.x + sliderPosition.x + getWidth() * getPercent(value),
                 position.y + offset.y + sliderPosition.y,
                 angle.value,
                 scale.x,
                 scale.y);
      return true;
    }
      
    if (!visible)
      return false;
    sprite.end();
    
    Gdx.gl.glEnable(GL10.GL_BLEND);
    shape.begin(ShapeType.Filled);
    calcOffset(width, height);

    color.a *= 0.5f;
    shape.setColor(color);
    shape.rect(position.x + offset.x,
               position.y + offset.y,
               width,
               height);
    
    color.a *= 2.0f;
    shape.setColor(color);
    if (fillLine)
    {
      shape.rect(position.x + offset.x,
                 position.y + offset.y,
                 width * getPercent(value),
                 height);
    }
    else
    {
      shape.rect(position.x + offset.x + width * getPercent(value),
                 position.y + offset.y,
                 widthScroller,
                 height);
    }

    shape.end();
    Gdx.gl.glDisable(GL10.GL_BLEND);
    
    sprite.begin();
    return true;
  }
  
  @Override
  public boolean intersectObject(float x, float y)
  {
    if (texture)
      return super.intersectObject(x, y);

    calcOffset(width, height);
    Rectangle rect = new Rectangle(position.x + offset.x,
                                   position.y + offset.y,
                                   width,
                                   height);
    Vector2 point = new Vector2(x, y);
    point.sub(position);
    point.rotate(angle.value);
    point.add(position);
    return rect.contains(point.x, point.y);
  }
  
  @Override
  public boolean touchDown(float x, float y)
  {
    FocusElement.setFocus(this);
    touchDragged(x, y);
    return true;
  }
  
  @Override
  public boolean touchDragged(float x, float y)
  {
    int oldValue = value;
    if (texture)
      value = getValue((x + getWidth() * 0.5f) / getWidth());
    else
      value = getValue((x + width * 0.5f) / width);
    if (oldValue != value && action != null)
      action.execute(this);
    return true;
  }
  
  @Override
  public boolean touchUp(float x, float y)
  {
    touchDragged(x, y);
    FocusElement.clearFocus();
    return true;
  }
}
