package com.game.rania.controller;

import java.util.Collection;
import java.util.HashMap;

import com.game.rania.model.Location;
import com.game.rania.model.ParallaxLayer;
import com.game.rania.model.ParallaxObject;
import com.game.rania.model.Planet;
import com.game.rania.model.Player;
import com.game.rania.model.Radar;
import com.game.rania.model.Star;
import com.game.rania.model.User;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.RegionID;
import com.game.rania.view.MainView;

public class LocationController {
	
	private MainView mView = null;
	private MainController mController = null;
	private ClientController cController = null;
	
	public LocationController(MainController mController, MainView mView, ClientController cController) {
		this.mView = mView;
		this.mController = mController;
		this.cController = cController;
	}
	
	public void loadTextures(){
		for (int i = 0; i < 18; i++)
			mView.loadTexture("data/sprites/planets.png", RegionID.fromInt(RegionID.PLANET_0.ordinal() + i), i % 5 * 204, i / 5 * 204, 204, 204);
		
		for (int i = 0; i < 8; i++)
			mView.loadTexture("data/backgrounds/nebulas.png", RegionID.fromInt(RegionID.NEBULA_0.ordinal() + i), i % 4 * 256, i / 4 * 256, 256, 256);

		mView.loadTexture("data/sprites/star.png",        RegionID.STAR);
		mView.loadTexture("data/sprites/radar.png", 	  RegionID.RADAR);
		mView.loadTexture("data/sprites/sensor.png", 	  RegionID.RADAR_SENSOR);
		mView.loadTexture("data/sprites/radarObject.png", RegionID.RADAR_OBJECT);
		mView.loadTexture("data/sprites/SpaceShip.png",   RegionID.SHIP);
		mView.loadTexture("data/backgrounds/space.png",   RegionID.BACKGROUND_SPACE);
		mView.loadTexture("data/backgrounds/stars.png",   RegionID.BACKGROUND_STARS);
	}

	//list objects
	private HashMap<String, Location> locations = null;
	private HashMap<String, Planet>   planets   = null;
	private HashMap<String, User> 	  users     = null;
	//objects
	private Player   player 		 = null;
	private Group	 background		 = null;
	private Radar    radar  		 = null;
	private Star	 star 			 = null;
	private	Location currentLocation = null;
	//help objects
	private ShipController pController = null;

	public void loadLocations() {
		locations = cController.getLocationList();
	}
	
	//player
	public boolean loadPlayer(){
		player = cController.getPlayerData();
		if (player == null)
			return false;
		currentLocation = getLocation(player.idLocation);
		if (currentLocation == null)
			return false;
		return true;
	}
	
	public void setPlayer(Player newPlayer){
		removePlayer();
		player = newPlayer;
		addPlayer();
	}
	
	public void addPlayer(){
		if (player != null)
		{
			mController.addDynamicObject(player);
			pController = new ShipController(player);
			mController.addProcessor(pController);
		}
	}
	
	public void removePlayer(){
		if (player != null) {
			mController.removeDynamicObject(player);
			if (pController != null)
				mController.removeProcessor(pController);
			player = null;
		}
	}
	
	//background
	public void loadBackground(){
		background = new Group();
		background.addElement(new ParallaxLayer(RegionID.BACKGROUND_SPACE, 250, 300, -0.35f));
		background.addElement(new ParallaxLayer(RegionID.BACKGROUND_STARS, -150, 0, -0.25f));
		background.addElement(new ParallaxObject(RegionID.NEBULA_3, 500, 500, 45, 3, 3, -0.4f));
		background.addElement(new ParallaxObject(RegionID.NEBULA_5, -500, 500, -45, 5, 5, -0.4f));
		background.addElement(new ParallaxObject(RegionID.NEBULA_6, 500, -500, 0, 4, 4, -0.4f));
		background.addElement(new ParallaxObject(RegionID.NEBULA_7, -500, -500, 200, 2, 2, -0.4f));
	}
	
	public void setBackground(Group newBackground){
		removeBackground();
		background = newBackground;
		addBackground();
	}
	
	public void addBackground(){
		if (background != null)
			mController.addObject(background);
	}

	public void removeBackground(){
		if (background != null) {
			mController.removeObject(background);
			background = null;
		}
	}
	
	//radar
	public void loadRadar(){
		radar = new Radar(player,
						 (mView.getHUDCamera().getWidth()  - mView.getTextureRegion(RegionID.RADAR).getRegionWidth())  * 0.5f,
						 (mView.getHUDCamera().getHeight() - mView.getTextureRegion(RegionID.RADAR).getRegionHeight()) * 0.5f,
						  2000, 2000);
	}
	
	public void setRadar(Radar newRadar){
		removeRadar();
		radar = newRadar;
		addRadar();
	}
	
	public void addRadar(){
		if (radar != null)
			mController.addDynamicHUDObject(radar);
	}

	public void removeRadar(){
		if (radar != null) {
			mController.removeObject(radar);
			radar = null;
		}
	}
	
	//planets
	public void loadPlanets(){
		if (currentLocation == null)
			return;
		star = new Star(RegionID.STAR, currentLocation.starRadius);
		planets = cController.getPlanetList();
	}
	
	public void updatePlanets(){
		removePlanets();
		planets = cController.getPlanetList();
		addPlanets();
	}
	
	public void addPlanets(){
		mController.addStaticObject(star);
		for (Planet planet : planets.values()) {
			mController.addDynamicObject(planet);
		}
	}

	public void removePlanets(){
		if (star != null) {
			mController.removeStaticObject(star);
			star = null;
		}
		for (Planet planet : planets.values()) {
			mController.removeDynamicObject(planet);
		}
	}

	public void addPlanet(Planet planet){
		String key = String.valueOf(planet.id);
		if (planets.containsKey(key))
			return;
		planets.put(key, planet);
		mController.addDynamicObject(planet);
	}
	
	public void removePlanet(Planet planet){
		String key = String.valueOf(planet.id);
		if (!planets.containsKey(key))
			return;
		planets.remove(key);
		mController.removeDynamicObject(planet);
	}

	public void removePlanet(int id){
		String key = String.valueOf(id);
		Planet planet = planets.get(key);
		if (planet == null)
			return;
		planets.remove(key);
		mController.removeDynamicObject(planet);
	}
	
	//users
	public void loadUsers(){
		users = cController.getUsersList();
	}
	
	public void updateUsers(){
		removeUsers();
		users = cController.getUsersList();
		addUsers();
	}
	
	public void addUsers(){
		for (User user : users.values()) {
			mController.addDynamicObject(user);
		}
	}

	public void removeUsers(){
		for (User user : users.values()) {
			mController.removeDynamicObject(user);
		}
	}

	public void addUser(User user) {
		String key = String.valueOf(user.id);
		if (users.containsKey(key))
			return;
		users.put(key, user);
		mController.addDynamicObject(user);
	}
	
	public void removeUser(User user) {
		String key = String.valueOf(user.id);
		if (!users.containsKey(key))
			return;
		users.remove(key);
		mController.removeDynamicObject(user);
	}
	
	public void removeUser(int id) {
		String key = String.valueOf(id);
		User user = users.get(key);
		if (user == null)
			return;
		users.remove(key);
		mController.removeDynamicObject(user);
	}
	
	//get objects
	public Player getPlayer(){
		return player;
	}
	
	public Radar getRadar(){
		return radar;
	}

	public Star getStar(){
		return star;
	}

	//get locations
	public Collection<Location> getLocations(){
		return locations.values();
	}

	public Location getLocation(int id){
		return locations.get(String.valueOf(id));
	}

	public Location getLocation(int x, int y){
		for (Location loc : locations.values()) {
			if (loc.x == x && loc.y == y)
				return loc;
		}
		return null;
	}
	
	//get planets
	public Collection<Planet> getPlanets(){
		return planets.values();
	}
	
	public Planet getPlanet(int id){
		return planets.get(String.valueOf(id));
	}

	public Planet getPlanet(String name){
		for (Planet planet : planets.values()) {
			if (planet.name.compareTo(name) == 0)
				return planet;
		}
		return null;
	}
	
	//get users
	public Collection<User> getUsers(){
		return users.values();
	}
	
	public User getUser(int id){
		return users.get(String.valueOf(id));
	}
	
	public User getPilot(String name){		
		for (User user : users.values()) {
		if (user.pilotName.compareTo(name) == 0)
			return user;
		}
		return null;
	}
	
	public User getShip(String name){		
		for (User user : users.values()) {
		if (user.shipName.compareTo(name) == 0)
			return user;
		}
		return null;
	}
}
