package com.game.rania.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.model.SpaceShip;

public class ShipController extends InputAdapter{

	private Vector2 touchPoint = new Vector2(0, 0);

	private SpaceShip controllObject = null;
	
	public ShipController(SpaceShip ship){
		controllObject = ship;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		touchPoint.set(x, Gdx.graphics.getHeight() - y);
		RaniaGame.mView.getCamera().toCameraCoord(touchPoint);
		RaniaGame.nController.SendTouchPoint((int)touchPoint.x, (int)touchPoint.y, RaniaGame.mUser);
		controllObject.setPositionTarget(touchPoint);
		return true;
	}
}
