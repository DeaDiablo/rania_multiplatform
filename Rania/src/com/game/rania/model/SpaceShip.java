package com.game.rania.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.items.Device;
import com.game.rania.model.items.Droid;
import com.game.rania.model.items.Engine;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Fuelbag;
import com.game.rania.model.items.Item;
import com.game.rania.model.items.Radar;
import com.game.rania.model.items.Hyper;
import com.game.rania.model.items.Shield;
import com.game.rania.model.items.Body;
import com.game.rania.model.items.Weapon;

public class SpaceShip extends Object{

	public String   shipName;
	
	public SpaceShip(float posX, float posY, String ShipName){
		super(RegionID.SHIP, posX, posY);
		shipName = ShipName;
	}
	
	public SpaceShip(float posX, float posY, float rotAngle, String ShipName){
		super(RegionID.SHIP, posX, posY, rotAngle);
		shipName = ShipName;
	}
	
	public SpaceShip(float posX, float posY, float rotAngle, float scaleX, float scaleY, String ShipName){
		super(RegionID.SHIP, posX, posY, rotAngle, scaleX, scaleY);
		shipName = ShipName;
	}
	
	private Vector2 targetPosition = new Vector2(0, 0);
	private Vector2 moveVec        = new Vector2(0, 0);
	private Vector2 addVec         = new Vector2(0, 0);
	private boolean move           = false;
	private float   speed          = 5.0f;

	public void setPositionTarget(Vector2 target){
		setPositionTarget(target.x, target.y);
	}
	
	public void stop(){
		move = false;
		moveVec.set(0, 0);
		targetPosition.set(position);
	}
	
	public void setPositionTarget(float x, float y){
		targetPosition.set(x, y);

		moveVec.set(targetPosition);
		moveVec.sub(position);
		moveVec.nor();
		moveVec.mul(speed * 100);

		move = true;
	}
	
	@Override
	public void update(float deltaTime){
		if (!move)
			return;

		addVec.set(moveVec);
		addVec.mul(deltaTime);

		if (!targetPosition.epsilonEquals(position, addVec.len()))
			position.add(addVec);
		else
			stop();
		angle = (float)Math.toDegrees(Math.atan2(-addVec.x, addVec.y));
	}
	
	@Override
	public boolean draw(ShapeRenderer shape){
		if (!visible || region == null)
			return false;
		shape.begin(ShapeType.FilledRectangle);
		float maxSize = Math.max(region.getRegionWidth(), region.getRegionHeight());
		if (body != null) {
			shape.setColor(new Color(1, 0, 0, 0.75f));
			shape.filledRect(position.x - maxSize * 0.5f, position.y + maxSize * 0.55f + 5, maxSize * (body.wear / body.item.durability), 5);
		}
		if (shield != null) {
			shape.setColor(new Color(0, 0, 1, 0.75f));
			shape.filledRect(position.x - maxSize * 0.5f, position.y + maxSize * 0.55f, maxSize * (shield.wear / shield.item.durability), 5);
		}
		if (fuelbag != null) {
			shape.setColor(new Color(0, 1, 0, 0.75f));
			shape.filledRect(position.x - maxSize * 0.5f, position.y - maxSize * 0.55f, maxSize * (fuel / maxFuel), 5);
		}
		shape.end();
		return true;
	}
	
	//equips
	public Equip<Engine>  engine  = null;
	public Equip<Droid>   droid   = null;
	public Equip<Fuelbag> fuelbag = null;
	public Equip<Radar>	  radar   = null;
	public Equip<Hyper>   hyper   = null;
	public Equip<Shield>  shield  = null;
	public Equip<Body> 	  body 	  = null;
	public Equip<Weapon>  weapon  = null;
	
	//characteristics
    public float fuel;
    public float maxFuel;
    public float maxSpeed;
	
	public List<Equip<Item>> inventory = new ArrayList<Equip<Item>>();

	public void setEquips(List<Equip<Item>> equips)
    {
        for (Equip<Item> equip : equips)
        {
            if (!equip.in_use)
            {
            	inventory.add(equip);
            }
            else
            {
                if (equip.item.getClass() == Body.class) {
                	this.body = new Equip<Body>(equip, Body.class);
                	continue;
                }

                if (equip.item.getClass() == Engine.class) {
                	this.engine = new Equip<Engine>(equip, Engine.class);
                	continue;
                }

                if (equip.item.getClass() == Fuelbag.class) {
                	this.fuelbag = new Equip<Fuelbag>(equip, Fuelbag.class);
                	continue;
                }

                if (equip.item.getClass() == Hyper.class) 
                {
                	this.hyper = new Equip<Hyper>(equip, Hyper.class);
                	continue;
                }

                if (equip.item.getClass() == Shield.class) 
                {
                	this.shield = new Equip<Shield>(equip, Shield.class);
                	continue;
                }

                if (equip.item.getClass() == Radar.class) 
                { 
                	this.radar = new Equip<Radar>(equip, Radar.class);
                	continue;
                }

                if (equip.item.getClass() == Weapon.class) 
                {
                	this.weapon = new Equip<Weapon>(equip, Weapon.class);
                	continue;
                }

                if (equip.item.getClass() == Droid.class) 
                {
                	this.droid = new Equip<Droid>(equip, Droid.class);
                	continue;
                }
            }
        }

        maxFuel = 0;
        maxSpeed = 0;
        
        if (fuelbag != null)
        {
            maxFuel = fuelbag.item.volume * fuelbag.item.compress / 100;
            fuel = maxFuel;
        }
        
        if (engine != null)
        {
            maxSpeed = engine.item.power;
        }
    }
	
    public void damage(Equip<?> equip, int value)
    {
    	equip.wear = Math.max(0, equip.wear - value);
    }
    
    public void repair(Equip<Device> equip, int value)
    {
    	equip.wear = Math.min(equip.item.durability, equip.wear + value);
    }

    public void unFuel(float f)
    {
        this.fuel -= f;
        if (this.fuel < 0)
        {
            this.fuel = 0;
            this.maxSpeed = 0;
        }
        if (this.fuel > maxFuel)
        {
            this.fuel = this.maxFuel;
        }
        this.maxSpeed = this.engine.item.power;
    }

    public void reFuel(float f)
    {
        this.fuel += f;
        if (this.fuel > maxFuel)
        {
            this.fuel = this.maxFuel;
        }
        this.maxSpeed = this.engine.item.power;
    }
}
