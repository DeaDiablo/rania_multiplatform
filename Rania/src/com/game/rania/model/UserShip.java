package com.game.rania.model;

import java.util.ArrayList;
import java.util.List;

import com.game.rania.model.items.EDroid;
import com.game.rania.model.items.EWeapon;
import com.game.rania.model.items.Engine;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Fuelbag;
import com.game.rania.model.items.Hyper;
import com.game.rania.model.items.Shield;
import com.game.rania.model.items.Ship;
import com.game.rania.model.items.Radar;

public class UserShip {
	public class EShip
    {
        public Equip equip = new Equip();
        public Ship device = new Ship();
    }
    public class EEngine
    {
        public Equip equip = new Equip();
        public Engine device = new Engine();
    }
    public class EFuelbag
    {
        public Equip equip = new Equip();
        public Fuelbag device = new Fuelbag();
    }
    public class EHyper
    {
        public Equip equip = new Equip();
        public Hyper device = new Hyper();
    }
    public class EShield
    {
        public Equip equip = new Equip();
        public Shield device = new Shield();
    }
    public class ERadar
    {
        public Equip equip = new Equip();
        public Radar device = new Radar();
    }
    
    public EShip ship = new EShip();
    public EEngine engine = new EEngine();
    public EFuelbag fuelbag = new EFuelbag();
    public EHyper hyper = new EHyper();
    public EShield shield = new EShield();
    public ERadar radar = new ERadar();
    public List<EWeapon> weapons = new ArrayList<EWeapon>();
    public List<EDroid> droids = new ArrayList<EDroid>();

    public float fuel;
    public float maxFuel;
    public float maxSpeed;
    
    public void shipDamage(int dmg)
    {
        this.ship.equip.condition -= dmg;
        if (this.ship.equip.condition < 0) 
        { 
            this.ship.equip.condition = 0; 
        }
    }
    public void engineDamage(int dmg)
    {
        this.engine.equip.condition -= dmg;
        if (this.engine.equip.condition < 0) 
        { 
            this.engine.equip.condition = 0; 
        }
    }
    public void fuelbagDamage(int dmg)
    {
        this.fuelbag.equip.condition -= dmg;
        if (this.fuelbag.equip.condition < 0) 
        { 
            this.fuelbag.equip.condition = 0; 
        }
    }
    public void hyperDamage(int dmg)
    {
        this.hyper.equip.condition -= dmg;
        if (this.hyper.equip.condition < 0) 
        { 
            this.hyper.equip.condition = 0; 
        }
    }
    public void shieldDamage(int dmg)
    {
        this.shield.equip.condition -= dmg;
        if (this.shield.equip.condition < 0) 
        { 
            this.shield.equip.condition = 0; 
        }
    }
    public void radarDamage(int dmg)
    {
        this.radar.equip.condition -= dmg;
        if (this.radar.equip.condition < 0) 
        { 
            this.radar.equip.condition = 0; 
        }
    }
    public void weaponDamage(int idW, int dmg)
    {
        this.weapons.get(idW).equip.condition -= dmg;
        if (this.weapons.get(idW).equip.condition < 0) 
        { 
            this.weapons.get(idW).equip.condition = 0; 
        }
    }
    public void droidDamage(int idD, int dmg)
    {
        this.droids.get(idD).equip.condition -= dmg;
        if (this.droids.get(idD).equip.condition < 0) 
        { 
            this.droids.get(idD).equip.condition = 0; 
        }
    }
    
    public void shipRepair(int dmg)
    {
        this.ship.equip.condition += dmg;
        if (this.ship.equip.condition > this.ship.device.durability) 
        { 
            this.ship.equip.condition = this.ship.device.durability; 
        }
    }
    public void fuelbagRepair(int dmg)
    {
        this.fuelbag.equip.condition += dmg;
        if (this.fuelbag.equip.condition > this.fuelbag.device.durability) 
        { 
            this.fuelbag.equip.condition = this.fuelbag.device.durability; 
        }
    }
    public void hyperRepair(int dmg)
    {
        this.hyper.equip.condition += dmg;
        if (this.hyper.equip.condition > this.hyper.device.durability) 
        { 
            this.hyper.equip.condition = this.hyper.device.durability; 
        }
    }
    public void shieldRepair(int dmg)
    {
        this.shield.equip.condition += dmg;
        if (this.shield.equip.condition > this.shield.device.durability) 
        { 
            this.shield.equip.condition = this.shield.device.durability; 
        }
    }
    public void radarRepair(int dmg)
    {
        this.radar.equip.condition += dmg;
        if (this.radar.equip.condition > this.radar.device.durability) 
        { 
            this.radar.equip.condition = this.radar.device.durability; 
        }
    }
    public void weaponRepair(int idW, int dmg)
    {
        this.weapons.get(idW).equip.condition += dmg;
        if (this.weapons.get(idW).equip.condition > this.weapons.get(idW).device.durability) 
        { 
            this.weapons.get(idW).equip.condition = this.weapons.get(idW).device.durability; 
        }
    }
    public void droidRepair(int idD, int dmg)
    {
        this.droids.get(idD).equip.condition += dmg;
        if (this.droids.get(idD).equip.condition > this.droids.get(idD).device.durability) 
        { 
            this.droids.get(idD).equip.condition = this.droids.get(idD).device.durability; 
        }
    }
    
    public void unFill(float f)
    {
        this.fuel -= f;
        if (this.fuel < 0)
        {
            this.fuel = 0;
            this.maxSpeed = 0;
        }
    }
    public void reFill(float f)
    {
        this.fuel += f;
        if (this.fuel > maxFuel)
        {
            this.fuel = this.maxFuel;
        }
        this.maxSpeed = this.engine.device.power;
    }
    public void unFill(double f)
    {
        this.fuel -= (float)f;
        if (this.fuel < 0)
        {
            this.fuel = 0;
            this.maxSpeed = 0;
        }
    }
    public void reFill(double f)
    {
        this.fuel += (float)f;
        if (this.fuel > maxFuel)
        {
            this.fuel = this.maxFuel;
        }
        this.maxSpeed = this.engine.device.power;
    }
}
