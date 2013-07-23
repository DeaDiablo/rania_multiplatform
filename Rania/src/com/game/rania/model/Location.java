package com.game.rania.model;

import java.util.HashMap;

public class Location {
	public int id;
	public int x;
	public int y;
	public int starRadius;
	public int starType;
	public int domain;
	public String starName;
	public Star star = null;
	public HashMap<Integer, Planet> planets = null;
}
