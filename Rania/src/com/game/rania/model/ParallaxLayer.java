package com.game.rania.model;

import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.rania.RaniaGame;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.element.Object;
import com.game.rania.view.Camera;
 
public class ParallaxLayer extends Object{
    
	private float ratio = 0.0f;
	private Sprite drawable = null;
	private Camera camera = null;
	
    public ParallaxLayer(RegionID id, float x, float y, float delta) {
        super(id, x, y);
        ratio = delta;
        drawable = new Sprite();
        camera = RaniaGame.mView.getCamera();
        if (region != null)
        	region.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
    }
    
    @Override
    public boolean draw(SpriteBatch sprite){
		if (!visible)
			return false;

		final float width  = camera.getWidth();
		final float height = camera.getHeight();
		final float layerOffsetX = camera.position.x * ratio;
		final float layerOffsetY = camera.position.y * ratio;
		float positionX = camera.getLeft();
		float positionY = camera.getBottom();

		drawable.setRegion(region);
        drawable.setColor(color);
		drawable.setSize(width, height);
		drawable.setPosition(positionX, positionY);
		positionX += position.x;
		positionY += position.y;
		drawable.setRegion((positionX + layerOffsetX) / region.getRegionWidth(),
						   (positionY + height + layerOffsetY) / region.getRegionHeight(),
						   (positionX + width + layerOffsetX) / region.getRegionWidth(),
						   (positionY + layerOffsetY) / region.getRegionHeight());
		drawable.draw(sprite);
		return true;
    }
}