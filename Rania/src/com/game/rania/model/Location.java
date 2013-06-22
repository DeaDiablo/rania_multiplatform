package com.game.rania.model;

import java.util.Vector;

public class Location{
	public int id;
	public int x;
	public int y;
	public int starRadius;
	public int starType;
	public String starName;
	public Vector<Planet> planets = new Vector<Planet>();
}
