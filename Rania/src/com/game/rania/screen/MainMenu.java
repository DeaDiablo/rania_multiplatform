package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.game.rania.Config;
import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.model.Text;
import com.game.rania.model.element.Font;
import com.game.rania.model.element.HUDObject;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.ui.Edit;
import com.game.rania.model.ui.Message;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;

public class MainMenu extends RaniaScreen{
	
	public MainMenu(){
	}
	
	@Override
	public void show() {
		float halfWidth = mView.getHUDCamera().getWidth() * 0.5f;
		float halfHeight = mView.getHUDCamera().getHeight() * 0.5f;
		mView.loadTexture("data/backgrounds/menu.jpg", RegionID.BACKGROUND_MENU);
		mView.loadTexture("data/gui/fly.png", RegionID.BTNLOGIN_OFF, 0, 0, 512, 128);
		mView.loadTexture("data/gui/fly.png", RegionID.BTNLOGIN_ON, 0, 128, 512, 128);
		mView.loadTexture("data/gui/newreg.png", RegionID.BTNREG_OFF, 0, 0, 512, 128);
		mView.loadTexture("data/gui/newreg.png", RegionID.BTNREG_ON, 0, 128, 512, 128);
		mView.loadTexture("data/gui/exit.png", RegionID.BTNEXIT_OFF, 0, 0, 512, 128);
		mView.loadTexture("data/gui/exit.png", RegionID.BTNEXIT_ON, 0, 128, 512, 128);
		mController.addHUDObject(new HUDObject(RegionID.BACKGROUND_MENU, 0.0f, 0.0f));

		

		mView.loadTexture("data/gui/edit.png", RegionID.EDIT_OFF, 0, 0, 512, 128);
		mView.loadTexture("data/gui/edit.png", RegionID.EDIT_ON, 0, 128, 512, 128);
		
		final Edit loginEdit = 
				new Edit(RegionID.EDIT_OFF, 
				     	 RegionID.EDIT_ON,
						 -halfWidth * 0.625f,
						 -halfHeight * 0.7f,
						 new Text(Config.autoLogin, Font.getFont("data/fonts/Picaresque Two.ttf", 20), new Color(1.0f, 0.667f, 0.0f, 1.0f), 0, 0),
						 16);
		
		final Edit passwordEdit = 
				new Edit(RegionID.EDIT_OFF, 
				     	 RegionID.EDIT_ON,
				     	 -halfWidth * 0.0916f,
				     	 -halfHeight * 0.7f,
						 new Text(Config.autoPassword, Font.getFont("data/fonts/Picaresque Two.ttf", 20), new Color(1.0f, 0.667f, 0.0f, 1.0f), 0, 0),
						 16);
		
		loginEdit.nextControll = passwordEdit;
		passwordEdit.nextControll = loginEdit;

		mController.addHUDObject(loginEdit);
		mController.addHUDObject(passwordEdit);
		
		mController.addHUDObject(
				new PressedButton(RegionID.BTNLOGIN_OFF,
								  RegionID.BTNLOGIN_ON,
								  halfWidth * 0.675f, halfHeight * 0.188f,
								  //new Text("¬ œŒÀ≈“", Font.getFont("data/fonts/Postmodern One.ttf", 15), new Color(0.774f, 0.957f, 1.0f, 1.0f), 0, 0),
								  new TouchAction() {
									@Override
									public void execute(boolean touch) {	
										if ((loginEdit.getText() != "") && (passwordEdit.getText() != ""))
										{
											if (Controllers.clientController.login(loginEdit.getText(), passwordEdit.getText()))
											{
												dispose();
												Controllers.locController.loadLocations();
												RaniaGame.mGame.setScreen(new LocationScreen());
											}
											else
											{
												mController.addHUDObject(
														new Message(RegionID.EDIT_ON, 0, 0,
															    new Text("ÕÂ‚ÂÌ˚È ÎÓ„ËÌ ËÎË Ô‡ÓÎ¸", Font.getFont("data/fonts/Postmodern One.ttf", 25), new Color(1.0f, 0.667f, 0.0f, 1.0f), 0, 0),
															    5));
											}
										}
									}
								  }));
		
		mController.addHUDObject(
				new PressedButton(RegionID.BTNREG_OFF,
								  RegionID.BTNREG_ON,
								  halfWidth * 0.675f, -halfHeight * 0.06f,
								  //new Text("ÕŒ¬€… œ»ÀŒ“", Font.getFont("data/fonts/Postmodern One.ttf", 15), new Color(0.774f, 0.957f, 1.0f, 1.0f), 0, 0),
								  new TouchAction() {
									@Override
									public void execute(boolean touch) {	
										dispose();
										RaniaGame.mGame.setScreen(new RegisterScreen());
									}
								  }));
		
		mController.addHUDObject(
				new PressedButton(RegionID.BTNEXIT_OFF,
								  RegionID.BTNEXIT_ON,
								  halfWidth * 0.675f, -halfHeight * 0.308f,
								  //new Text("¬€’Œƒ", Font.getFont("data/fonts/Postmodern One.ttf", 15), new Color(0.774f, 0.957f, 1.0f, 1.0f), 0, 0),
								  new TouchAction() {
									@Override
									public void execute(boolean touch) {	
										dispose();
							    		RaniaGame.mGame.dispose();
									}
								  }));
	}

	@Override
	public void dispose() {
		Gdx.input.setOnscreenKeyboardVisible(false);
		mController.clear();
		mView.clear();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {		
	}

	@Override
	public void render(float deltaTime) {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		mController.update(deltaTime);
		mView.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}
}
