package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.game.rania.RaniaGame;
import com.game.rania.controller.MainController;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.StaticObject;
import com.game.rania.view.MainView;

public class RegisterScreen implements Screen{
	private MainView view = null;
	private MainController controller = null;
	private RaniaGame game = null;
	private boolean SelectMort = false;
	private boolean SelectErbo = false;
	private boolean SelectArahnid = false;
	private boolean SelectGurdin = false;
	private boolean SelectSiktan = false;
	private boolean SelectDemiurg = false;
	private StaticObject embMort = null;  
	private StaticObject embMortAct = null;
	private StaticObject embErbo = null;  
	private StaticObject embErboAct = null;
	private StaticObject embArahnid = null;  
	private StaticObject embArahnidAct = null;
	private StaticObject embSiktan = null;  
	private StaticObject embSiktanAct = null;
	private StaticObject embGurdin = null;  
	private StaticObject embGurdinAct = null;
	private StaticObject embDemiurg = null;  
	private StaticObject embDemiurgAct = null;
	
	public RegisterScreen(){
		view = RaniaGame.mView;
		controller = RaniaGame.mController;
		game = RaniaGame.mGame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		view.draw();
	}

	@Override
	public void resize(int width, int height) {		
	}

	@Override
	public void show() {
		float Width = view.getCamera().getWidth();
		float Height = view.getCamera().getHeight();
		view.loadTexture("data/backgrounds/menu.jpg", RegionID.BACKGROUND_MENU, 0, 0, 768, 512);
		controller.addStaticObject(new StaticObject(RegionID.BACKGROUND_MENU, 0.0f, 0.0f));
		view.loadTexture("data/sprites/emblems/erbo.png", RegionID.ERBO, 0, 0, 256, 256);
		embErbo = new StaticObject(RegionID.ERBO, -Width/2.0f+64, Height/2.0f-64);
		embErbo.scale.set(0.5f, 0.5f);
		embErbo.visible = true;
		controller.addStaticObject(embErbo);
		view.loadTexture("data/sprites/emblems/siktan.png", RegionID.SIKTAN, 0, 0, 256, 256);
		embSiktan = new StaticObject(RegionID.SIKTAN, -Width/2.0f+64+128, Height/2.0f-64);
		embSiktan.scale.set(0.5f, 0.5f);
		embSiktan.visible = true;
		controller.addStaticObject(embSiktan);
		view.loadTexture("data/sprites/emblems/mort.png", RegionID.MORT, 0, 0, 256, 256);
		embMort = new StaticObject(RegionID.MORT, -Width/2.0f+64+128+128, Height/2.0f-64);
		embMort.scale.set(0.5f, 0.5f);
		embMort.visible = true;
		controller.addStaticObject(embMort);
		view.loadTexture("data/sprites/emblems/arahnid.png", RegionID.ARAHNID, 0, 0, 256, 256);
		embArahnid = new StaticObject(RegionID.ARAHNID, -Width/2.0f+64+128+128+128, Height/2.0f-64);
		embArahnid.scale.set(0.5f, 0.5f);
		embArahnid.visible = true;
		controller.addStaticObject(embArahnid);
		view.loadTexture("data/sprites/emblems/gurdin.png", RegionID.GURDIN, 0, 0, 256, 256);
		embGurdin = new StaticObject(RegionID.GURDIN, -Width/2.0f+64+128+128+128+128, Height/2.0f-64);
		embGurdin.scale.set(0.5f, 0.5f);
		embGurdin.visible = true;
		controller.addStaticObject(embGurdin);
		view.loadTexture("data/sprites/emblems/demiurg.png", RegionID.DEMIURG, 0, 0, 256, 256);
		embDemiurg = new StaticObject(RegionID.DEMIURG, -Width/2.0f+64+128+128+128+128+128, Height/2.0f-64);
		embDemiurg.scale.set(0.5f, 0.5f);
		embDemiurg.visible = true;
		controller.addStaticObject(embDemiurg);
		view.loadTexture("data/sprites/emblems/erbo_act.png", RegionID.ERBO_ACT, 0, 0, 256, 256);
		embErboAct = new StaticObject(RegionID.ERBO_ACT, -Width/2.0f+64, Height/2.0f-64);
		embErboAct.scale.set(0.5f, 0.5f);
		embErboAct.visible = false;
		controller.addStaticObject(embErboAct);
		view.loadTexture("data/sprites/emblems/siktan_act.png", RegionID.SIKTAN_ACT, 0, 0, 256, 256);
		embSiktanAct = new StaticObject(RegionID.SIKTAN_ACT, -Width/2.0f+64+128, Height/2.0f-64);
		embSiktanAct.scale.set(0.5f, 0.5f);
		embSiktanAct.visible = false;
		controller.addStaticObject(embSiktanAct);
		view.loadTexture("data/sprites/emblems/mort_act.png", RegionID.MORT_ACT, 0, 0, 256, 256);
		embMortAct = new StaticObject(RegionID.MORT_ACT, -Width/2.0f+64+128+128, Height/2.0f-64);
		embMortAct.scale.set(0.5f, 0.5f);
		embMortAct.visible = false;
		controller.addStaticObject(embMortAct);
		view.loadTexture("data/sprites/emblems/arahnid_act.png", RegionID.ARAHNID_ACT, 0, 0, 256, 256);
		embArahnidAct = new StaticObject(RegionID.ARAHNID_ACT, -Width/2.0f+64+128+128+128, Height/2.0f-64);
		embArahnidAct.scale.set(0.5f, 0.5f);
		embArahnidAct.visible = false;
		controller.addStaticObject(embArahnidAct);
		view.loadTexture("data/sprites/emblems/gurdin_act.png", RegionID.GURDIN_ACT, 0, 0, 256, 256);
		embGurdinAct = new StaticObject(RegionID.GURDIN_ACT, -Width/2.0f+64+128+128+128+128, Height/2.0f-64);
		embGurdinAct.scale.set(0.5f, 0.5f);
		embGurdinAct.visible = false;
		controller.addStaticObject(embGurdinAct);
		view.loadTexture("data/sprites/emblems/demiurg_act.png", RegionID.DEMIURG_ACT, 0, 0, 256, 256);
		embDemiurgAct = new StaticObject(RegionID.DEMIURG_ACT, -Width/2.0f+64+128+128+128+128+128, Height/2.0f-64);
		embDemiurgAct.scale.set(0.5f, 0.5f);
		embDemiurgAct.visible = false;
		controller.addStaticObject(embDemiurgAct);
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
		controller.clear();
		view.clear();
	}
}
