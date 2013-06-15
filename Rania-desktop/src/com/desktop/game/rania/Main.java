package com.desktop.game.rania;

import com.game.rania.RaniaGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title 	= "Rania";
		config.useGL20 	= true;
		config.width 	= 800;
		config.height 	= 600;
		config.samples  = 8;
		new LwjglApplication(new RaniaGame(), config);
	}
}
