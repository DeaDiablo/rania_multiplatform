package com.game.rania;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.game.rania.controller.MainController;
import com.game.rania.controller.ClientController;
import com.game.rania.screen.MainMenu;
import com.game.rania.view.MainView;

public class RaniaGame extends Game {
	
	//game
	public static RaniaGame   	   mGame = null;
	public static ClientController mClient = null;
	
	//mvc
	public static MainView       mView       = null;
	public static MainController mController = null;

	@Override
	public void create() {
		ShaderProgram.pedantic = false;
		mGame = this;
		mView = new MainView();
		mController = new MainController();
		mClient = new ClientController(mController.getCommandController());
		Gdx.input.setInputProcessor(mController);
		setScreen(new MainMenu());
	}

	/*
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void render() {
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
	*/

	@Override
	public void dispose() {
		Gdx.app.exit();
	}
}
