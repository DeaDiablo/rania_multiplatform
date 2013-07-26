package com.game.rania.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.model.element.Object;
import com.game.rania.model.ui.FocusElement;

public class InputController extends InputAdapter{

	private MainController mController = null;
	
	public InputController(MainController controller){
		mController = controller;
	}
	
	@Override
	public boolean keyDown (int keycode) {
		for(Object object : mController.getReverseHUDObjects()){
			if (object.keysObject)
				if (object.keyDown(keycode))
					return true;
		}
		for(Object object : mController.getReverseObjects()){
			if (object.keysObject)
				if (object.keyDown(keycode))
					return true;
		}
		return false;
	}

	@Override
	public boolean keyUp (int keycode) {
		for(Object object : mController.getReverseHUDObjects()){
			if (object.keysObject)
				if (object.keyUp(keycode))
					return true;
		}
		for(Object object : mController.getReverseObjects()){
			if (object.keysObject)
				if (object.keyUp(keycode))
					return true;
		}
		return false;
	}
	
	private static int noneChar = 0;
	private static int backspaceChar = 8;
	private static int tabChar = 9;
	private static int enterChar = 13;
	private static int delChar = 127;

	@Override
	public boolean keyTyped (char character) {
		if (character == noneChar || 
			character == backspaceChar ||
			character == tabChar ||
			character == enterChar ||
			character == delChar)
			return false;
		
		for(Object object : mController.getReverseHUDObjects()){
			if (object.keysObject)
				if (object.keyTyped(character))
					return true;
		}

		for(Object object : mController.getReverseObjects()){
			if (object.keysObject)
				if (object.keyTyped(character))
					return true;
		}
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY = Gdx.graphics.getHeight() - screenY;
		Vector2 touchPoint = new Vector2(screenX, screenY);
		
		RaniaGame.mView.getHUDCamera().toCameraCoord(touchPoint);
		for(Object object : mController.getReverseHUDObjects()){
			if ((object.touchObject && object.visible && object.intersectObject(touchPoint.x, touchPoint.y)) || object.allTouchObject)
				if (object.touchDown(-object.position.x + touchPoint.x, -object.position.y + touchPoint.y))
					return true;
		}
		
		touchPoint.set(screenX, screenY);
		RaniaGame.mView.getCamera().toCameraCoord(touchPoint);
		for(Object object : mController.getReverseObjects()){
			if ((object.touchObject && object.visible && object.intersectObject(touchPoint.x, touchPoint.y)) || object.allTouchObject)
				if (object.touchDown(-object.position.x + touchPoint.x, -object.position.y + touchPoint.y))
					return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		screenY = Gdx.graphics.getHeight() - screenY;
		Vector2 touchPoint = new Vector2(screenX, screenY);
		
		RaniaGame.mView.getHUDCamera().toCameraCoord(touchPoint);
		for(Object object : mController.getReverseHUDObjects()){
			if ((object.touchObject && object.visible && object.intersectObject(touchPoint.x, touchPoint.y)) || object.allTouchObject)
				if (object.touchDragged(-object.position.x + touchPoint.x, -object.position.y + touchPoint.y))
					return true;
		}
		
		touchPoint.set(screenX, screenY);
		RaniaGame.mView.getCamera().toCameraCoord(touchPoint);
		for(Object object : mController.getReverseObjects()){
			if ((object.touchObject && object.visible && object.intersectObject(touchPoint.x, touchPoint.y)) || object.allTouchObject)
				if (object.touchDragged(-object.position.x + touchPoint.x, -object.position.y + touchPoint.y))
					return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = Gdx.graphics.getHeight() - screenY;
		Vector2 touchPoint = new Vector2(screenX, screenY);
		
		RaniaGame.mView.getHUDCamera().toCameraCoord(touchPoint);
		for(Object object : mController.getReverseHUDObjects()){
			if ((object.touchObject && object.visible && object.intersectObject(touchPoint.x, touchPoint.y)) || object.allTouchObject)
				if (object.touchUp(-object.position.x + touchPoint.x, -object.position.y + touchPoint.y))
					return true;
		}
		
		touchPoint.set(screenX, screenY);
		RaniaGame.mView.getCamera().toCameraCoord(touchPoint);
		for(Object object : mController.getReverseObjects()){
			if ((object.touchObject && object.visible && object.intersectObject(touchPoint.x, touchPoint.y)) || object.allTouchObject)
				if (object.touchUp(-object.position.x + touchPoint.x, -object.position.y + touchPoint.y))
					return true;
		}
		FocusElement.clearFocus();
		return false;
	}
}
