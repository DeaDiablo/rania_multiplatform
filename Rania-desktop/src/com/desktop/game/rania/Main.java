package com.desktop.game.rania;

import com.game.rania.RaniaGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title 	= "Rinia";
		config.useGL20 	= true;
		config.width 	= 1024;
		config.height 	= 768;
		
		new LwjglApplication(new RaniaGame(), config);
	}
}
