package com.game.rania.controller;

import java.util.Vector;

import com.badlogic.gdx.InputMultiplexer;
import com.game.rania.model.Player;
import com.game.rania.model.element.DynamicObject;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.HUDObject;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.StaticObject;

public class MainController extends InputMultiplexer{
	
	private CommandController		 commandController = new CommandController(this);		 
	private Vector<UpdateController> updateControllers = new Vector<UpdateController>();
	private Vector<DynamicObject> 	 dynamicObjects    = new Vector<DynamicObject>();
	private Vector<StaticObject>  	 staticObjects     = new Vector<StaticObject>();
	private Vector<HUDObject> 	  	 HUDObjects  	   = new Vector<HUDObject>();
	private Player 			 		 mPlayer 		   = null;

	public MainController(){
		super();
	}
	
	public CommandController getCommandController(){
		return commandController;
	}
	
	//update controllers
	public void addProcessor(UpdateController controller){
		if (!updateControllers.contains(controller))
		{
			updateControllers.add(controller);
			super.addProcessor(controller);
		}
	}
	
	public void removeProcessor(UpdateController controller){
		if (!updateControllers.contains(controller))
		{
			updateControllers.remove(controller);
			super.removeProcessor(controller);
		}
	}
	
	//player
	public Player getPlayer(){
		return mPlayer;
	}
	
	public void setPlayer(Player player)
	{
		mPlayer = player;
	}
	
	//dynamic objects
	public Vector<DynamicObject> getDynamicObjects(){
		return dynamicObjects;
	}
	
	public void addDynamicObject(DynamicObject object){
		if (!dynamicObjects.contains(object))
			dynamicObjects.add(object);
	}
	
	public void removeDynamicObject(DynamicObject object){
		dynamicObjects.remove(object);
	}

	//static objects
	public Vector<StaticObject> getStaticObjects(){
		return staticObjects;
	}
	
	public void addStaticObject(StaticObject object){
		if (!staticObjects.contains(object))
			staticObjects.add(object);
	}
	
	public void removeStaticObject(StaticObject object){
		staticObjects.remove(object);
	}
	
	//HUD objects
	public Vector<HUDObject> getHUDObjects(){
		return HUDObjects;
	}
	
	public void addHUDObject(HUDObject object){
		if (!HUDObjects.contains(object))
			HUDObjects.add(object);
	}
	
	
	public void removeHUDObject(HUDObject object){
		HUDObjects.remove(object);
	}
	
	//all object
	public void addObject(Object object){
		if (object.asDynamicObject() != null) {
			addDynamicObject(object.asDynamicObject());
			return;
		}
		
		if (object.asStaticObject() != null) {
			addStaticObject(object.asStaticObject());
			return;
		}
		
		if (object.asHUDObject() != null)
		{
			addHUDObject(object.asHUDObject());
			return;
		}
	}
	
	public void removeObject(Object object){
		if (object.asDynamicObject() != null) {
			removeDynamicObject(object.asDynamicObject());
			return;
		}
		
		if (object.asStaticObject() != null) {
			removeStaticObject(object.asStaticObject());
			return;
		}
		
		if (object.asHUDObject() != null)
		{
			removeHUDObject(object.asHUDObject());
			return;
		}
	}
	
	public void addObject(Group group){
		for(Object element : group.getElements()){
			addObject(element);
		}
	}
	
	public void removeObject(Group group){
		for(Object element : group.getElements()){
			removeObject(element);
		}
	}
	
	public void update(float deltaTime){
		commandController.updateCommands(deltaTime);
		
		for (UpdateController controller : updateControllers) {
			controller.update(deltaTime);
		}

		if (mPlayer != null)
		{
			mPlayer.update(deltaTime);
			//RaniaGame.mCLient.x = (int)mPlayer.position.x;
			//RaniaGame.mCLient.y = (int)mPlayer.position.y;
		}

		for (DynamicObject object : dynamicObjects) {
			object.update(deltaTime);
		}
	}

	public void clear() {
		super.clear();
		updateControllers.clear();
		dynamicObjects.clear();
		staticObjects.clear();
		HUDObjects.clear();
		mPlayer = null;
	}
}
