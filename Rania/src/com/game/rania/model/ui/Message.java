package com.game.rania.model.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.rania.RaniaGame;
import com.game.rania.model.Text;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Message extends Object{

	private Text  text = null;
	
	public Message(RegionID id, float x, float y, Text text, float lifeTime) {
		super(id, x, y);
		this.text = text;
		this.lifeTime = lifeTime;
		touchObject = true;
	}

	@Override
	public boolean draw(SpriteBatch sprite){
		if (!super.draw(sprite))
			return false;

		if (text != null)
			text.draw(sprite, text.position.x + position.x, text.position.y + position.y, angle, scale.x, scale.y);

		return true;
	}
	
	@Override
	public boolean touchUp(float x, float y) {	
		RaniaGame.mController.removeHUDObject(this);
		return true;
	}
}
