package com.game.rania.model.element;

public class HUDObject extends Object {

	public HUDObject(float posX, float posY, float rotAngle, float scaleX,
			float scaleY) {
		super(posX, posY, rotAngle, scaleX, scaleY);
	}

	public HUDObject(RegionID id, float posX, float posY, float rotAngle,
			float scaleX, float scaleY) {
		super(id, posX, posY, rotAngle, scaleX, scaleY);
	}

	public HUDObject(RegionID id, float posX, float posY, float rotAngle) {
		super(id, posX, posY, rotAngle, 1.0f, 1.0f);
	}

	public HUDObject(RegionID id, float posX, float posY) {
		super(id, posX, posY, 0.0f, 1.0f, 1.0f);
	}

	@Override
	public HUDObject asHUDObject() {
		return this;
	}
}
