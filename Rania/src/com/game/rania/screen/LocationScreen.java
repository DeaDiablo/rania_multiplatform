package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.LocationController;
import com.game.rania.screen.part.Parts;
import com.game.rania.screen.part.SideBar;

public class LocationScreen extends RaniaScreen{
	
	private LocationController locController = Controllers.locController;
	private SideBar sideBar                  = Parts.getSideBar();
	
	public LocationScreen(){
		super();
	}

	@Override
	public void show() {		
		locController.loadTextures();
		if (!locController.loadPlayer())
			return;
		
		locController.loadBackground();
		locController.loadNebulas();
		locController.loadPlanets();
		locController.loadUsers();
		locController.loadRadar();
		
		locController.addBackground();
		locController.addNebulas();
		locController.addPlanets();
		locController.addUsers();
		locController.addRadar();
		locController.addPlayer();
		sideBar.addElements();
	}
	
	@Override
	public void dispose(){
		locController.clearObjects();
		super.dispose();
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		locController.update(deltaTime);
		mController.update(deltaTime);
		mView.draw();
	}
}
