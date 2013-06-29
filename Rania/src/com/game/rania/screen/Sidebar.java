package com.game.rania.screen;

import com.badlogic.gdx.Screen;
import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;
import com.game.rania.view.MainView;

public class Sidebar extends Group{

	private MainView mView = RaniaGame.mView;

	private Screen		  screen 	 	= null;
	private Group		  menu       	= new Group();
	private PressedButton panelBlank 	= null;
	private PressedButton btnCancel 	= null;
	private PressedButton btnDisconnect = null;
	private Group		  panel       	= new Group();
	private PressedButton btnMenu 		= null;
	private PressedButton btnChat 		= null;
	
	public Sidebar(Screen screen){
		this.screen = screen;
	}

	public void loadSidebar(){
		mView.loadTexture("data/gui/blank.png", RegionID.BLANK);
		mView.loadTexture("data/gui/ui_menu.png", RegionID.BTN_UI_MENU_OFF, 0,   0, 96,  96);
		mView.loadTexture("data/gui/ui_menu.png", RegionID.BTN_UI_MENU_ON,  0,  96, 96,  96);
		mView.loadTexture("data/gui/ui_chat.png", RegionID.BTN_UI_CHAT_OFF, 0,   0, 96,  96);
		mView.loadTexture("data/gui/ui_chat.png", RegionID.BTN_UI_CHAT_ON,  0,  96, 96,  96);
		mView.loadTexture("data/gui/ui_back.png", RegionID.BTN_UI_BACK_OFF, 0,   0, 450, 100);
		mView.loadTexture("data/gui/ui_back.png", RegionID.BTN_UI_BACK_ON,  0, 100, 450, 100);
		mView.loadTexture("data/gui/ui_exit.png", RegionID.BTN_UI_EXIT_OFF, 0,   0, 450, 100);
		mView.loadTexture("data/gui/ui_exit.png", RegionID.BTN_UI_EXIT_ON,  0, 100, 450, 100);

		float halfWidth = mView.getHUDCamera().getWidth() * 0.5f;
		float halfHeight  = mView.getHUDCamera().getHeight() * 0.5f;
		
		panelBlank = new PressedButton(RegionID.BLANK, RegionID.BLANK, 0.0f, 0.0f);
		
		btnDisconnect = new PressedButton(RegionID.BTN_UI_EXIT_OFF,
				  						  RegionID.BTN_UI_EXIT_ON,
				  						  halfWidth * 0.0f,
				  						  -halfHeight * 0.137f,
				  						  new TouchAction() {
											  @Override
											  public void execute(boolean touch) {
												  Controllers.netController.disconnect();
												  screen.dispose();
												  RaniaGame.mGame.setScreen(new MainMenu());
											  }
										 });

		btnCancel = new PressedButton(RegionID.BTN_UI_BACK_OFF,
				  					  RegionID.BTN_UI_BACK_ON,
				  					  halfWidth * 0.0f, halfHeight * 0.137f,
				  					  new TouchAction() {
										  @Override
										  public void execute(boolean touch) {
						  						menu.setVisible(false);
											  	panel.setVisible(true);
											  }
										  });

		btnMenu = new PressedButton(RegionID.BTN_UI_MENU_OFF,
				  					RegionID.BTN_UI_MENU_ON,
				  					halfWidth * 0.9396f, -halfHeight * 0.8926f,
				  					new TouchAction() {
					  					@Override
					  					public void execute(boolean touch) {
					  						panel.setVisible(false);
					  						menu.setVisible(true);
					  					}
					  				});
		
		btnChat = new PressedButton(RegionID.BTN_UI_CHAT_OFF,
				  					RegionID.BTN_UI_CHAT_ON,
				  					halfWidth * 0.9396f, -halfHeight * 0.6963f,
				  					new TouchAction() {
										@Override
										public void execute(boolean touch) {
											//showChat
										}
									});

		menu.addElement(btnDisconnect);
		menu.addElement(btnCancel);
		menu.addElement(panelBlank);
		addElement(menu);
		
		panel.addElement(btnMenu);
		panel.addElement(btnChat);
		addElement(panel);
		
		menu.setVisible(false);
	}	
}
