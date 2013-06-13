package com.game.rania.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.model.element.DynamicObject;
import com.game.rania.model.element.HUDDynamicObject;
import com.game.rania.model.element.HUDStaticObject;
import com.game.rania.model.element.StaticObject;

public class TouchObjectController extends InputAdapter{

	private MainController controller = null;
	
	public TouchObjectController(MainController controller){
		this.controller = controller;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 touchPoint = new Vector2(screenX, screenY);
		
		RaniaGame.mView.getHUDCamera().toCameraCoord(touchPoint);
		for(HUDStaticObject object : controller.getHUDStaticObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchDown(screenX, screenY))
					return true;
		}
		for(HUDDynamicObject object : controller.getHUDDynamicObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchDown(screenX, screenY))
					return true;
		}
		
		touchPoint.set(screenX, screenY);
		RaniaGame.mView.getCamera().toCameraCoord(touchPoint);
		for(DynamicObject object : controller.getDynamicObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchDown(screenX, screenY))
					return true;
		}
		for(StaticObject object : controller.getStaticObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchDown(screenX, screenY))
					return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		Vector2 touchPoint = new Vector2(screenX, screenY);
		
		RaniaGame.mView.getHUDCamera().toCameraCoord(touchPoint);
		for(HUDStaticObject object : controller.getHUDStaticObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchDragged(screenX, screenY))
					return true;
		}
		RaniaGame.mView.getHUDCamera().toCameraCoord(touchPoint);
		for(HUDDynamicObject object : controller.getHUDDynamicObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchDragged(screenX, screenY))
					return true;
		}
		
		touchPoint.set(screenX, screenY);
		RaniaGame.mView.getCamera().toCameraCoord(touchPoint);
		for(DynamicObject object : controller.getDynamicObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchDragged(screenX, screenY))
					return true;
		}
		for(StaticObject object : controller.getStaticObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchDragged(screenX, screenY))
					return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 touchPoint = new Vector2(screenX, screenY);
		
		RaniaGame.mView.getHUDCamera().toCameraCoord(touchPoint);
		for(HUDStaticObject object : controller.getHUDStaticObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchUp(screenX, screenY))
					return true;
		}
		for(HUDDynamicObject object : controller.getHUDDynamicObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchUp(screenX, screenY))
					return true;
		}
		
		touchPoint.set(screenX, screenY);
		RaniaGame.mView.getCamera().toCameraCoord(touchPoint);
		for(DynamicObject object : controller.getDynamicObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchUp(screenX, screenY))
					return true;
		}
		for(StaticObject object : controller.getStaticObjects()){
			if (object.touchObject && object.intersectObject(touchPoint.x, touchPoint.y))
				if (object.touchUp(screenX, screenY))
					return true;
		}
		return false;
	}
}
