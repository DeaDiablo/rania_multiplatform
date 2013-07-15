package com.game.rania.model.items;

import java.util.ArrayList;
import java.util.List;

public class ItemCollection {
	public List<Item> items = new ArrayList<Item>();
	
	public ItemCollection()
	{
	
	}
	
	public int Count() 
    {
        return this.items.size();    
    }
	
	public Ship getShip(int id)
    {
        Ship dev = new Ship();
        for (int i = 0; i < this.Count(); i++)
        {
            if (this.items.get(i).id == id)
            {
                if (this.items.get(i).getShip() != null)
                {
                    dev = this.items.get(i).getShip();
                }
            }
        }
        return dev;
    }
    public Engine getEngine(int id)
    {
        Engine dev = new Engine();
        for (int i = 0; i < this.Count(); i++)
        {
            if (this.items.get(i).id == id)
            {
                if (this.items.get(i).getEngine() != null)
                {
                    dev = this.items.get(i).getEngine();
                }
            }
        }
        return dev;
    }
    public Weapon getWeapon(int id)
    {
        Weapon dev = new Weapon();
        for (int i = 0; i < this.Count(); i++)
        {
            if (this.items.get(i).id == id)
            {
                if (this.items.get(i).getWeapon() != null)
                {
                    dev = this.items.get(i).getWeapon();
                }
            }
        }
        return dev;
    }
    public Droid getDroid(int id)
    {
        Droid dev = new Droid();
        for (int i = 0; i < this.Count(); i++)
        {
            if (this.items.get(i).id == id)
            {
                if (this.items.get(i).getDroid() != null)
                {
                    dev = this.items.get(i).getDroid();
                }
            }
        }
        return dev;
    }
    public Fuelbag getFuelbag(int id)
    {
        Fuelbag dev = new Fuelbag();
        for (int i = 0; i < this.Count(); i++)
        {
            if (this.items.get(i).id == id)
            {
                if (this.items.get(i).getFuelbag() != null)
                {
                    dev = this.items.get(i).getFuelbag();
                }
            }
        }
        return dev;
    }
    public Hyper getHyper(int id)
    {
        Hyper dev = new Hyper();
        for (int i = 0; i < this.Count(); i++)
        {
            if (this.items.get(i).id == id)
            {
                if (this.items.get(i).getHyper() != null)
                {
                    dev = this.items.get(i).getHyper();
                }
            }
        }
        return dev;
    }
    public Radar getRadar(int id)
    {
        Radar dev = new Radar();
        for (int i = 0; i < this.Count(); i++)
        {
            if (this.items.get(i).id == id)
            {
                if (this.items.get(i).getRadar() != null)
                {
                    dev = this.items.get(i).getRadar();
                }
            }
        }
        return dev;
    }
    public Shield getShield(int id)
    {
        Shield dev = new Shield();
        for (int i = 0; i < this.Count(); i++)
        {
            if (this.items.get(i).id == id)
            {
                if (this.items.get(i).getShield() != null)
                {
                    dev = this.items.get(i).getShield();
                }
            }
        }
        return dev;
    }
}
