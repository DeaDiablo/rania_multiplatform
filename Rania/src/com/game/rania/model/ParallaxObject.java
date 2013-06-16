package com.game.rania.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.StaticObject;
import com.game.rania.view.Camera;

public class ParallaxObject extends StaticObject{

	private float  ratio = 0.0f;
	private Camera camera = null;
	
    public ParallaxObject(RegionID id, float x, float y, float delta) {
        super(RaniaGame.mView.getTextureRegion(id), x, y);
        ratio = delta;
        camera = RaniaGame.mView.getCamera();
    }
	
    public ParallaxObject(TextureRegion region, float x, float y, float delta) {
        super(region, x, y);
        ratio = delta;
        camera = RaniaGame.mView.getCamera();
    }
    
    public ParallaxObject(RegionID id, float x, float y, float angle, float scaleX, float scaleY, float delta) {
        super(RaniaGame.mView.getTextureRegion(id), x, y);
        ratio = delta;
        this.angle = angle;
        scale.set(scaleX, scaleY);
        camera = RaniaGame.mView.getCamera();
    }
	
    public ParallaxObject(TextureRegion region, float x, float y, float angle, float scaleX, float scaleY, float delta) {
        super(region, x, y);
        ratio = delta;
        this.angle = angle;
        scale.set(scaleX, scaleY);
        camera = RaniaGame.mView.getCamera();
    }
    
    @Override
    public boolean draw(SpriteBatch sprite){
		if (!visible)
			return false;

		return draw(sprite, new Vector2(position.x - camera.position.x * ratio, position.y - camera.position.y * ratio), angle, scale, color);
    }
}
