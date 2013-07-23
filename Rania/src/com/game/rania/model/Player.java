package com.game.rania.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.rania.controller.Controllers;

public class Player extends User {

	public Target target = new Target(0, Target.none, null);

	public Player(int id, float posX, float posY, String ShipName,
			String PilotName, int domain) {
		super(id, posX, posY, ShipName, PilotName, domain);
		zIndex = Indexes.player;
		isPlayer = true;
	}
	public Player(int id, float posX, float posY, String ShipName,
			String PilotName, int domain, int inPlanet) {
		super(id, posX, posY, ShipName, PilotName, domain, inPlanet);
		zIndex = Indexes.player;
		isPlayer = true;
	}

	@Override
	public void stop() {
		super.stop();
		Controllers.netController.sendTouchPoint((int) position.x,
				(int) position.y, (int) position.x, (int) position.y);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		target.update(deltaTime);
	}

	@Override
	public boolean draw(SpriteBatch sprite) {
		target.draw(sprite);
		return super.draw(sprite);
	}
}
