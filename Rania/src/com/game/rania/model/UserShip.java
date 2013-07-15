package com.game.rania.model;

import java.util.ArrayList;
import java.util.List;

import com.game.rania.model.items.Droid;
import com.game.rania.model.items.Engine;
import com.game.rania.model.items.Fuelbag;
import com.game.rania.model.items.Hyper;
import com.game.rania.model.items.Shield;
import com.game.rania.model.items.Ship;
import com.game.rania.model.items.Radar;
import com.game.rania.model.items.Weapon;

public class UserShip {
	public Ship ship;
    public Engine engine;
    public Fuelbag fuelbag;
    public Hyper hyper;
    public Shield shield;
    public Radar radar;
    public List<Weapon> weapons = new ArrayList<Weapon>();
    public List<Droid> droids = new ArrayList<Droid>();
}
