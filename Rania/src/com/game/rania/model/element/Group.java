package com.game.rania.model.element;

import java.util.Vector;

public class Group {

	private Vector<Object> elements = new Vector<Object>();
	
	public Vector<Object> getElements(){
		return elements;
	}
	
	public void AddElement(Object object){
		elements.add(object);
	}

	public void RemoveElement(Object object){
		elements.remove(object);
	}

	public void RemoveElement(int num){
		elements.remove(num);
	}
}
