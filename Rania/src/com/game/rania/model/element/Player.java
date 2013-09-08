package com.game.rania.model.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Player extends User
{

  public Target target = new Target(0, Target.none, null);

  public Player(int id, float posX, float posY, String ShipName, String PilotName, int domain)
  {
    super(id, posX, posY, ShipName, PilotName, domain);
    zIndex = Indexes.player;
    isPlayer = true;
  }

  public Player(int id, float posX, float posY, String ShipName, String PilotName, int domain, int inPlanet)
  {
    super(id, posX, posY, ShipName, PilotName, domain, inPlanet);
    zIndex = Indexes.player;
    isPlayer = true;
  }

  @Override
  public boolean update(float deltaTime)
  {
    if (!super.update(deltaTime))
      return false;
    target.update(deltaTime);
    return true;
  }

  @Override
  public boolean draw(SpriteBatch sprite, ShapeRenderer shape)
  {
    if (planet > 0)
    {
      angle.value = 0.0f;
      position.set(target.object.position);
      return true;
    }
    target.draw(sprite, shape);
    sprite.end();
    shape.begin(ShapeType.Filled);
    float maxSize = Math.max(region.getRegionWidth(), region.getRegionHeight());
    if (body != null)
    {
      shape.setColor(new Color(1, 0, 0, 0.75f));
      shape.rect(position.x - maxSize * 0.5f, position.y + maxSize * 0.55f + 5, maxSize * ((float)Math.max(0, body.wear) / body.item.durability), 5);
    }
    if (shield != null)
    {
      shape.setColor(new Color(0, 0, 1, 0.75f));
      shape.rect(position.x - maxSize * 0.5f, position.y + maxSize * 0.55f, maxSize * ((float)Math.max(0, shield.wear) / shield.item.durability), 5);
    }
    if (fuelbag != null)
    {
      shape.setColor(new Color(0, 1, 0, 0.75f));
      shape.rect(position.x - maxSize * 0.5f, position.y - maxSize * 0.55f, maxSize * ((float)Math.max(0, energy) / maxFuel), 5);
    }
    shape.end();
    sprite.begin();
    return super.draw(sprite, shape);
  }

  public float getTargetDistance()
  {
    return (float) Math.sqrt(Math.pow(position.x - target.object.position.x, 2) + Math.pow(position.y - target.object.position.y, 2));
  }
}
