package com.html.rania.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.game.rania.RaniaGame;

public class RaniaGwt extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(1600, 872);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new RaniaGame(1920, 1080);
	}
}