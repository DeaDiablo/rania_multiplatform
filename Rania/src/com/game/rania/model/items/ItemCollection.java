package com.game.rania.model.items;

import java.util.HashMap;

public class ItemCollection{
	public HashMap<String, Body> 	bodies   = new HashMap<String, Body>();
	public HashMap<String, Engine> 	engines  = new HashMap<String, Engine>();
	public HashMap<String, Weapon> 	weapons  = new HashMap<String, Weapon>();
	public HashMap<String, Droid> 	droids 	 = new HashMap<String, Droid>();
	public HashMap<String, Fuelbag> fuelbags = new HashMap<String, Fuelbag>();
	public HashMap<String, Hyper> 	hypers 	 = new HashMap<String, Hyper>();
	public HashMap<String, Radar> 	radars 	 = new HashMap<String, Radar>();
	public HashMap<String, Shield> 	shields  = new HashMap<String, Shield>();
}
