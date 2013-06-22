package com.game.rania.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.model.SpaceShip;
import com.game.rania.view.Camera;

public class ShipController extends UpdateController{

	private Vector2 touchPoint = new Vector2(0, 0);

	private SpaceShip controllObject = null;
	private Camera camera = null;
	
	public ShipController(SpaceShip ship){
		controllObject = ship;
		camera = RaniaGame.mView.getCamera();
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		touchPoint.set(x, Gdx.graphics.getHeight() - y);
		RaniaGame.mView.getCamera().toCameraCoord(touchPoint);
		Controllers.clientController.SendTouchPoint((int)touchPoint.x, (int)touchPoint.y, (int)controllObject.position.x, (int)controllObject.position.y);
		controllObject.setPositionTarget(touchPoint);
		return true;
	}

	@Override
	public void update(float deltaTime) {
		if (controllObject != null)
			camera.position.set(controllObject.position.x, controllObject.position.y, 0);
	}
}
