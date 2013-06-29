package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.LocationController;

public class LocationScreen extends RaniaScreen{
	
	private LocationController locController = Controllers.locController;
	private Sidebar sideBar = new Sidebar(this);
	
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
		sideBar.loadSidebar();
		
		locController.addBackground();
		locController.addNebulas();
		locController.addPlanets();
		locController.addUsers();
		locController.addRadar();
		locController.addPlayer();
		mController.addObject(sideBar);
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
