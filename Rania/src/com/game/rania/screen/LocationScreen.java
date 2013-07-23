package com.game.rania.screen;

import com.game.rania.controller.Controllers;
import com.game.rania.controller.LocationController;
import com.game.rania.screen.part.InfoPanel;
import com.game.rania.screen.part.Parts;
import com.game.rania.screen.part.SideBar;
import com.game.rania.screen.part.SkillsPanel;

public class LocationScreen extends LoadableScreen{

	private LocationController locController = Controllers.locController;

	private SideBar            sideBar       = Parts.getSideBar();
	private InfoPanel          infoPanel     = Parts.getInfoPanel();
	private SkillsPanel        skillsPanel   = Parts.getSkillsPanel();
	
	public LocationScreen(){
		super();

		LoadObject loadObject = new LoadObject(new String("Загрузка текстур...")) {
			@Override
			public void load() {
				locController.loadTextures();
			}
		};
		addLoadObject(loadObject);

		loadObject = new LoadObject(new String("Загрузка локаций...")) {
			@Override
			public void load() {
				locController.loadLocationsAndNebulas();
			}
		};
		addLoadObject(loadObject);
		
		loadObject = new LoadObject(new String("Загрузка предметов...")) {
			@Override
			public void load() {
				locController.loadItems();
			}
		};
		addLoadObject(loadObject);
		
		loadObject = new LoadObject(new String("Загрузка игрока...")) {
			@Override
			public void load() {
				locController.loadPlayer();
			}
		};
		addLoadObject(loadObject);
		
		loadObject = new LoadObject(new String("Загрузка фона...")) {
			@Override
			public void load() {
				locController.loadBackground();
			}
		};
		addLoadObject(loadObject);

		loadObject = new LoadObject(new String("Загрузка туманностей...")) {
			@Override
			public void load() {
				locController.loadNebulas();
			}
		};
		addLoadObject(loadObject);

		loadObject = new LoadObject(new String("Загрузка планет...")) {
			@Override
			public void load() {
				locController.loadPlanets();
			}
		};
		addLoadObject(loadObject);

		loadObject = new LoadObject(new String("Загрузка систем корабля...")) {
			@Override
			public void load() {
				locController.loadRadar();
				infoPanel.loadPart();
				skillsPanel.loadPart();
			}
		};
		addLoadObject(loadObject);
		
		loadObject = new LoadObject(new String("Загрузка завершена.")) {
			@Override
			public void load() {
				locController.loadComplete();
			}
		};
		addLoadObject(loadObject);
	}

	@Override
	public void show() {
		if (locController.getPlayer() == null || locController.getCurrentLocation() == null)
			return;

		locController.addBackground();
		locController.addNebulas();
		locController.addPlanets();
		locController.addUsers();
		locController.addRadar();
		locController.addPlayer();
		sideBar.addPart();
		infoPanel.addPart();
		skillsPanel.addPart();
	}
	
	@Override
	public void dispose(){
		locController.clearObjects();
		super.dispose();
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		locController.update(deltaTime);
	}
}
