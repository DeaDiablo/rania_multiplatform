package com.game.rania.screen;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.MainController;
import com.game.rania.controller.command.SwitchScreenCommand;
import com.game.rania.view.MainView;

public class RaniaScreen implements Screen{
	
	protected MainView mView = null;
	protected MainController mController = null;
	
	public RaniaScreen(){
		mView = RaniaGame.mView;
		mController = RaniaGame.mController;
	}

	public boolean isLoaded(){
		return true;
	}
	
	public void update(float deltaTime){
		mController.update(deltaTime);
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mView.draw();
		update(deltaTime);
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
		if (Gdx.app.getType() == ApplicationType.Desktop)
			return;
		new MainMenu().set();
		Controllers.netController.disconnect();
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
	
	public LoadableScreen asLoadableScreen(){
		return null;
	}

	public void set() {
		Controllers.commandController.addCommand(new SwitchScreenCommand(this));
	}
	
}
