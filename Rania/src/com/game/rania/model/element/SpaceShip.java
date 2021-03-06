package com.game.rania.model.element;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.command.AddObjectCommand;
import com.game.rania.model.Font;
import com.game.rania.model.FrameSequence;
import com.game.rania.model.Object;
import com.game.rania.model.RegionID;
import com.game.rania.model.animator.AnimatorColor;
import com.game.rania.model.animator.AnimatorVector2;
import com.game.rania.model.items.Device;
import com.game.rania.model.items.RepairKit;
import com.game.rania.model.items.Engine;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Battery;
import com.game.rania.model.items.Item;
import com.game.rania.model.items.Radar;
import com.game.rania.model.items.Hyper;
import com.game.rania.model.items.Shield;
import com.game.rania.model.items.Body;
import com.game.rania.model.items.weapons.Weapon;

public class SpaceShip extends Object
{
  public String shipName;
  public boolean isPlayer = false;

  public SpaceShip(float posX, float posY, String ShipName)
  {
    super(RegionID.SHIP, posX, posY);
    shipName = ShipName;
  }

  public SpaceShip(float posX, float posY, float rotAngle, String ShipName)
  {
    super(RegionID.SHIP, posX, posY, rotAngle);
    shipName = ShipName;
  }

  public SpaceShip(float posX, float posY, float rotAngle, float scaleX, float scaleY, String ShipName)
  {
    super(RegionID.SHIP, posX, posY, rotAngle, scaleX, scaleY);
    shipName = ShipName;
  }

  private Vector2 targetPosition = new Vector2(0, 0);
  private Vector2 moveVec        = new Vector2(0, 0);
  private Vector2 addVec         = new Vector2(0, 0);
  private boolean move           = false;

  public void setPositionTarget(Vector2 target, float time)
  {
    setPositionTarget(target.x, target.y, time);
  }

  public void stop()
  {
    move = false;
    moveVec.set(0, 0);
    targetPosition.set(position);
  }

  public void setPositionTarget(float x, float y, float time)
  {
    targetPosition.set(x, y);

    moveVec.set(targetPosition);
    moveVec.sub(position);
    moveVec.div(time * 0.001f);

    move = true;
  }

  @Override
  public boolean update(float deltaTime)
  {
    if (!super.update(deltaTime))
      return false;
    if (!move)
      return true;

    unEnergy(deltaTime);

    addVec.set(moveVec);
    addVec.scl(deltaTime);
    if (!targetPosition.epsilonEquals(position, addVec.len()))
      position.add(addVec);
    else
      stop();
    angle.value = (float) Math.toDegrees(Math.atan2(-addVec.x, addVec.y));
    return true;
  }

  protected Text textShip = new Text("", Font.getFont("data/fonts/Arial.ttf", 20), new Color(1, 1, 1, 1), 0, 0);

  @Override
  public boolean draw(SpriteBatch sprite, ShapeRenderer shape)
  {      
    if (body.wear <= 0 || region == null || !super.draw(sprite, shape))
      return false;

    textShip.draw(sprite, position.x, position.y + region.getRegionHeight() * 0.5f);
    
    sprite.end();
    shape.begin(ShapeType.Filled);
    if (!isPlayer)
    {
      float maxSize = Math.max(region.getRegionWidth(), region.getRegionHeight());
      if (body != null)
      {
        shape.setColor(new Color(1, 0, 0, 0.75f));
        shape.rect(position.x - maxSize * 0.5f, position.y + maxSize * 0.55f, maxSize * ((float) Math.max(0, body.wear) / body.item.durability), 3);
      }
    }
    shape.end();
    sprite.begin();

    return true;
  }

  // equips
  public Equip<Engine>                      engine    = null;
  public Equip<Battery>                     battery   = null;
  public Equip<Radar>                       radar     = null;
  public Equip<Hyper>                       hyper     = null;
  public Equip<Shield>                      shield    = null;
  public Equip<Body>                        body      = null;
  public HashMap<Integer, Equip<Weapon>>    weapon    = new HashMap<Integer, Equip<Weapon>>();
  public HashMap<Integer, Equip<RepairKit>> repairKit = new HashMap<Integer, Equip<RepairKit>>();

  public HashMap<Integer, Equip<Item>>      inventory = new HashMap<Integer, Equip<Item>>();

  // characteristics
  public double                             energy;
  public int                                maxEnergy;

  public void setEquips(List<Equip<Item>> equips)
  {
    for (Equip<Item> equip : equips)
    {
      if (!equip.in_use)
      {
        inventory.put(equip.id, equip);
      }
      else
      {
        if (equip.item instanceof Body)
        {
          this.body = new Equip<Body>(equip, Body.class);
          continue;
        }

        if (equip.item instanceof Engine)
        {
          this.engine = new Equip<Engine>(equip, Engine.class);
          continue;
        }

        if (equip.item instanceof Battery)
        {
          this.battery = new Equip<Battery>(equip, Battery.class);
          continue;
        }

        if (equip.item instanceof Hyper)
        {
          this.hyper = new Equip<Hyper>(equip, Hyper.class);
          continue;
        }

        if (equip.item instanceof Shield)
        {
          this.shield = new Equip<Shield>(equip, Shield.class);
          continue;
        }

        if (equip.item instanceof Radar)
        {
          this.radar = new Equip<Radar>(equip, Radar.class);
          continue;
        }

        if (equip.item instanceof Weapon)
        {
          this.weapon.put(equip.id, new Equip<Weapon>(equip, Weapon.class));
          continue;
        }

        if (equip.item instanceof RepairKit)
        {
          this.repairKit.put(equip.id, new Equip<RepairKit>(equip, RepairKit.class));
          continue;
        }
      }
    }

    maxEnergy = 0;

    if (battery != null)
    {
      maxEnergy = (int) battery.item.volume * battery.item.compress;
      energy = Math.max(0, Math.min(maxEnergy, energy));
    }
  }

  public void damage(Equip<? extends Device> equip, int value)
  {
    equip.wear = Math.max(0, equip.wear - value);
    if (equip == body && equip.wear <= 0)
    {
      crashSpaceShip(10);
      FrameSequence boom = new FrameSequence("data/location/boom.png", 10, 1.0f);
      boom.scale.set(2, 2);
      boom.position = position;

      Controllers.commandController.addCommand(new AddObjectCommand(boom));
    }
    String text = String.valueOf(value);
    if (value == 0)
      text = "miss";
    Text infoText = new Text(text, Font.getFont("data/fonts/Arial.ttf", 35), new Color(1.0f, 0.2f, 0.1f, 1.0f), position.x, position.y);
    infoText.addAnimator(new AnimatorVector2(infoText.position, infoText.position.x, infoText.position.y + 70, 0, 1.0f));
    infoText.addAnimator(new AnimatorColor(infoText.color, 0, 0, 1.0f));
    infoText.lifeTime = 1.0f;
    infoText.zIndex = Indexes.infoText;
    infoText.setAlign(Align.LEFT, Align.BOTTOM);
    Controllers.commandController.addCommand(new AddObjectCommand(infoText));
  }

  public void repair(Equip<? extends Device> equip, int value)
  {
    equip.wear = Math.min(equip.item.durability, equip.wear + value);
    String text = String.valueOf(value);
    if (value == 0)
      text = "miss";
    Text infoText = new Text(text, Font.getFont("data/fonts/Arial.ttf", 35), new Color(0.2f, 1.0f, 0.1f, 1.0f), position.x, position.y);
    infoText.addAnimator(new AnimatorVector2(infoText.position, infoText.position.x, infoText.position.y + 70, 0, 1.0f));
    infoText.addAnimator(new AnimatorColor(infoText.color, 0, 0, 1.0f));
    infoText.lifeTime = 1.0f;
    infoText.zIndex = Indexes.infoText;
    infoText.setAlign(Align.RIGHT, Align.BOTTOM);
    Controllers.commandController.addCommand(new AddObjectCommand(infoText));
  }
  
  public void repairFull(Equip<? extends Device> equip)
  {
    equip.wear = equip.item.durability;
  }


  public void unEnergy(float f)
  {
    energy -= f;
    energy = Math.max(energy, 0);
  }

  public void reEnergy(float f)
  {
    energy += f;
    energy = Math.min(energy, maxEnergy);
  }

  public void crashSpaceShip(int percent)
  {
    if (this.engine != null)
    {
      this.engine.wear = (int) (this.engine.wear - ((double) this.engine.item.durability * percent / 100));
      if (this.engine.wear < 0)
        this.engine.wear = 0;
    }

    if (this.battery != null)
    {
      this.battery.wear = (int) (this.battery.wear - ((double) this.battery.item.durability * percent / 100));
      if (this.battery.wear < 0)
        this.battery.wear = 0;
    }

    if (this.hyper != null)
    {
      this.hyper.wear = (int) (this.hyper.wear - ((double) this.hyper.item.durability * percent / 100));
      if (this.hyper.wear < 0)
        this.hyper.wear = 0;
    }

    if (this.radar != null)
    {
      this.radar.wear = (int) (this.radar.wear - ((double) this.radar.item.durability * percent / 100));
      if (this.radar.wear < 0)
        this.radar.wear = 0;
    }

    if (this.shield != null)
    {
      this.shield.wear = (int) (this.shield.wear - ((double) this.shield.item.durability * percent / 100));
      if (this.shield.wear < 0)
        this.shield.wear = 0;
    }

    for (Equip<RepairKit> dr : this.repairKit.values())
    {
      dr.wear = dr.wear - (int) ((double) dr.item.durability * percent / 100);
      if (dr.wear < 0)
        dr.wear = 0;
    }

    for (Equip<Weapon> weap : this.weapon.values())
    {
      weap.wear = weap.wear - (int) ((double) weap.item.durability * percent / 100);
      if (weap.wear < 0)
        weap.wear = 0;
    }
  }
}
