package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.LocationController;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;

public class LocationScreen extends RaniaScreen{
	
	private LocationController locController = Controllers.locController;

	private PressedButton panelBlank = null;
	private PressedButton btnMenu = null;
	private PressedButton btnChat = null;
	private PressedButton btnCancel = null;
	private PressedButton btnDisconnect = null;
	
	public LocationScreen(){
		super();
	}

	@Override
	public void show() {
		LoadHUDobjects();
		
		locController.loadTextures();
		if (!locController.loadPlayer())
			return;
		
		locController.loadBackground();
		locController.loadPlanets();
		locController.loadUsers();
		locController.loadRadar();
		
		locController.addBackground();
		locController.addPlanets();
		locController.addUsers();
		locController.addRadar();
		locController.addPlayer();

		mController.addHUDObject(btnMenu);
		mController.addHUDObject(btnChat);
		mController.addHUDObject(btnDisconnect);
		mController.addHUDObject(btnCancel);
		mController.addHUDObject(panelBlank);
		ShowHUDbuttons();
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
	
	private void ShowMenu()
	{
		panelBlank.visible = true;
		btnDisconnect.visible = true;
		btnCancel.visible = true;
		btnMenu.visible = false;
		btnChat.visible = false;
		locController.getRadar().visible = false;
	}

	private void ShowHUDbuttons()
	{
		panelBlank.visible = false;
		btnDisconnect.visible = false;
		btnCancel.visible = false;
		btnMenu.visible = true;
		btnChat.visible = true;
		locController.getRadar().visible = true;
	}

	private void LoadHUDobjects()
	{
		float halfWidth = mView.getHUDCamera().getWidth() * 0.5f;
		float halfHeight  = mView.getHUDCamera().getHeight() * 0.5f;;
		mView.loadTexture("data/gui/blank.png", RegionID.BLANK);
		mView.loadTexture("data/gui/ui_menu.png", RegionID.BTN_UI_MENU_OFF, 0,   0, 96,  96);
		mView.loadTexture("data/gui/ui_menu.png", RegionID.BTN_UI_MENU_ON,  0,  96, 96,  96);
		mView.loadTexture("data/gui/ui_chat.png", RegionID.BTN_UI_CHAT_OFF, 0,   0, 96,  96);
		mView.loadTexture("data/gui/ui_chat.png", RegionID.BTN_UI_CHAT_ON,  0,  96, 96,  96);
		mView.loadTexture("data/gui/ui_back.png", RegionID.BTN_UI_BACK_OFF, 0,   0, 450, 100);
		mView.loadTexture("data/gui/ui_back.png", RegionID.BTN_UI_BACK_ON,  0, 100, 450, 100);
		mView.loadTexture("data/gui/ui_exit.png", RegionID.BTN_UI_EXIT_OFF, 0,   0, 450, 100);
		mView.loadTexture("data/gui/ui_exit.png", RegionID.BTN_UI_EXIT_ON,  0, 100, 450, 100);
		panelBlank = new PressedButton(RegionID.BLANK, RegionID.BLANK, 0.0f, 0.0f);
		btnDisconnect = new PressedButton(RegionID.BTN_UI_EXIT_OFF,
				  RegionID.BTN_UI_EXIT_ON,
				  halfWidth * 0.0f, -halfHeight * 0.137f,
				  new TouchAction() {
					@Override
					public void execute(boolean touch) {
								Controllers.clientController.disconnect();
								dispose();
								RaniaGame.mGame.setScreen(new MainMenu());
							}});
		btnMenu = new PressedButton(RegionID.BTN_UI_MENU_OFF,
				  RegionID.BTN_UI_MENU_ON,
				  halfWidth * 0.9396f, -halfHeight * 0.8926f,
				  new TouchAction() {
					@Override
					public void execute(boolean touch) {ShowMenu();}});
		btnChat = new PressedButton(RegionID.BTN_UI_CHAT_OFF,
				  RegionID.BTN_UI_CHAT_ON,
				  halfWidth * 0.9396f, -halfHeight * 0.6963f,
				  new TouchAction() {
					@Override
					public void execute(boolean touch) {	
								
							}});
		btnCancel = new PressedButton(RegionID.BTN_UI_BACK_OFF,
				  RegionID.BTN_UI_BACK_ON,
				  halfWidth * 0.0f, halfHeight * 0.137f,
				  new TouchAction() {
					@Override
					public void execute(boolean touch) {ShowHUDbuttons();}});
	}
}
