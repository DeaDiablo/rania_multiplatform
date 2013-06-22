package com.game.rania.controller;

import com.game.rania.net.NetController;
import com.game.rania.view.MainView;

public class Controllers {
	public static CommandController  commandController = null;
	public static NetController 	 netController 	   = null;
	public static ClientController   clientController  = null;
	public static InputController    inputController   = null;
	public static LocationController locController 	   = null;
	
	public static void initSystemControllers(MainView mView, MainController mController){
		commandController = new CommandController(mController);
		netController	  = new NetController(commandController);
		clientController  = new ClientController(netController);
		inputController   = new InputController(mController);
		locController     = new LocationController(mController, mView, clientController);
	}
	
	public static void clearGlobalControllers(){
		commandController = null;
		netController	  = null;
		clientController  = null;
		inputController   = null;
		locController     = null;
	}
}
