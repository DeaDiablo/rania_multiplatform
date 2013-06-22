package com.game.rania.controller;

import java.util.Vector;

import com.badlogic.gdx.InputMultiplexer;
import com.game.rania.model.element.DynamicObject;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.HUDDynamicObject;
import com.game.rania.model.element.HUDStaticObject;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.StaticObject;

public class MainController extends InputMultiplexer{

	private Vector<UpdateController> updateControllers = new Vector<UpdateController>();
	//objects
	private Vector<DynamicObject> 	 dynamicObjects    = new Vector<DynamicObject>();
	private Vector<StaticObject>  	 staticObjects     = new Vector<StaticObject>();
	private Vector<HUDDynamicObject> dynamicHUDObjects = new Vector<HUDDynamicObject>();
	private Vector<HUDStaticObject>  staticHUDObjects  = new Vector<HUDStaticObject>();
	private Vector<DynamicObject> 	 removeDObjects    = new Vector<DynamicObject>();
	private Vector<StaticObject>  	 removeSObjects    = new Vector<StaticObject>();
	private Vector<HUDDynamicObject> removeHDObjects   = new Vector<HUDDynamicObject>();
	private Vector<HUDStaticObject>  removeHSObjects   = new Vector<HUDStaticObject>();

	public MainController(){
		super();
	}
	
	public void init(){
		clear();
		if (Controllers.inputController != null)
			super.addProcessor(0, Controllers.inputController);
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
	
	//dynamic objects
	public Vector<DynamicObject> getDynamicObjects(){
		return dynamicObjects;
	}
	
	public void addDynamicObject(DynamicObject object){
		if (!dynamicObjects.contains(object))
			dynamicObjects.add(object);
	}
	
	public void removeDynamicObject(DynamicObject object){
		removeDObjects.add(object);
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
		removeSObjects.add(object);
	}
	
	//HUD objects
	public Vector<HUDStaticObject> getHUDStaticObjects(){
		return staticHUDObjects;
	}
	
	public void addStaticHUDObject(HUDStaticObject object){
		if (!staticHUDObjects.contains(object))
			staticHUDObjects.add(object);
	}
	
	public void removeStaticHUDObject(HUDStaticObject object){
		removeHSObjects.add(object);
	}
	
	public Vector<HUDDynamicObject> getHUDDynamicObjects(){
		return dynamicHUDObjects;
	}
	
	public void addDynamicHUDObject(HUDDynamicObject object){
		if (!dynamicHUDObjects.contains(object))
			dynamicHUDObjects.add(object);
	}
	
	
	public void removeDynamicHUDObject(HUDDynamicObject object){
		removeHDObjects.add(object);
	}
	
	//all object
	public void addObject(Object object){
		if (object.asHUDDynamicObject() != null)
		{
			addDynamicHUDObject(object.asHUDDynamicObject());
			return;
		}

		if (object.asHUDStaticObject() != null)
		{
			addStaticHUDObject(object.asHUDStaticObject());
			return;
		}

		if (object.asDynamicObject() != null) {
			addDynamicObject(object.asDynamicObject());
			return;
		}
		
		if (object.asStaticObject() != null) {
			addStaticObject(object.asStaticObject());
			return;
		}
	}
	
	public void removeObject(Object object){		
		if (object.asHUDDynamicObject() != null)
		{
			removeDynamicHUDObject(object.asHUDDynamicObject());
			return;
		}
		
		if (object.asHUDStaticObject() != null)
		{
			removeStaticHUDObject(object.asHUDStaticObject());
			return;
		}

		if (object.asDynamicObject() != null) {
			removeDynamicObject(object.asDynamicObject());
			return;
		}
		
		if (object.asStaticObject() != null) {
			removeStaticObject(object.asStaticObject());
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
	
	public Vector<Object> getObjects() {
		Vector<Object> objects = new Vector<Object>();
		objects.addAll(staticObjects);
		objects.addAll(dynamicObjects);
		objects.addAll(staticHUDObjects);
		objects.addAll(dynamicHUDObjects);
		return objects;
	}
	
	public void update(float deltaTime){
		dynamicObjects.removeAll(removeDObjects);
		staticObjects.removeAll(removeSObjects);
		dynamicHUDObjects.removeAll(removeHDObjects);
		staticHUDObjects.removeAll(removeHSObjects);
		removeDObjects.clear();
		removeSObjects.clear();
		removeHDObjects.clear();
		removeHSObjects.clear();
		
		Controllers.commandController.updateCommands(deltaTime);
		
		for (UpdateController controller : updateControllers) {
			controller.update(deltaTime);
		}

		for (DynamicObject object : dynamicObjects) {
			object.update(deltaTime);
		}
		
		for (HUDDynamicObject object : dynamicHUDObjects){
			object.update(deltaTime);
		}
	}

	public void clear() {
		super.clear();
		updateControllers.clear();
		dynamicObjects.clear();
		staticObjects.clear();
		staticHUDObjects.clear();
		dynamicHUDObjects.clear();
	}
}
