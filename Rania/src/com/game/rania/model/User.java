package com.game.rania.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.rania.controller.Controllers;
import com.game.rania.model.element.Font;
import com.game.rania.model.items.Droid;
import com.game.rania.model.items.EDroid;
import com.game.rania.model.items.EWeapon;
import com.game.rania.model.items.Engine;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Fuelbag;
import com.game.rania.model.items.Radar;
import com.game.rania.model.items.Hyper;
import com.game.rania.model.items.Shield;
import com.game.rania.model.items.Ship;
import com.game.rania.model.items.Weapon;

public class User extends SpaceShip{

	protected boolean isPlayer = false;
	public int id;
	public String pilotName;
	public Domain domain;
	public int inPlanet;
	public List<Equip> equips = new ArrayList<Equip>();
	public UserShip userShip = new UserShip();

	public User(int Id, float posX, float posY, String ShipName, String PilotName, int Domain) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		domain = Controllers.locController.getDomain(Domain);
		inPlanet = 0;
		zIndex = Indexes.users;
		textShip.content = shipName;
	}

	public User(int Id, float posX, float posY, String ShipName, String PilotName, int Domain, int InPlanet) {
		super(posX, posY, ShipName);
		id = Id;
		pilotName = PilotName;
		domain = Controllers.locController.getDomain(Domain);
		inPlanet = InPlanet;
		zIndex = Indexes.users;
		textShip.content = shipName;
	}
	
	protected Text textShip = new Text("", Font.getFont("data/fonts/arial.ttf", 20), new Color(1, 1, 1, 1), 0, 0);
	
	@Override
	public boolean draw(SpriteBatch sprite){
		if (!super.draw(sprite))
			return false;
		if (!isPlayer)
			textShip.draw(sprite, position.x, position.y + region.getRegionHeight() * 0.5f);
		return true;
	}
	
	public void updateUserShip()
    {
        for (int i = 0; i < this.equips.size(); i++)
        {
            if (this.equips.get(i).in_use)
            {
                Ship ship = this.equips.get(i).item.getShip();
                if (ship != null) 
                { 

                    this.userShip.ship.device = ship;
                    this.userShip.ship.equip = this.equips.get(i);
                }
                Engine engine = this.equips.get(i).item.getEngine();
                if (engine != null) 
                { 
                    this.userShip.engine.device = engine;
                    this.userShip.engine.equip = this.equips.get(i);
                }
                Fuelbag fuelbag = this.equips.get(i).item.getFuelbag();
                if (fuelbag != null) 
                { 
                    this.userShip.fuelbag.device = fuelbag;
                    this.userShip.fuelbag.equip = this.equips.get(i);
                }
                Hyper hyper = this.equips.get(i).item.getHyper();
                if (hyper != null) 
                { 
                    this.userShip.hyper.device = hyper;
                    this.userShip.hyper.equip = this.equips.get(i);
                }
                Shield shield = this.equips.get(i).item.getShield();
                if (shield != null) 
                { 
                    this.userShip.shield.device = shield;
                    this.userShip.shield.equip = this.equips.get(i);
                }
                Radar radar = this.equips.get(i).item.getRadar();
                if (radar != null) 
                { 
                    this.userShip.radar.device = radar;
                    this.userShip.radar.equip = this.equips.get(i);
                }
                Weapon weapon = this.equips.get(i).item.getWeapon();
                if (weapon != null) 
                {
                    EWeapon eWeapon = new EWeapon();
                    eWeapon.device = weapon;
                    eWeapon.equip = this.equips.get(i);
                    this.userShip.weapons.add(eWeapon); 
                }
                Droid droid = this.equips.get(i).item.getDroid();
                if (droid != null) 
                {
                    EDroid eDroid = new EDroid();
                    eDroid.device = droid;
                    eDroid.equip = this.equips.get(i);
                    this.userShip.droids.add(eDroid); 
                }
                this.userShip.maxFuel = 0;
                this.userShip.maxSpeed = 0;
                if (this.userShip.fuelbag != null)
                {
                    this.userShip.maxFuel = this.userShip.fuelbag.device.volume * this.userShip.fuelbag.device.compress / 100;
                }
                if (this.userShip.engine != null)
                {
                    this.userShip.maxSpeed = this.userShip.engine.device.power;
                }
            }
        }
    }
}
