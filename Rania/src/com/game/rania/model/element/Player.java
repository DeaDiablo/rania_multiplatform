package com.game.rania.model.element;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
    target.draw(sprite, shape);
    return super.draw(sprite, shape);
  }

  public float getTargetDistance()
  {
    return (float) Math.sqrt(Math.pow(position.x - target.object.position.x, 2) + Math.pow(position.y - target.object.position.y, 2));
  }
}
