package com.game.rania.model.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.TimeController;
import com.game.rania.model.Object;
import com.game.rania.model.RegionID;

public class Planet extends Object
{

  public int                 id               = -1;
  public int                 type             = -1;
  public float               speed            = 0;
  public int                 orbit            = 0;
  public int                 radius           = 0;
  public Color               atmophereColor   = new Color(1.0f, 1.0f, 1.0f, 1.0f);
  public int[]               services         = null;
  public Domain              domain           = null;
  public float               atmosphereSpeedX = 0.0f;
  public float               atmosphereSpeedY = 0.0f;
  public String              name             = "";
  public int                 idLocation       = -1;
  public int                 price_coef       = 1;
  public Star                star             = null;
  public Texture             cloudTexture     = null;

  private static final float radianSec        = MathUtils.degreesToRadians / 3600.0f;
  private Vector2            cloudSpeed       = new Vector2();

  public Planet(int id, String name, int type, int radius, int speed, int orbit, int idLocation, int Domain, int ASX, int ASY)
  {
    super(RegionID.fromInt(RegionID.PLANET_0.ordinal() + type), 0, 0);
    cloudTexture = RaniaGame.mView.getTexture(RegionID.CLOUDS);
    this.id = id;
    this.name = name;
    this.type = type;
    this.radius = radius;
    this.speed = speed * radianSec / 100.0f;
    this.orbit = orbit;
    this.idLocation = idLocation;
    this.domain = Controllers.locController.getDomain(Domain);
    this.atmosphereSpeedX = ASX * 0.0001f;
    this.atmosphereSpeedY = ASY * 0.0001f;
    this.star = Controllers.locController.getLocation(idLocation).star;
    zIndex = Indexes.planets;
    calcPosition(TimeController.globalTime);
    setSize(2.0f * radius);
    shader = Controllers.shaderManager.getShader("data/shaders/main.vert", "data/shaders/planet.frag");
  }

  @Override
  public void reloadTexture()
  {
    super.reloadTexture();
    cloudTexture = RaniaGame.mView.getTexture(RegionID.CLOUDS);
  }

  protected float dt = 0.0f;

  @Override
  public boolean update(float deltaTime)
  {
    if (!super.update(deltaTime))
      return false;
    dt += deltaTime;
    cloudSpeed.set(atmosphereSpeedX * dt, atmosphereSpeedY * dt);
    calcPosition(TimeController.globalTime);
    return true;
  }

  @Override
  public boolean draw(SpriteBatch sprite, ShapeRenderer shape)
  {
    if (star == null)
      return false;
    if (currentShader == shader && shader != null)
    {
      shader.setUniformf("uvMin", new Vector2(region.getU(), region.getV()));
      shader.setUniformf("uvMax", new Vector2(region.getU2(), region.getV2()));
      shader.setUniformf("v_color2", atmophereColor);
    }
    boolean ret = super.draw(sprite, shape);
    sprite.flush();
    return ret;
  }

  @Override
  public boolean setShader(SpriteBatch sprite)
  {
    if (!super.setShader(sprite))
      return false;

    cloudTexture.bind(1);
    Gdx.gl.glActiveTexture(GL10.GL_TEXTURE0);
    shader.setUniformi("u_texture2", 1);
    shader.setUniformf("v_speed", cloudSpeed);

    return true;
  }

  private void calcPosition(float currentTime)
  {
    if (star == null)
      return;
    float calcAngle = speed * currentTime;
    position.set((float) Math.cos(calcAngle),
                 (float) Math.sin(calcAngle));
    position.scl(orbit);
    position.add(star.position);
    angle.value = MathUtils.radiansToDegrees * calcAngle + 45.0f;
  }
}
