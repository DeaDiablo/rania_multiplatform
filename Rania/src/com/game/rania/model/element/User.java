package com.game.rania.model.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.rania.controller.Controllers;
import com.game.rania.model.Font;

public class User extends SpaceShip
{

  public class Action
  {
    public static final int none   = 0;
    public static final int attack = 1;
    public static final int repair = 2;
  }

  public boolean isPlayer = false;
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

  protected Text textShip = new Text("", Font.getFont("data/fonts/Arial.ttf", 20), new Color(1, 1, 1, 1), 0, 0);
  
  @Override
  public boolean draw(SpriteBatch sprite, ShapeRenderer shape)
  {
    if (!super.draw(sprite, shape))
      return false;
    if (!isPlayer && textShip != null)
      textShip.draw(sprite, position.x, position.y + region.getRegionHeight() * 0.5f);
    return true;
  }
}
