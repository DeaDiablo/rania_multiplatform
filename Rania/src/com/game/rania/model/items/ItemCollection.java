package com.game.rania.model.items;

import java.util.HashMap;

import com.game.rania.model.items.weapons.Weapon;

public class ItemCollection
{
  public HashMap<Integer, Body>       bodies      = new HashMap<Integer, Body>();
  public HashMap<Integer, Engine>     engines     = new HashMap<Integer, Engine>();
  public HashMap<Integer, Weapon>     weapons     = new HashMap<Integer, Weapon>();
  public HashMap<Integer, RepairKit>  droids      = new HashMap<Integer, RepairKit>();
  public HashMap<Integer, Battery>    fuelbags    = new HashMap<Integer, Battery>();
  public HashMap<Integer, Hyper>      hypers      = new HashMap<Integer, Hyper>();
  public HashMap<Integer, Radar>      radars      = new HashMap<Integer, Radar>();
  public HashMap<Integer, Shield>     shields     = new HashMap<Integer, Shield>();
  public HashMap<Integer, Consumable> consumables = new HashMap<Integer, Consumable>();
}
