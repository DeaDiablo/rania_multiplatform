package com.game.rania.screen;

import java.util.Vector;

public class LoadableScreen extends RaniaScreen {

	protected boolean loaded = true;

	public LoadableScreen() {
		super();
		loaded = false;
	}

	@Override
	public boolean isLoaded() {
		return loaded;
	}

	public abstract class LoadObject {
		public String loadText = null;
		public LoadObject(String text) {
			loadText = text;
		}
		public abstract void load();
	}

	protected Vector<LoadObject> loadObjects = new Vector<LoadObject>();

	public void addLoadObject(LoadObject loadObject) {
		loadObjects.add(loadObject);
	}

	public String getCurrentResource() {
		if (loadObjects.isEmpty())
			return null;
		LoadObject loadObject = loadObjects.get(0);
		if (loadObject != null)
			return loadObject.loadText;
		return null;
	}

	public void loadNextResource() {
		LoadObject loadObject = loadObjects.get(0);
		if (loadObject == null)
			return;

		loadObject.load();
		loadObjects.remove(loadObject);
		if (loadObjects.isEmpty())
			loaded = true;
	}

	@Override
	public LoadableScreen asLoadableScreen() {
		return this;
	}
}
