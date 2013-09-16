package com.game.rania.model.element;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.rania.controller.Controllers;

public class User extends SpaceShip
{

  public class Action
  {
    public static final int none   = 0;
    public static final int attack = 1;
    public static final int repair = 2;
  }

  public int     id;
  public String  pilotName;
  public Domain  domain;
  public int     planet;

  public User(int Id, float posX, float posY, String ShipName, String PilotName, int Domain)
  {
    super(posX, posY, ShipName);
    id = Id;
    pilotName = PilotName;
    domain = Controllers.locController.getDomain(Domain);
    planet = 0;
    zIndex = Indexes.users;
    textShip.content = shipName;
  }

  public User(int Id, float posX, float posY, String ShipName, String PilotName, int Domain, int InPlanet)
  {
    super(posX, posY, ShipName);
    id = Id;
    pilotName = PilotName;
    domain = Controllers.locController.getDomain(Domain);
    planet = InPlanet;
    zIndex = Indexes.users;
    textShip.content = shipName;
  }

  @Override
  public boolean draw(SpriteBatch sprite, ShapeRenderer shape)
  {
    if (!super.draw(sprite, shape))
      return false;
    return true;
  }
}
