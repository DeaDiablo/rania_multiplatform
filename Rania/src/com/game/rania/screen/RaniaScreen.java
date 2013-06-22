package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.game.rania.RaniaGame;
import com.game.rania.controller.MainController;
import com.game.rania.view.MainView;

public class RaniaScreen implements Screen{
	
	protected MainView mView = null;
	protected MainController mController = null;
	
	public RaniaScreen(){
		mView = RaniaGame.mView;
		mController = RaniaGame.mController;
		mController.init();
	}

	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		Gdx.input.setOnscreenKeyboardVisible(false);
		mController.clear();
		mView.clear();
	}
	
}
