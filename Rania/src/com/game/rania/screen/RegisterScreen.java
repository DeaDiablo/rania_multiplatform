package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.game.rania.RaniaGame;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.StaticObject;
import com.game.rania.model.ui.CheckButton;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;

public class RegisterScreen extends RaniaScreen{
	private CheckButton btnErbo = null;
	private CheckButton btnSiktan = null;
	private CheckButton btnGurdin = null;
	private CheckButton btnArahnid = null;
	private CheckButton btnMort = null;
	
	public RegisterScreen(){
		super();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mView.draw();
	}

	@Override
	public void resize(int width, int height) {		
	}

	@Override
	public void show() {
		
		float width = mView.getCamera().getWidth();
		float height = mView.getCamera().getHeight();
		float halfWidth = width/2.0f;
		float halfHeight = height/2.0f;
		mView.loadTexture("data/backgrounds/bgstars.jpg", RegionID.BACKGROUND_MENU);
		mController.addStaticObject(new StaticObject(RegionID.BACKGROUND_MENU, 0.0f, 0.0f));
		mView.loadTexture("data/sprites/emblems/erbo.png", RegionID.ERBO, 0, 0, 256, 256);
		mView.loadTexture("data/sprites/emblems/siktan.png", RegionID.SIKTAN, 0, 0, 256, 256);
		mView.loadTexture("data/sprites/emblems/mort.png", RegionID.MORT, 0, 0, 256, 256);
		mView.loadTexture("data/sprites/emblems/arahnid.png", RegionID.ARAHNID, 0, 0, 256, 256);
		mView.loadTexture("data/sprites/emblems/gurdin.png", RegionID.GURDIN, 0, 0, 256, 256);
		mView.loadTexture("data/sprites/emblems/erbo_act.png", RegionID.ERBO_ACT, 0, 0, 256, 256);
		mView.loadTexture("data/sprites/emblems/siktan_act.png", RegionID.SIKTAN_ACT, 0, 0, 256, 256);
		mView.loadTexture("data/sprites/emblems/mort_act.png", RegionID.MORT_ACT, 0, 0, 256, 256);
		mView.loadTexture("data/sprites/emblems/arahnid_act.png", RegionID.ARAHNID_ACT, 0, 0, 256, 256);
		mView.loadTexture("data/sprites/emblems/gurdin_act.png", RegionID.GURDIN_ACT, 0, 0, 256, 256);
		TouchAction ErboTouch = new TouchAction() {
			@Override
			public void execute(boolean touch) {	
				btnSiktan.setCheck(false);
				btnGurdin.setCheck(false);
				btnArahnid.setCheck(false);
				btnMort.setCheck(false);
				if (!btnErbo.getCheck()) {btnErbo.setCheck(true);}
			}
		};
		TouchAction SiktanTouch = new TouchAction() {
			@Override
			public void execute(boolean touch) {	
				btnErbo.setCheck(false);
				btnGurdin.setCheck(false);
				btnArahnid.setCheck(false);
				btnMort.setCheck(false);
				if (!btnSiktan.getCheck()) {btnSiktan.setCheck(true);}
			}
		};
		TouchAction GurdinTouch = new TouchAction() {
			@Override
			public void execute(boolean touch) {	
				btnErbo.setCheck(false);
				btnSiktan.setCheck(false);
				btnArahnid.setCheck(false);
				btnMort.setCheck(false);
				if (!btnGurdin.getCheck()) {btnGurdin.setCheck(true);}
			}
		};
		TouchAction ArahnidTouch = new TouchAction() {
			@Override
			public void execute(boolean touch) {	
				btnErbo.setCheck(false);
				btnSiktan.setCheck(false);
				btnGurdin.setCheck(false);
				btnMort.setCheck(false);
				if (!btnArahnid.getCheck()) {btnArahnid.setCheck(true);}
			}
		};
		TouchAction MortTouch = new TouchAction() {
			@Override
			public void execute(boolean touch) {	
				btnErbo.setCheck(false);
				btnSiktan.setCheck(false);
				btnGurdin.setCheck(false);
				btnArahnid.setCheck(false);
				if (!btnMort.checkButton()) {btnMort.setCheck(true);}
			}
		};
		btnErbo = new CheckButton(RegionID.ERBO, RegionID.ERBO_ACT, -halfWidth * 0.0f, halfHeight *0.726f, ErboTouch);
		btnSiktan = new CheckButton(RegionID.SIKTAN, RegionID.SIKTAN_ACT, -halfWidth * 0.2875f, halfHeight *0.726f, SiktanTouch);
		btnGurdin = new CheckButton(RegionID.GURDIN, RegionID.GURDIN_ACT, halfWidth * 0.2875f, halfHeight *0.726f, GurdinTouch);
		btnArahnid = new CheckButton(RegionID.ARAHNID, RegionID.ARAHNID_ACT, halfWidth * 0.575f, halfHeight *0.726f, ArahnidTouch);
		btnMort = new CheckButton(RegionID.MORT, RegionID.MORT_ACT, -halfWidth * 0.575f, halfHeight *0.726f, MortTouch);
		mController.addStaticHUDObject(btnErbo);
		mController.addStaticHUDObject(btnSiktan);
		mController.addStaticHUDObject(btnGurdin);
		mController.addStaticHUDObject(btnArahnid);
		mController.addStaticHUDObject(btnMort);
		mView.loadTexture("data/gui/back.png", RegionID.BTNBACK_OFF, 0, 0, 512, 128);
		mView.loadTexture("data/gui/back.png", RegionID.BTNBACK_ON, 0, 128, 512, 128);
		mController.addStaticHUDObject(
				new PressedButton(RegionID.BTNBACK_OFF,
								  RegionID.BTNBACK_ON,
								  halfWidth * 0.7125f, -halfHeight * 0.8444f,
								  new TouchAction() {
									@Override
									public void execute(boolean touch) {	
										dispose();
										RaniaGame.mGame.setScreen(new MainMenu());
									}
								  }));
	}
}
