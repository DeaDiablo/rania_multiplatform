package com.game.rania.screen.part;

public class Parts {
	
	//sidebar	
	protected static SideBar sideBar = null;
	public static SideBar getSideBar(){
		if (sideBar == null)
			sideBar = new SideBar();
		return sideBar;
	}
	public static void removeSideBar(){
		if (sideBar != null){
			sideBar.unloadTextures();
			sideBar = null;
		}
	}
}
