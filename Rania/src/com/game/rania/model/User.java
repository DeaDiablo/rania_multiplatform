package com.game.rania.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.rania.controller.Controllers;
import com.game.rania.model.element.Font;
import com.game.rania.model.items.Droid;
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
                if (ship != null) { this.userShip.ship = ship; }
                Engine engine = this.equips.get(i).item.getEngine();
                if (engine != null) { this.userShip.engine = engine; }
                Fuelbag fuelbag = this.equips.get(i).item.getFuelbag();
                if (fuelbag != null) { this.userShip.fuelbag = fuelbag; }
                Hyper hyper = this.equips.get(i).item.getHyper();
                if (hyper != null) { this.userShip.hyper = hyper; }
                Shield shield = this.equips.get(i).item.getShield();
                if (shield != null) { this.userShip.shield = shield; }
                Radar radar = this.equips.get(i).item.getRadar();
                if (radar != null) { this.userShip.radar = radar; }
                Weapon weapon = this.equips.get(i).item.getWeapon();
                if (weapon != null) { this.userShip.weapons.add(weapon); }
                Droid droid = this.equips.get(i).item.getDroid();
                if (droid != null) { this.userShip.droids.add(droid); }
            }
        }
    }
}
