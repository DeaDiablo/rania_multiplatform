package com.game.rania.model.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.Object;

public class Text extends Object
{

  public String     content = null;
  public BitmapFont font    = null;

  public Text(String text, BitmapFont font, Color color, float x, float y)
  {
    super(x, y, 0, 1, 1);
    this.content = text;
    this.font = font;
    this.color = color;
  }

  public Text(String text, BitmapFont font, Color color, float x, float y, Align horzAlign, Align vertAligin)
  {
    super(x, y, 0, 1, 1);
    this.content = text;
    this.font = font;
    this.color = color;
    hAlign = horzAlign;
    vAlign = vertAligin;
  }

  protected Matrix4    transformMatrix = new Matrix4();
  protected Matrix4    bufferMatrix    = new Matrix4();
  protected TextBounds textBound       = new TextBounds();

  public TextBounds getTextBound(String text)
  {
    font.setScale(scale.x, scale.y);
    font.getBounds(text, textBound);
    return textBound;
  }

  public TextBounds getTextBound()
  {
    font.setScale(scale.x, scale.y);
    font.getBounds(content, textBound);
    return textBound;
  }

  public TextBounds getTextBound(int startChar, int finishChar)
  {
    font.setScale(scale.x, scale.y);
    font.getBounds(content, startChar, finishChar, textBound);
    return textBound;
  }

  public float getLeft()
  {
    font.setScale(scale.x, scale.y);
    font.getBounds(content, textBound);
    return position.x - textBound.width * 0.5f;
  }

  public float getBottom()
  {
    font.setScale(scale.x, scale.y);
    font.getBounds(content, textBound);
    return position.y + textBound.height * 0.5f;
  }

  @Override
  public boolean draw(SpriteBatch sprite, ShapeRenderer shape)
  {
    return draw(sprite, position, angle.value, scale, color);
  }

  @Override
  public boolean draw(SpriteBatch sprite, Vector2 position, float angle, Vector2 scale, Color color)
  {
    if (!visible || content.isEmpty())
      return false;

    preDraw(position.x, position.y, angle, scale.x, scale.y);
    draw(sprite, content);

    return true;
  }

  public boolean draw(SpriteBatch sprite, float x, float y)
  {
    return draw(sprite, x, y, 0, 1, 1);
  }

  public boolean draw(SpriteBatch sprite, int begin, int end, float x, float y)
  {
    return draw(sprite, begin, end, x, y, 0, 1, 1);
  }

  public boolean draw(SpriteBatch sprite, float x, float y, float angle, float scaleX, float scaleY)
  {
    if (!visible || content.isEmpty())
      return false;

    preDraw(x, y, angle, scaleX, scaleY);
    draw(sprite, content);

    return true;
  }

  public boolean draw(SpriteBatch sprite, int begin, int end, float x, float y, float angle, float scaleX, float scaleY)
  {
    if (!visible || content.isEmpty())
      return false;

    preDraw(x, y, angle, scaleX, scaleY);
    draw(sprite, content.substring(begin, end));

    return true;
  }

  protected void preDraw(float x, float y, float angle, float scaleX, float scaleY)
  {
    transformMatrix.setToRotation(0, 0, 1, angle);
    transformMatrix.mul(bufferMatrix.setToTranslation(x, y, 0));
    transformMatrix.mul(bufferMatrix.setToRotation(0, 0, 1, this.angle.value));
    font.setColor(color);
    font.setScale(scale.x * scaleX, scale.y * scaleY);
  }

  protected void draw(SpriteBatch sprite, String string)
  {
    font.getBounds(string, textBound);
    calcOffset(textBound.width, textBound.height);
    sprite.setTransformMatrix(transformMatrix);
    font.draw(sprite, string, offset.x, -offset.y);
    sprite.setTransformMatrix(transformMatrix.idt());
  }
}