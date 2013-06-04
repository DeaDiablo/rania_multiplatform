package com.game.rania;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.game.rania.controller.MainController;
import com.game.rania.model.Location;
import com.game.rania.net.NetController;
import com.game.rania.screen.MainMenu;
import com.game.rania.userdata.User;
import com.game.rania.view.MainView;

public class RaniaGame extends Game {
	
	//game
	public static RaniaGame                 mGame       = null;
	public static HashMap<String, Location> mLocations  = null;
	
	//mvc
	public static MainView       mView       = null;
	public static MainController mController = null;
	
	//network
	public static NetController nController = null;
	public static User          mUser       = null;

	@Override
	public void create() {
		mGame = this;
		mView = new MainView();
		mController = new MainController();
		mUser = new User();
		nController = new NetController();
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
		if (mLocations != null)
			mLocations.clear();
		Gdx.app.exit();
	}
}
